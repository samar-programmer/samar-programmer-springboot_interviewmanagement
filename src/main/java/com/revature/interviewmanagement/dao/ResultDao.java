package com.revature.interviewmanagement.dao;

import java.util.List;

import com.revature.interviewmanagement.entity.Result;
import com.revature.interviewmanagement.model.ResultDto;

public interface ResultDao {

	 List<Result> getAllResult();

	 Result getResultById(Long id);

	 Result getResultByInterviewId(Long interviewId);

	 List<Result> getResultByEmployeeId(Long empId);

	 List<Result> getResultByCandidateId(Long canId);

	 String addResult(Long interviewId, ResultDto result);

	 String updateResult(ResultDto result);

	 String deleteResult(Long id);

}
