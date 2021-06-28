package com.cognizant.springlearn.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestHeader;
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
	/*
	 * since we are taking the messages from message.propertiess we need a message source object to access.
	 */
	@Autowired
	MessageSource messageSource;
	/*
	 * this method is used to make use of internationalization.
	 * we need to include accept-language parameter in header when we include.
	 * and value will be en,fr ,etc.
	 */
	@RequestMapping("/hello-internationalized")
	public String sayHelloI18n(@RequestHeader(name="Accept-language",required = false) Locale locale)
	{
		return messageSource.getMessage("hello.message",null,"Default Message", locale);
		//instead of getting the loacle from header we can also use locale contextholder to get current locale.
		//return messageSource.getMessage("hello.message",null,"Default message", LocaleContextHolder.getLocale());
	}
}
