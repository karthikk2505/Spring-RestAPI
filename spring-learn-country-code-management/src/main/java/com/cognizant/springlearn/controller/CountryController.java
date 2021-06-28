package com.cognizant.springlearn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@RestController//(combination of @Controller and @Response body) 
@RequestMapping("/countries")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("")
	public List<Country> getAllCountries()
	{
		return countryService.getAllCountries();
	}
	/**
	 * 
	 *
	 */
	@GetMapping("/{code}")
	public EntityModel<Country> getCountryByCode(@PathVariable("code") String code) {
		
		Country country = countryService.getCountry(code);
		if(country==null)
		{
			throw new CountryNotFoundException();
		}
		/*
		 * this is to make available of the links within the controller with the json which is passed to the client.
		 * {
		 * 	_links:{
		 * 		all-countries:{
		 * 			href :the link for getAllCountries();
		 * 	}
		 * } 
		 */
		EntityModel<Country> model = EntityModel.of(country);
		
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getAllCountries());
		model.add(linkToUsers.withRel("all-countries"));
		return model;
	}
	@PostMapping("")
	public Country addCountry(@Valid @RequestBody Country country) {
		// Create validator factory
		/*
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		Validator validator = factory.getValidator();

		// Validation is done against the annotations defined in country bean

		Set<ConstraintViolation<Country>> violations = validator.validate(country);

		List<String> errors = new ArrayList<String>();

		// Accumulate all errors in an ArrayList of type String

		for (ConstraintViolation<Country> violation : violations) {

		errors.add(violation.getMessage());

		}

		// Throw exception so that the user of this web service receives appropriate error message

		if (violations.size() > 0) {

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());

		}*/
		countryService.addCountry(country);
		return country;
	}

}
