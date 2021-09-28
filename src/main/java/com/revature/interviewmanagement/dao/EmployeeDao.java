package com.revature.interviewmanagement.dao;

import java.util.List;

import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.model.EmployeeDto;

public interface EmployeeDao {

	 List<Employee> getAllEmployee();

	 Employee getEmployeeById(Long id);

	 Employee getEmployeeByEmailId(String email);

	 Employee getEmployeeByPhoneNumber(String phoneNumber);

	 Employee getEmployeeByDesignationId(Long destId);

	 Employee getEmployeeByEmployeeId(Long empId);

	 String addEmployee(EmployeeDto employee);

	 String updateEmployee(EmployeeDto employee);

	 String deleteEmployee(Long id);

	 List<Employee> getEmployeeByName(String name);
	 
	 List<Boolean> checkState(EmployeeDto employee,Integer statusCode, Long id);


}
