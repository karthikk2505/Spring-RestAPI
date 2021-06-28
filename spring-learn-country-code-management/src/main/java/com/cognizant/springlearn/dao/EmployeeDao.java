package com.cognizant.springlearn.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;


@Component
public class EmployeeDao {
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
	private static List<Employee> EMPLOYEE_LIST = (ArrayList<Employee>) context.getBean("empList");
	public List<Employee> getAllEmployees(){
		return EMPLOYEE_LIST;
	}
	public void updateEmployee(Employee employee) {
		Optional<Employee> optional =  EMPLOYEE_LIST.stream().filter(emp -> emp.getId()==employee.getId()).findAny();
		if(optional.isPresent())
		{
			Employee emp = optional.get();
			emp.setName(employee.getName());
			emp.setPermanent(employee.isPermanent());
			emp.setSalary(employee.getSalary());
			emp.setDepartment(employee.getDepartment());
			emp.setSkills(employee.getSkills());
		}
		else
		{
			throw new EmployeeNotFoundException();
		}
	}
	public void deleteEmployee(Long id) {
		Optional<Employee> optional =  EMPLOYEE_LIST.stream().filter(emp -> emp.getId()==id).findAny();
		if(optional.isPresent()) 
		{ 
			Employee emp = optional.get();
			EMPLOYEE_LIST.remove(emp);
		}
		else
		{
			throw new EmployeeNotFoundException();
		}
	}
}
