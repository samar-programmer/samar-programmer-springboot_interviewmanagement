package com.revature.interviewmanagement.dao;

import java.util.List;

import com.revature.interviewmanagement.entity.Result;

public interface ResultDao {

	public List<Result> getAllResult();

	public Result getResultById(Long id);

	public List<Result> getResultByInterviewId(Long interviewId);

	public List<Result> getResultByEmployeeId(Long empId);

	public List<Result> getResultByCandidateId(Long canId);

	public String addResult(Long interviewId, Result result);

	public String updateResult(Long id, Result result);

	public String deleteResult(Long id);

}
