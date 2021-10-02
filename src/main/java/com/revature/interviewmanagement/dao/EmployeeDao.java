package com.revature.interviewmanagement.dao;

import java.util.List;

import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.model.EmployeeDto;

public interface EmployeeDao {

	List<Employee> getAllEmployee();

	Employee getEmployeeById(Long id);

	Employee getEmployeeByEmailId(String email);

	Employee getEmployeeByPhoneNumber(String phoneNumber);

	List<Employee> getEmployeeByDesignation(String designation);

	String addEmployee(Employee employee);

	String updateEmployee(Employee employee);

	String deleteEmployee(Long id);

	List<Employee> getEmployeeByName(String name);

	List<Boolean> checkState(EmployeeDto employee, Integer statusCode, Long id);

	List<Employee> getEmployeeByStatus(String status);

	List<?> getAllDesignation();

}
