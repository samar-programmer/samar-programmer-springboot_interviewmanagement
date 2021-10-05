package com.revature.interviewmanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.model.InterviewDto;

public interface InterviewService {

	List<Interview> getAllInterview();

	List<?> getAllInterviewType();

	Interview getInterviewById(Long id);

	List<Interview> getInterviewByScheduledDate(LocalDate scheduledDate);

	List<Interview> getInterviewByCandidateId(Long canId);

	List<Interview> getInterviewByCandidateName(String name);

	List<Interview> getInterviewByCandidatePhone(CandidateDto candidateDto);

	List<Interview> getInterviewByCandidateEmailId(CandidateDto candidateDto);

	List<Interview> getInterviewByCandidateRole(String role);

	List<Interview> getInterviewByCandidateExperience(String exp);

	List<Interview> getInterviewByEmployeeId(Long id);

	List<Interview> getInterviewByEmployeeDesignation(String designation);

	List<Interview> getInterviewByEmployeeName(String name);

	List<Interview> getInterviewByEmployeePhone(EmployeeDto employeeDto);

	List<Interview> getInterviewByEmployeeEmailId(EmployeeDto employeeDto);

	String deleteInterview(Long id);

	String updateInterview(InterviewDto interview);

	String addInterview(InterviewDto interview, Long canId, Long empId);

	List<Interview> getInterviewByType(String type);

	String sendScheduledInterviewMail(Long canId, Long empId, InterviewDto interviewDto);

	String sendRescheduledInterviewMail(Long canId, Long empId, InterviewDto interviewDto);

	List<Interview> getInterviewByEmployeeStatus(String status);

}
