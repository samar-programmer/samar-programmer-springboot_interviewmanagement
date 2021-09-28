package com.revature.interviewmanagement.util.mapper;

import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.model.InterviewDto;

public class InterviewMapper {
	private InterviewMapper() {
		throw new IllegalStateException("Utility class");

	}
	
	public static Interview interviewEntityMapper(InterviewDto interviewDto) {
		Interview interviewEntity=new Interview();
		
		interviewEntity.setId(interviewDto.getId());
		interviewEntity.setCallScheduledDate(interviewDto.getCallScheduledDate());
		interviewEntity.setCallScheduledTime(interviewDto.getCallScheduledTime());
		interviewEntity.setInterviewType(interviewDto.getInterviewType());
		interviewEntity.setStatus(interviewDto.getStatus());
		interviewEntity.setCandidate(interviewDto.getCandidate());
		interviewEntity.setEmployee(interviewDto.getEmployee());
		interviewEntity.setResult(interviewDto.getResult());
		interviewEntity.setAddedOn(interviewDto.getAddedOn());
		interviewEntity.setUpdatedOn(interviewDto.getUpdatedOn());
		interviewEntity.setUpdatedBy(interviewDto.getUpdatedBy());
		
		
		return interviewEntity;
	}
}
