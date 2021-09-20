package com.revature.interviewmanagement.service;

import com.revature.interviewmanagement.model.credentials.EmployeeCredentialDto;

public interface EmployeeCredentialService {

	public String updatePassword(Long id, EmployeeCredentialDto employeeCredential);

	public String addCredential(EmployeeCredentialDto employeeCredential);

	public Boolean validateCredential(EmployeeCredentialDto employeeCredential);

	public Boolean validateEmail(String email);

}
