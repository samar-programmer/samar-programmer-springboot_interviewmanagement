package com.revature.interviewmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.entity.credentials.EmployeeCredential;
import com.revature.interviewmanagement.service.EmployeeCredentialService;
import com.revature.interviewmanagement.dao.EmployeeCredentialDao;

@Service
public class EmployeeCredentialServiceImpl implements EmployeeCredentialService {

	@Autowired
	private EmployeeCredentialDao employeeCredentialDao;
	
	@Override
	public String updateCredential(Long id, EmployeeCredential employeeCredential) {
		
		return employeeCredentialDao.updateCredential(id,employeeCredential);
	}

	@Override
	public String addCredential(EmployeeCredential employeeCredential) {
		
		return employeeCredentialDao.addCredential(employeeCredential);
	}

	@Override
	public Boolean validateCredential(EmployeeCredential employeeCredential) {
		
		return employeeCredentialDao.validateCredential(employeeCredential);
	}

	@Override
	public EmployeeCredential getCredentialById(Long id) {
		
		return employeeCredentialDao.getCredentialById(id);
	}

}
