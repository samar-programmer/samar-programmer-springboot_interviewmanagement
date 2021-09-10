package com.revature.interviewmanagement.dao;

import com.revature.interviewmanagement.entity.credentials.CandidateCredential;

public interface CandidateCredentialDao {

	public CandidateCredential getCredentialById(Long id);

	public Boolean validateCredential(CandidateCredential candidateCredential);

	public String addCredential(CandidateCredential candidateCredential);

	public String updateCredential(Long id, CandidateCredential candidateCredential);

}
