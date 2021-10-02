package com.revature.interviewmanagement.service;

import java.util.List;

import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.model.CandidateDto;

public interface CandidateService {

	List<Candidate> getAllCandidate();

	Candidate getCandidateById(Long id);

	List<Candidate> getCandidateByEmailId(CandidateDto candidateDto);

	List<Candidate> getCandidateByRole(String role);

	List<Candidate> getCandidateByExperience(String exp);

	List<Candidate> getCandidateByPhoneNumber(CandidateDto candidateDto);

	String deleteCandidate(Long id);

	String updateCandidate(CandidateDto candidateDto);

	String addCandidate(CandidateDto candidateDto);

	List<Candidate> getCandidateByName(String name);

	List<?> getAllExperience();

	List<?> getAllJobRole();

	Boolean validateJobRole(CandidateDto candidateDto);

}
