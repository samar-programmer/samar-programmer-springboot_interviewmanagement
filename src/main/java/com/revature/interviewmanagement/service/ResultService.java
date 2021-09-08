package com.revature.interviewmanagement.service;

import java.util.List;

import com.revature.interviewmanagement.entity.Result;

public interface ResultService {

	public String deleteResult(Long id);

	public String updateResult(Long id, Result result);

	public String addResult(Long interviewId, Result result);

	public List<Result> getResultByCandidateId(Long canId);

	public List<Result> getResultByEmployeeId(Long empId);

	public List<Result> getResultByInterviewId(Long interviewId);

	public Result getResultById(Long id);

	public List<Result> getAllResult();

}
