package com.revature.interviewmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.dao.CandidateDao;
import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {
	
	@Autowired
	private CandidateDao candidateDao;
	
	@Override
	public List<Candidate> getAllCandidate(){
		
		return candidateDao.getAllCandidate();
	}

	@Override
	public Candidate getCandidateById(Long id) {
		
		return candidateDao.getCandidateById(id);
	}

	@Override
	public Candidate getCandidateByEmailId(String email) {
		
		return candidateDao.getCandidateByEmailId(email);
	}

	@Override
	public List<Candidate> getCandidateByRole(String role) {
		
		return candidateDao.getCandidateByRole(role) ;
	}

	@Override
	public List<Candidate> getCandidateByExperience(Integer exp) {
		
		return candidateDao.getCandidateByExperience(exp);
	}

	@Override
	public List<Candidate> getCandidateByFirstName(String fname) {
		
		return candidateDao.getCandidateByFirstName(fname) ;
	}

	@Override
	public Candidate getCandidateByPhoneNumber(String phoneNumber) {
		
		return candidateDao.getCandidateByPhoneNumber(phoneNumber);
	}

	@Override
	public String deleteCandidate(Long id) {
		
		return candidateDao.deleteCandidate(id) ;
	}

	@Override
	public String updateCandidate(Long id,Candidate candidate) {
		
		return candidateDao.updateCandidate(id,candidate);
	}

	@Override
	public String addCandidate(Candidate candidate) {
		
		return candidateDao.addCandidate(candidate);
	}


	@Override
	public List<Candidate> getCandidateByLastName(String lname) {
		return candidateDao.getCandidateByLastName(lname) ;
	}
}
