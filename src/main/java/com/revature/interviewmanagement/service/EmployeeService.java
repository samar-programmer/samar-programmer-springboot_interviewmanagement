package com.revature.interviewmanagement.service;

import java.util.List;

import com.revature.interviewmanagement.entity.Employee;

public interface EmployeeService {

	public String deleteEmployee(Long id);

	public String updateEmployee(Long id, Employee employee);

	public String addEmployee(Employee employee);

	public List<Employee> getEmployeeByEmployeeId(Long empId);

	public List<Employee> getEmployeeByDesignationId(Long destId);

	public Employee getEmployeeByPhoneNumber(String phoneNumber);

	public Employee getEmployeeByEmailId(String email);

	public Employee getEmployeeById(Long id);

	public List<Employee> getAllEmployees();

	public List<Employee> getEmployeeByLastName(String lname);

	public List<Employee> getEmployeeByFirstName(String fname);

}
