package com.revature.interviewmanagement.dao;

import com.revature.interviewmanagement.entity.credentials.CandidateCredential;
import com.revature.interviewmanagement.model.credentials.CandidateCredentialDto;

public interface CandidateCredentialDao {

	public CandidateCredential validateCredential(CandidateCredentialDto candidateCredential);

	public String addCredential(CandidateCredentialDto candidateCredential);

	public String updatePassword(Long id, CandidateCredentialDto candidateCredential);

	public Boolean validateEmail(String email);

	public Boolean resetPassword(CandidateCredentialDto candidateCredentialDto);

}
