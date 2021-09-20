package com.revature.interviewmanagement.util.mapper;

import com.revature.interviewmanagement.entity.credentials.CandidateCredential;
import com.revature.interviewmanagement.model.credentials.CandidateCredentialDto;

public class CandidateCredentialMapper {
	
	private CandidateCredentialMapper() {
		throw new IllegalStateException("Utility class");

	}
	
	public static CandidateCredential candidateCredentialMapper(CandidateCredentialDto candidateCredentialDto) {
		CandidateCredential candidateCredential=new CandidateCredential();
		
		candidateCredential.setId(candidateCredentialDto.getId());
		candidateCredential.setEmailId(candidateCredentialDto.getEmailId());
		candidateCredential.setPassword(candidateCredentialDto.getPassword());
		candidateCredential.setSalt(candidateCredentialDto.getSalt());
		candidateCredential.setCandidate(candidateCredentialDto.getCandidate());
		candidateCredential.setRecoveryPassword(candidateCredentialDto.getRecoveryPassword());
		candidateCredential.setAddedOn(candidateCredentialDto.getAddedOn());
		candidateCredential.setUpdatedOn(candidateCredentialDto.getUpdatedOn());
		candidateCredential.setUpdatedBy(candidateCredentialDto.getUpdatedBy());
		
		return candidateCredential;
	}

}
