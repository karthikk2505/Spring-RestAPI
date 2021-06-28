package com.cognizant.springlearn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;

@RestController//(combination of @Controller and @Response body) 
@RequestMapping()
public class CountryController {
	//@Value annotaion  to read the data from properties file.
	
	@Autowired
	private CountryService countryService;
	/*
	 * REST - Country Web Service
	 * Write a REST service that returns India country details in the earlier created spring learn application.
	 */
	@RequestMapping("/country")
	public Country getCountryIndia() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		Country country = (Country) context.getBean("country", Country.class);
		return country;
	}
	/*
	 * REST - Get all countries.
	 * Write a REST service that returns all the countries
	 */
	@GetMapping("/countries")
	public List<Country> getAllCountries()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		List<Country> countriesList = (ArrayList<Country>) context.getBean("countryList");
		
		return countriesList;
	}
	/*
	 * REST - Get country based on country code
	 * Write a REST service that returns a specific country based on country code.
	 * The country code should be case insensitive.
	 */
	@GetMapping("/country/{code}")
	public Country getCountry(@PathVariable("code") String code) {
		Country country = countryService.getCountry(code);

		return country;
		
	}
}
