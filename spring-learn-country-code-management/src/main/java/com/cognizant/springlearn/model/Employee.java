package com.cognizant.springlearn.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Employee {
	@NotNull(message = "Id should not be null")
	@Min(value=0, message="Id should be Number")
	private long id;
	@NotBlank
	@NotNull(message = "Name should not be null")
	@Size(min = 3, max = 30, message 
    = "Name must be between 3 and 30 characters")
	private String name;
	@NotNull(message = "Salary should not be null")
	@Min(value=0,message = "Salary muct be positive")
	private long salary;
	@NotNull(message="Permanent should not be null")
	private boolean permanent;
	private List<Skill> skills;
	private Department department;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date dateOfBirth;
}
