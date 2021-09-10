package com.revature.interviewmanagement.service;

import com.revature.interviewmanagement.entity.credentials.EmployeeCredential;

public interface EmployeeCredentialService {

	public String updateCredential(Long id, EmployeeCredential employeeCredential);

	public String addCredential(EmployeeCredential employeeCredential);

	public Boolean validateCredential(EmployeeCredential employeeCredential);

	public EmployeeCredential getCredentialById(Long id);

}
