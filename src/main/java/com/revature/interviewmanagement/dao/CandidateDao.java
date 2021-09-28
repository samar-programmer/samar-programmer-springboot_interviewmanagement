package com.revature.interviewmanagement.dao;

import java.util.List;

import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.model.CandidateDto;

public interface CandidateDao {

	public String addCandidate(CandidateDto candidate);

	public String updateCandidate(CandidateDto candidate);

	public String deleteCandidate(Long id);

	public List<Candidate> getCandidateByPhoneNumber(String phoneNumber);

	public List<Candidate> getCandidateByExperience(Integer exp);

	public List<Candidate> getCandidateByRole(String role);

	public List<Candidate> getCandidateByEmailId(String email);

	public Candidate getCandidateById(Long id);

	public List<Candidate> getAllCandidate();

	public List<Candidate> getCandidateByName(String name);

}
