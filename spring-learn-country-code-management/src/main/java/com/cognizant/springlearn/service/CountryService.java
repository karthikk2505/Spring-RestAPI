package com.cognizant.springlearn.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.dao.CountryDao;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
	private static List<Country> countriesList = (ArrayList<Country>) context.getBean("countryList");
	
	public List<Country> getAllCountries() {
		return countriesList;
	}
	
	public void addCountry(Country country) {
		countriesList.add(country);
	}
	
	public boolean updateCountry(Country country) {
		for(Country c: countriesList) {
			if(c.getCode().equalsIgnoreCase(country.getCode()))
			{
				c.setName(country.getName());
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteCountry(String code) {
		Iterator<Country> it = countriesList.iterator();
		while(it.hasNext()) {
			Country country = it.next();
			if(country.getCode().equalsIgnoreCase(code)) {
				it.remove();
			}
		}
		
		return false;
	}
	
	public Country getCountry(String code) {	
		Country found =countriesList.stream()
			.filter(country->country.getCode().equalsIgnoreCase(code))
			.findAny()
			.orElse(null);
		if(found==null)
			throw new CountryNotFoundException();
		return found;
	}
}
