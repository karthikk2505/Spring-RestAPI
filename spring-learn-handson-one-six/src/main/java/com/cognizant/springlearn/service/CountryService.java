package com.cognizant.springlearn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	private ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
	private List<Country> countriesList = (ArrayList<Country>) context.getBean("countryList");
	/*
	 * Get the country code using @PathVariable
	 * Get country list from country.xml
	 * Iterate through the country list
	 * Make a case insensitive matching of country code and return the country.
	 * Lambda expression can also be used instead of iterating the country list.
	 */
	public Country getCountry(String code) {
		
		Country found =countriesList.stream()
			.filter(country->country.getCode().equalsIgnoreCase(code))
			.findAny()
			.orElse(null);
		if(found==null)
		{
			throw new CountryNotFoundException();
		}
		/*for(Country country: countriesList)
		{
			if(country.getCode().equalsIgnoreCase(code))
				return country;
		}*/
		return found;
	}
}
