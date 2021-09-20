package com.revature.interviewmanagement.util.mapper;

import com.revature.interviewmanagement.entity.credentials.EmployeeCredential;
import com.revature.interviewmanagement.model.credentials.EmployeeCredentialDto;

public class EmployeeCredentialMapper {
	
	private EmployeeCredentialMapper() {
		throw new IllegalStateException("Utility class");

	}
	
	public static EmployeeCredential candidateCredentialMapper(EmployeeCredentialDto employeeCredentialDto) {
		EmployeeCredential employeeCredential=new EmployeeCredential();
		
		employeeCredential.setId(employeeCredentialDto.getId());
		employeeCredential.setEmailId(employeeCredentialDto.getEmailId());
		employeeCredential.setPassword(employeeCredentialDto.getPassword());
		employeeCredential.setEmployee(employeeCredentialDto.getEmployee());
		employeeCredential.setAddedOn(employeeCredentialDto.getAddedOn());
		employeeCredential.setUpdatedOn(employeeCredentialDto.getUpdatedOn());
		employeeCredential.setUpdatedBy(employeeCredentialDto.getUpdatedBy());
		
		return employeeCredential;
	}
}
