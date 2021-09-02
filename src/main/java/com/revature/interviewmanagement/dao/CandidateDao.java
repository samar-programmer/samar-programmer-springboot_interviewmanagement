package com.revature.interviewmanagement.dao;

import java.util.List;

import com.revature.interviewmanagement.entity.Candidate;

public interface CandidateDao {

	public String addCandidate(Candidate candidate);

	public String updateCandidate(Long id, Candidate candidate);

	public String deleteCandidate(Long id);

	public Candidate getCandidateByPhoneNumber(String phoneNumber);

	public List<Candidate> getCandidateByName(String name);

	public List<Candidate> getCandidateByExperience(Integer exp);

	public List<Candidate> getCandidateByRole(String role);

	public Candidate getCandidateByEmailId(String email);

	public Candidate getCandidateById(Long id);

	public List<Candidate> getAllCandidate();

}
