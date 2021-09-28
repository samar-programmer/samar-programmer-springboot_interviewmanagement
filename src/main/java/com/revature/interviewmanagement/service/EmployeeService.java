package com.revature.interviewmanagement.service;

import java.util.List;

import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.model.EmployeeDto;

public interface EmployeeService {

	 String deleteEmployee(Long id);

	 String updateEmployee(EmployeeDto employeeDto);

	 String addEmployee(EmployeeDto employeeDto);

	 Employee getEmployeeByEmployeeId(EmployeeDto employeeDto);

	 Employee getEmployeeByDesignationId(EmployeeDto employeeDto);

	 Employee getEmployeeByPhoneNumber(EmployeeDto employeeDto);

	 Employee getEmployeeByEmailId(EmployeeDto employeeDto);

	 Employee getEmployeeById(Long id);

	 List<Employee> getAllEmployee();

	 List<Employee> getEmployeeByName(String name);
	

}
