package com.revature.interviewmanagement.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.dao.InterviewDao;
import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.model.InterviewDto;
import com.revature.interviewmanagement.service.InterviewService;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewDao interviewDao;
	
	@Override
	public List<Interview> getAllInterview() {
		
		return interviewDao.getAllInterview();
	}

	@Override
	public Interview getInterviewById(Long id) {
		
		return interviewDao.getInterviewById(id);
	}

	@Override
	public List<Interview> getInterviewByScheduledDate(LocalDate scheduledDate) {
		
		return interviewDao.getInterviewByScheduledDate(scheduledDate);
	}

	@Override
	public List<Interview> getInterviewByCandidateId(Long canId) {
	
		return interviewDao.getInterviewByCandidateId(canId);
	}

	@Override
	public List<Interview> getInterviewByCandidateName(String name) {
		
		return interviewDao.getInterviewByCandidateName(name);
	}

	@Override
	public List<Interview> getInterviewByCandidatePhone(String phone) {
		
		return interviewDao.getInterviewByCandidatePhone(phone);
	}

	@Override
	public List<Interview> getInterviewByCandidateEmail(String email) {
		
		return interviewDao.getInterviewByCandidateEmail(email);
	}

	@Override
	public List<Interview> getInterviewByCandidateRole(String role) {
		
		return interviewDao.getInterviewByCandidateRole(role);
	}

	@Override
	public List<Interview> getInterviewByCandidateExperience(Integer exp) {
		
		return interviewDao.getInterviewByCandidateExperience(exp);
	}
	
	@Override
	public List<Interview> getInterviewByEmpId(Long empId) {
		
		return interviewDao.getInterviewByEmpId(empId);
	}

	@Override
	public List<Interview> getInterviewByEmployeeId(Long employeeId) {
		
		return interviewDao.getInterviewByEmployeeId(employeeId) ;
	}

	@Override
	public List<Interview> getInterviewByDesignationId(Long destId) {
		
		return interviewDao.getInterviewByDesignationId(destId);
	}

	@Override
	public List<Interview> getInterviewByEmployeeName(String name) {
		
		return interviewDao.getInterviewByEmployeeName(name);
	}

	@Override
	public List<Interview> getInterviewByEmployeePhone(String phone) {
		
		return interviewDao.getInterviewByEmployeePhone(phone);
	}

	@Override
	public List<Interview> getInterviewByEmployeeEmail(String email) {
		
		return interviewDao.getInterviewByEmployeeEmail(email);
	}

	@Override
	public String deleteInterview(Long id) {
		
		return interviewDao.deleteInterview(id);
	}

	@Override
	public String updateInterview(Long id, InterviewDto interview) {
		
		return interviewDao.updateInterview(id,interview);
	}

	@Override
	public String addInterview(InterviewDto interview,Long canId,Long empId) {
		
		return interviewDao.addInterview(interview,canId,empId);
	}

	@Override
	public List<Interview> getInterviewByType(String type) {
		
		return interviewDao.getInterviewByType(type);
	}

	

	

}
