package com.revature.interviewmanagement.service;

import com.revature.interviewmanagement.entity.credentials.CandidateCredential;
import com.revature.interviewmanagement.model.credentials.CandidateCredentialDto;

public interface CandidateCredentialService {

	public String updatePassword(Long id, CandidateCredentialDto candidateCredential);

	public String addCredential(CandidateCredentialDto candidateCredential);

	public CandidateCredential validateCredential(CandidateCredentialDto candidateCredential);

	public Boolean validateEmail(String email);

	public Boolean resetPassword(CandidateCredentialDto candidateCredentialDto);

}
