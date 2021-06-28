package com.cognizant.springlearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.springlearn.dao.DepartmentDao;
import com.cognizant.springlearn.model.Department;



@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Transactional
	public List<Department> getAllDepartments(){
		
		return departmentDao.getAllDepartments();
	}

}
