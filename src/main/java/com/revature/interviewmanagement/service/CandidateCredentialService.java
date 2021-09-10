package com.revature.interviewmanagement.service;

import com.revature.interviewmanagement.entity.credentials.CandidateCredential;

public interface CandidateCredentialService {

	public String updateCredential(Long id, CandidateCredential candidateCredential);

	public String addCredential(CandidateCredential candidateCredential);

	public Boolean validateCredential(CandidateCredential candidateCredential);

	public CandidateCredential getCredentialById(Long id);

}
