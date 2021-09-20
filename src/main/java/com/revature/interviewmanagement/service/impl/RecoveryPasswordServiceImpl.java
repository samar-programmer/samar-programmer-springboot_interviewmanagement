package com.revature.interviewmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.dao.RecoveryPasswordDao;
import com.revature.interviewmanagement.model.credentials.RecoveryPasswordDto;
import com.revature.interviewmanagement.service.RecoveryPasswordService;

@Service
public class RecoveryPasswordServiceImpl implements RecoveryPasswordService {
	
	@Autowired
	private RecoveryPasswordDao recoveryPasswordDao;
	
	@Override
	public void addCode(RecoveryPasswordDto recoverPassword, String forgotPasswordCode) {
		recoveryPasswordDao.addCode(recoverPassword,forgotPasswordCode);
		
	}

	@Override
	public Boolean validateCode(RecoveryPasswordDto recoverPassword) {
		return recoveryPasswordDao.validateCode(recoverPassword);
		
	}


}
