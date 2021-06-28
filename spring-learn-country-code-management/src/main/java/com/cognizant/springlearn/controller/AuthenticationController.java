package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.SpringLearnApplication;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	@GetMapping("/authenticate")
	public Map<String,String> authenicate(@RequestHeader("Authorization") String authHeader) {
		LOGGER.info("Start");
		Map<String,String> map = new HashMap<>();
		LOGGER.debug("AuthHeader -> {}",authHeader);
		map.put("token", generateJwt(getUser(authHeader)));
		LOGGER.debug("Auth Token - {}",map.get("token"));
		LOGGER.info("End");
		return map;
	}
	private String getUser(String authHeader) {
		authHeader = authHeader.substring(6,authHeader.length()-1);
		String res = new String(Base64.getDecoder().decode(authHeader));
		return res.split(":")[0];
	}
	private String generateJwt(String user) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);
		// Set the token issue time as current time
		builder.setIssuedAt(new Date());
		// Set the token expire as 20 minutes from now
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		String token = builder.compact();
		return token;
	}
}
