package com.revature.interviewmanagement.dao;

import java.time.LocalDate;
import java.util.List;

import com.revature.interviewmanagement.entity.Interview;

public interface InterviewDao {

	public List<Interview> getAllInterview();

	public Interview getInterviewById(Long id);

	public List<Interview> getInterviewByScheduledDate(LocalDate scheduledDate);

	public List<Interview> getInterviewByCandidateId(Long canId);

	public List<Interview> getInterviewByCandidateName(String name);

	public List<Interview> getInterviewByCandidatePhone(String phone);

	public List<Interview> getInterviewByCandidateEmail(String email);

	public List<Interview> getInterviewByCandidateRole(String role);

	public List<Interview> getInterviewByCandidateExperience(Integer exp);
	
	public List<Interview> getInterviewByEmpId(Long empId);

	public List<Interview> getInterviewByEmployeeId(Long employeeId);

	public List<Interview> getInterviewByDesignationId(Long destId);

	public List<Interview> getInterviewByEmployeeName(String name);

	public List<Interview> getInterviewByEmployeePhone(String phone);

	public List<Interview> getInterviewByEmployeeEmail(String email);

	public String deleteInterview(Long id);

	public String updateInterview(Long id, Interview interview);

	public String addInterview(Interview interview,Long canId,Long empId);

	public List<Interview> getInterviewByType(String type);

}
