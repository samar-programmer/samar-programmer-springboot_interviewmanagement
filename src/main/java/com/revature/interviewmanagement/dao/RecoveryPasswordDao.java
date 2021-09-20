package com.revature.interviewmanagement.dao;

import com.revature.interviewmanagement.model.credentials.RecoveryPasswordDto;

public interface RecoveryPasswordDao {

	public void addCode(RecoveryPasswordDto recoverPassword, String forgotPasswordCode);

	public Boolean validateCode(RecoveryPasswordDto recoverPassword);

}
