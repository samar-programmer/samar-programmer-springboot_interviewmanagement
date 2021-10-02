package com.revature.interviewmanagement.dao;

import java.util.List;

import com.revature.interviewmanagement.entity.Result;

public interface ResultDao {

	List<Result> getAllResult();

	Result getResultById(Long id);

	Result getResultByInterviewId(Long interviewId);

	List<Result> getResultByEmployeeId(Long empId);

	List<Result> getResultByCandidateId(Long canId);

	String addResult(Long interviewId, Result result);

	String updateResult(Result result);

	String deleteResult(Long id);

}
