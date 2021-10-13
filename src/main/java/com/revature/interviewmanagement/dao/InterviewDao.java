package com.revature.interviewmanagement.dao;

import java.time.LocalDate;
import java.util.List;

import com.revature.interviewmanagement.entity.Interview;

public interface InterviewDao {

	List<Interview> getAllInterview();

	Interview getInterviewById(Long id);

	List<Interview> getInterviewByScheduledDate(LocalDate scheduledDate);

	List<Interview> getInterviewByCandidateId(Long canId);

	List<Interview> getInterviewByCandidateName(String name);

	List<Interview> getInterviewByCandidatePhone(String phone);

	List<Interview> getInterviewByCandidateEmailId(String email);

	List<Interview> getInterviewByCandidateRole(String role);

	List<Interview> getInterviewByCandidateExperience(String exp);

	List<Interview> getInterviewByEmployeeId(Long employeeId);

	List<Interview> getInterviewByEmployeeName(String name);

	List<Interview> getInterviewByEmployeePhone(String phone);

	List<Interview> getInterviewByEmployeeEmailId(String email);

	List<Interview> getInterviewByEmployeeDesignation(String designation);

	List<Interview> getInterviewByEmployeeStatus(String status);

	String deleteInterview(Long id);

	String updateInterview(Interview interview);

	String addInterview(Interview interview, Long canId, Long empId);

	List<Interview> getInterviewByType(String type);

	List<?> getAllInterviewType();

	boolean isCandidateHasLiveInterview(Long canId);

	Long isCandidateHasLiveInterviewForUpdate(Long canId);

}
