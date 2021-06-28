package com.cognizant.springlearn.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cognizant.springlearn.model.Department;



@Component
public class DepartmentDao {
	
	private List<Department> DEPARTMENT_LIST;
	
	public DepartmentDao() {
		ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
		DEPARTMENT_LIST = (ArrayList<Department>) context.getBean("deptList");
	}
	public List<Department> getAllDepartments(){
		return DEPARTMENT_LIST;
	}
}
