package com.revature.interviewmanagement.dao;

import java.util.List;

import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.model.CandidateDto;

public interface CandidateDao {

	String addCandidate(Candidate candidate);

	String updateCandidate(Candidate candidate);

	String deleteCandidate(Long id);

	List<Candidate> getCandidateByPhoneNumber(String phoneNumber);

	List<Candidate> getCandidateByExperience(String exp);

	List<Candidate> getCandidateByRole(String role);

	List<Candidate> getCandidateByEmailId(String email);

	Candidate getCandidateById(Long id);

	List<Candidate> getAllCandidate();

	List<Candidate> getCandidateByName(String name);

	List<?> getAllExperience();

	List<?> getAllJobRole();

	Candidate validateJobRole(CandidateDto candidateDto);

}
