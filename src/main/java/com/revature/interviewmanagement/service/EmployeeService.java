package com.revature.interviewmanagement.service;

import java.util.List;

import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.model.EmployeeDto;

public interface EmployeeService {

	public String deleteEmployee(Long id);

	public String updateEmployee(Long id, EmployeeDto employee);

	public String addEmployee(Long id,EmployeeDto employee);

	public List<Employee> getEmployeeByEmployeeId(Long empId);

	public List<Employee> getEmployeeByDesignationId(Long destId);

	public Employee getEmployeeByPhoneNumber(String phoneNumber);

	public Employee getEmployeeByEmailId(String email);

	public Employee getEmployeeById(Long id);

	public List<Employee> getAllEmployees();

	public List<Employee> getEmployeeByName(String name);
	

}
