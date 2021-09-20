package com.revature.interviewmanagement.util.mapper;

import com.revature.interviewmanagement.entity.credentials.RecoveryPassword;
import com.revature.interviewmanagement.model.credentials.RecoveryPasswordDto;

public class RecoveryPasswordMapper {
	
	private RecoveryPasswordMapper() {
		throw new IllegalStateException("Utility class");

	}
	
	public static RecoveryPassword recoveryPasswordMapper(RecoveryPasswordDto recoveryPasswordDto) {
		RecoveryPassword recoverypassword=new RecoveryPassword();
		
		recoverypassword.setId(recoveryPasswordDto.getId());
		recoverypassword.setEmailId(recoveryPasswordDto.getCode());
		recoverypassword.setCandidateCredential(recoveryPasswordDto.getCandidateCredential());
		recoverypassword.setCode(recoveryPasswordDto.getEmailId());
		recoverypassword.setAddedOn(recoveryPasswordDto.getAddedOn());
		recoverypassword.setUpdatedOn(recoveryPasswordDto.getUpdatedOn());
		recoverypassword.setUpdatedBy(recoveryPasswordDto.getUpdatedBy());
		
		return recoverypassword;
	}
}
