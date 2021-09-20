package com.revature.interviewmanagement.dao;

import java.util.List;

import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.model.EmployeeDto;

public interface EmployeeDao {

	public List<Employee> getAllEmployees();

	public Employee getEmployeeById(Long id);

	public Employee getEmployeeByEmailId(String email);

	public Employee getEmployeeByPhoneNumber(String phoneNumber);

	public List<Employee> getEmployeeByDesignationId(Long destId);

	public List<Employee> getEmployeeByEmployeeId(Long empId);

	public String addEmployee(Long id,EmployeeDto employee);

	public String updateEmployee(Long id, EmployeeDto employee);

	public String deleteEmployee(Long id);

	public List<Employee> getEmployeeByName(String name);


}
