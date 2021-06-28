package com.cognizant.springlearn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SpringLearnApplication {
	/*
	 * hands on 3:
	 * Include the below static variable in SpringLearnApplication.java
	 * In SpringLearnApplication.java include the following imports:
	 * Include info log on start and end of method. Debug log for displaying the date (refer code below)
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);
	
	
	public static void main(String[] args) throws ParseException {
		SpringApplication.run(SpringLearnApplication.class, args);
		
		SpringLearnApplication springLearnApplication=new SpringLearnApplication();
		
		//hands on 1-4
		springLearnApplication.displayDate();
		//hands on 5
		springLearnApplication.displayCountry();
		//hands on 6
		springLearnApplication.displaycountries();
		
		
	}
	public  void displayDate() throws ParseException {

		LOGGER.info("START");
		
		/*
		 * Hands on 1 and 2:
		 */
		ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
		SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
		String dateString="31/12/2018";
		Date date = format.parse(dateString);
		LOGGER.debug("{}",date);
		
		
		LOGGER.info("END");

		}
	/*
	 * Hands on 4
	 * Spring Core – Load Country from Spring Configuration XML
	 * Create a method displayCountry() in SpringLearnApplication.java, which will read the country bean from spring
	 * configuration file and display the country details.
	 */
	public void displayCountry() {
		/*
		 * Handson 4:
		 */
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

		Country country = (Country) context.getBean("country", Country.class);
		/*
		 * Hands on 5
		 * Spring Core – Demonstration of Singleton Scope and Prototype Scope.
		 */
		Country anotherCountry = context.getBean("country", Country.class);
		LOGGER.debug("Country : {}", country.toString());
	}
	/*
	 * Hands on 6
	 * Spring Core – Load list of countries from Spring Configuration XML
	 */
	public void displaycountries() {
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		List<Country> countriesList = (ArrayList<Country>) context.getBean("countryList");
		LOGGER.debug("List of Countries : {}", countriesList);
	}
}
