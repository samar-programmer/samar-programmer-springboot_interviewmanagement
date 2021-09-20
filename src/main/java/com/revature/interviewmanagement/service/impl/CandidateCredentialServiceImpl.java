package com.revature.interviewmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.dao.CandidateCredentialDao;
import com.revature.interviewmanagement.entity.credentials.CandidateCredential;
import com.revature.interviewmanagement.model.credentials.CandidateCredentialDto;
import com.revature.interviewmanagement.service.CandidateCredentialService;

@Service
public class CandidateCredentialServiceImpl implements CandidateCredentialService {

	@Autowired
	private CandidateCredentialDao candidateCredentialDao ;
	
	@Override
	public String updatePassword(Long id, CandidateCredentialDto candidateCredential) {
		
		return candidateCredentialDao.updatePassword(id,candidateCredential);
	}

	@Override
	public String addCredential(CandidateCredentialDto candidateCredential) {
		
		return candidateCredentialDao.addCredential(candidateCredential);
	}

	@Override
	public CandidateCredential validateCredential(CandidateCredentialDto candidateCredential) {
		
		return candidateCredentialDao.validateCredential(candidateCredential);
	}


	@Override
	public Boolean validateEmail(String email) {
		
		return candidateCredentialDao.validateEmail(email);
	}

	@Override
	public Boolean resetPassword(CandidateCredentialDto candidateCredentialDto) {
		
		return candidateCredentialDao.resetPassword(candidateCredentialDto);
	}

}
