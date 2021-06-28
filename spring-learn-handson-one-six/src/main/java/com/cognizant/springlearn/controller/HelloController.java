package com.cognizant.springlearn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Hello World RESTful Web Service Write a REST service in the spring learn application created earlier,
 * that returns the text "Hello World!!" using Spring Web Framework. Refer details below
 */
@RestController
public class HelloController {
	
	
	@RequestMapping("/hello")
	public String sayHello()
	{
		return "Hello World!!";
	}
}
