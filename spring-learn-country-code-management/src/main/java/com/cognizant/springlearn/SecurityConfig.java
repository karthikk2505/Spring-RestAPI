package com.cognizant.springlearn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cognizant.springlearn.security.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().httpBasic().and()
		.authorizeRequests()
		//.antMatchers("/countries").hasRole("USER")
		.antMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
		.anyRequest().authenticated()
		.and()
		.addFilter(new JwtAuthorizationFilter(authenticationManager()));
		
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin").password(passwordEncoder().encode("pwd")).roles("ADMIN")
		.and()
		.withUser("user").password(passwordEncoder().encode("pwd")).roles("USER");
	}
	@Bean
	public PasswordEncoder passwordEncoder() {

	LOGGER.info("Start");

	return new BCryptPasswordEncoder();

	}
	//curl -s -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjI0MzAwMjQyLCJleHAiOjE2MjQzMDE0NDJ9.BYFsAKhvOgkgjYh7IAYxPFE8h0GvJrCeu7txVTzEtYc" http://localhost:8090/countries
}
