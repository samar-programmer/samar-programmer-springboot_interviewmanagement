package com.revature.interviewmanagement.dao;

import java.util.List;

import com.revature.interviewmanagement.entity.Result;
import com.revature.interviewmanagement.model.ResultDto;

public interface ResultDao {

	public List<Result> getAllResult();

	public Result getResultById(Long id);

	public List<Result> getResultByInterviewId(Long interviewId);

	public List<Result> getResultByEmployeeId(Long empId);

	public List<Result> getResultByCandidateId(Long canId);

	public String addResult(Long interviewId, ResultDto result);

	public String updateResult(Long id, ResultDto result);

	public String deleteResult(Long id);

}
