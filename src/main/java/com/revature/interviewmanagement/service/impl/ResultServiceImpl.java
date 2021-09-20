package com.revature.interviewmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.dao.ResultDao;
import com.revature.interviewmanagement.entity.Result;
import com.revature.interviewmanagement.model.ResultDto;
import com.revature.interviewmanagement.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	private ResultDao resultDao;
	
	@Override
	public String deleteResult(Long id) {
		
		return resultDao.deleteResult(id);
	}

	@Override
	public String updateResult(Long id, ResultDto result) {
		
		return resultDao.updateResult(id,result);
	}

	@Override
	public String addResult(Long interviewId, ResultDto result) {
		
		return resultDao.addResult(interviewId,result);
	}

	@Override
	public List<Result> getResultByCandidateId(Long canId) {
		
		return resultDao.getResultByCandidateId(canId);
	}

	@Override
	public List<Result> getResultByEmployeeId(Long empId) {
		
		return resultDao.getResultByEmployeeId(empId);
	}

	@Override
	public List<Result> getResultByInterviewId(Long interviewId) {
		
		return resultDao.getResultByInterviewId(interviewId);
	}

	@Override
	public Result getResultById(Long id) {
		
		return resultDao.getResultById(id) ;
	}

	@Override
	public List<Result> getAllResult() {
		
		return resultDao.getAllResult();
	}

}
