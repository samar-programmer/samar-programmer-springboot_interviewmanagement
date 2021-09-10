package com.revature.interviewmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.dao.EmployeeDao;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public String deleteEmployee(Long id) {
		
		return employeeDao.deleteEmployee(id);
	}

	@Override
	public String updateEmployee(Long id, Employee employee) {
		
		return employeeDao.updateEmployee(id,employee);
	}

	@Override
	public String addEmployee(Long id,Employee employee) {
		
		return employeeDao.addEmployee(id,employee);
	}

	@Override
	public List<Employee> getEmployeeByEmployeeId(Long empId) {
	
		return employeeDao.getEmployeeByEmployeeId(empId);
	}

	@Override
	public List<Employee> getEmployeeByDesignationId(Long destId) {
		
		return employeeDao.getEmployeeByDesignationId(destId);
	}

	

	@Override
	public Employee getEmployeeByPhoneNumber(String phoneNumber) {
		
		return employeeDao.getEmployeeByPhoneNumber(phoneNumber);
	}

	@Override
	public Employee getEmployeeByEmailId(String email) {
		
		return employeeDao.getEmployeeByEmailId(email);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		
		return employeeDao.getEmployeeById(id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeDao.getAllEmployees();
	}

	@Override
	public List<Employee> getEmployeeByLastName(String lname) {
		
		return employeeDao.getEmployeeByLastName(lname);
	}

	@Override
	public List<Employee> getEmployeeByFirstName(String fname) {
		
		 return employeeDao.getEmployeeByFirstName(fname);
	}

}
