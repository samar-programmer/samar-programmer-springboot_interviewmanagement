package com.revature.interviewmanagement.util.mapper;

import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.model.CandidateDto;

public class CandidateMapper {
	
	private CandidateMapper() {
		throw new IllegalStateException("Utility class");

	}
	
	public static Candidate candidateEntityMapper(CandidateDto candidateDto) {
		Candidate candidateEntity=new Candidate();
		candidateEntity.setId(candidateDto.getId());
		candidateEntity.setFirstName(candidateDto.getFirstName());
		candidateEntity.setLastName(candidateDto.getLastName());
		candidateEntity.setEmailId(candidateDto.getEmailId());
		candidateEntity.setPhoneNumber(candidateDto.getPhoneNumber());
		candidateEntity.setResumeLink(candidateDto.getResumeLink());
		candidateEntity.setExperience(candidateDto.getExperience());
		candidateEntity.setJobRole(candidateDto.getJobRole());
		candidateEntity.setAddedOn(candidateDto.getAddedOn());
		candidateEntity.setUpdatedOn(candidateDto.getUpdatedOn());
		candidateEntity.setUpdatedBy(candidateDto.getUpdatedBy());
		candidateEntity.setInterview(candidateDto.getInterview());
		
		
		return candidateEntity;
	}

}
