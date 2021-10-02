package com.revature.interviewmanagement.service;

import java.util.List;

import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.model.EmployeeDto;

public interface EmployeeService {

	String deleteEmployee(Long id);

	String updateEmployee(EmployeeDto employeeDto);

	String addEmployee(EmployeeDto employeeDto);

	List<Employee> getEmployeeByDesignation(String designation);

	Employee getEmployeeByPhoneNumber(EmployeeDto employeeDto);

	Employee getEmployeeByEmailId(EmployeeDto employeeDto);

	Employee getEmployeeById(Long id);

	List<Employee> getAllEmployee();

	List<Employee> getEmployeeByName(String name);

	List<Employee> getEmployeeByStatus(String status);

	List<?> getAllDesignation();

}
