package com.revature.interviewmanagement.service;

import java.util.List;

import com.revature.interviewmanagement.entity.Candidate;

public interface CandidateService {
	public List<Candidate> getAllCandidate();

	public Candidate getCandidateById(Long id);

	public Candidate getCandidateByEmailId(String email);

	public List<Candidate> getCandidateByRole(String role);

	public List<Candidate> getCandidateByExperience(Integer exp);

	public Candidate getCandidateByPhoneNumber(String phoneNumber);

	public String deleteCandidate(Long id);

	public String updateCandidate(Long id,Candidate candidate);

	public String addCandidate(Long id,Candidate candidate);

	public List<Candidate> getCandidateByFirstName(String fname);

	public List<Candidate> getCandidateByLastName(String lname);
}
