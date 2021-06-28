package com.cognizant.springlearn.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Skill {
	@NotNull(message = "Id should not be null")
	@Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Id should be Number")
	private int id;
	@NotBlank
	@NotNull(message = "Name should not be null")
	@Size(min = 3, max = 30, message 
    = "Name must be between 3 and 30 characters")
	private String name;
}
