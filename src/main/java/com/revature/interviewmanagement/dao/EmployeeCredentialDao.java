package com.revature.interviewmanagement.dao;

import com.revature.interviewmanagement.model.credentials.EmployeeCredentialDto;

public interface EmployeeCredentialDao {

	public String updatePassword(Long id, EmployeeCredentialDto employeeCredential);

	public String addCredential(EmployeeCredentialDto employeeCredential);

	public Boolean validateCredential(EmployeeCredentialDto employeeCredential);

	public Boolean validateEmail(String email);

}
