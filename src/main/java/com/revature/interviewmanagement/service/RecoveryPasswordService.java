package com.revature.interviewmanagement.service;

import com.revature.interviewmanagement.model.credentials.RecoveryPasswordDto;

public interface RecoveryPasswordService {

	public void addCode(RecoveryPasswordDto recoveryPasswordDto, String forgotPasswordCode);

	public Boolean validateCode(RecoveryPasswordDto recoverPasswordDto);

}
