package com.revature.interviewmanagement.util.mapper;

import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.model.EmployeeDto;

public class EmployeeMapper {
	
	private EmployeeMapper() {
		throw new IllegalStateException("Utility class");

	}
	
	public static Employee employeeEntityMapper(EmployeeDto employeeDto) {
		Employee employeeEntity=new Employee();
		
		employeeEntity.setId(employeeDto.getId());
		employeeEntity.setFirstName(employeeDto.getFirstName());
		employeeEntity.setLastName(employeeDto.getLastName());
		employeeEntity.setEmailId(employeeDto.getEmailId());
		employeeEntity.setDesignation(employeeDto.getDesignation());
		employeeEntity.setStatus(employeeDto.getStatus());
		employeeEntity.setPhoneNumber(employeeDto.getPhoneNumber());
		employeeEntity.setAddedOn(employeeDto.getAddedOn());
		employeeEntity.setUpdatedOn(employeeDto.getUpdatedOn());
		employeeEntity.setUpdatedBy(employeeDto.getUpdatedBy());
		employeeEntity.setInterview(employeeDto.getInterview());
		
		
		return employeeEntity;
	}

}
