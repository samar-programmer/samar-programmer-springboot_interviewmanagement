package com.revature.interviewmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.model.credentials.EmployeeCredentialDto;
import com.revature.interviewmanagement.service.EmployeeCredentialService;
import com.revature.interviewmanagement.dao.EmployeeCredentialDao;

@Service
public class EmployeeCredentialServiceImpl implements EmployeeCredentialService {

	@Autowired
	private EmployeeCredentialDao employeeCredentialDao;
	
	@Override
	public String updatePassword(Long id, EmployeeCredentialDto employeeCredential) {
		
		return employeeCredentialDao.updatePassword(id,employeeCredential);
	}

	@Override
	public String addCredential(EmployeeCredentialDto employeeCredential) {
		
		return employeeCredentialDao.addCredential(employeeCredential);
	}

	@Override
	public Boolean validateCredential(EmployeeCredentialDto employeeCredential) {
		
		return employeeCredentialDao.validateCredential(employeeCredential);
	}

	@Override
	public Boolean validateEmail(String email) {
	
		return employeeCredentialDao.validateEmail(email);
	}

}
