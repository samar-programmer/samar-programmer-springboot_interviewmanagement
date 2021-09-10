package com.revature.interviewmanagement.dao;

import java.util.List;

import com.revature.interviewmanagement.entity.Employee;

public interface EmployeeDao {

	public List<Employee> getAllEmployees();

	public Employee getEmployeeById(Long id);

	public Employee getEmployeeByEmailId(String email);

	public Employee getEmployeeByPhoneNumber(String phoneNumber);

	public List<Employee> getEmployeeByDesignationId(Long destId);

	public List<Employee> getEmployeeByEmployeeId(Long empId);

	public String addEmployee(Long id,Employee employee);

	public String updateEmployee(Long id, Employee employee);

	public String deleteEmployee(Long id);

	public List<Employee> getEmployeeByFirstName(String fname);

	public List<Employee> getEmployeeByLastName(String lname);

}
