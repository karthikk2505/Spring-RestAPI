package com.cognizant.springlearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.model.Employee;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional
	public List<Employee> getAllEmployees(){
		
		return employeeDao.getAllEmployees();
	}
	@Transactional
	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}
	
	@Transactional
	public void deleteEmployee(Long id) {
		employeeDao.deleteEmployee(id);
	}

}
