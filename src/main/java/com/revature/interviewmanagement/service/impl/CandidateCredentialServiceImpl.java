package com.revature.interviewmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.dao.CandidateCredentialDao;
import com.revature.interviewmanagement.entity.credentials.CandidateCredential;
import com.revature.interviewmanagement.service.CandidateCredentialService;

@Service
public class CandidateCredentialServiceImpl implements CandidateCredentialService {

	@Autowired
	private CandidateCredentialDao candidateCredentialDao ;
	
	@Override
	public String updateCredential(Long id, CandidateCredential candidateCredential) {
		
		return candidateCredentialDao.updateCredential(id,candidateCredential);
	}

	@Override
	public String addCredential(CandidateCredential candidateCredential) {
		
		return candidateCredentialDao.addCredential(candidateCredential);
	}

	@Override
	public Boolean validateCredential(CandidateCredential candidateCredential) {
		
		return candidateCredentialDao.validateCredential(candidateCredential);
	}

	@Override
	public CandidateCredential getCredentialById(Long id) {
		
		return candidateCredentialDao.getCredentialById(id);
	}

}
