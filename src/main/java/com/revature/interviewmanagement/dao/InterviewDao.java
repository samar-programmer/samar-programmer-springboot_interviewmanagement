package com.revature.interviewmanagement.dao;

import java.time.LocalDate;
import java.util.List;

import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.model.InterviewDto;

public interface InterviewDao {

	 List<Interview> getAllInterview();

	 Interview getInterviewById(Long id);

	 List<Interview> getInterviewByScheduledDate(LocalDate scheduledDate);

	 List<Interview> getInterviewByCandidateId(Long canId);

	 List<Interview> getInterviewByCandidateName(String name);

	 List<Interview> getInterviewByCandidatePhone(String phone);

	 List<Interview> getInterviewByCandidateEmailId(String email);

	 List<Interview> getInterviewByCandidateRole(String role);

	 List<Interview> getInterviewByCandidateExperience(Integer exp);
	
	 List<Interview> getInterviewByEmpId(Long empId);

	 List<Interview> getInterviewByEmployeeId(Long employeeId);

	 List<Interview> getInterviewByDesignationId(Long destId);

	 List<Interview> getInterviewByEmployeeName(String name);

	 List<Interview> getInterviewByEmployeePhone(String phone);

	 List<Interview> getInterviewByEmployeeEmailId(String email);

	 String deleteInterview(Long id);

	 String updateInterview(InterviewDto interview);

	 String addInterview(InterviewDto interview,Long canId,Long empId);

	 List<Interview> getInterviewByType(String type);

}
