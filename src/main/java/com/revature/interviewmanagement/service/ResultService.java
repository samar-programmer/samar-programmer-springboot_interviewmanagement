package com.revature.interviewmanagement.service;

import java.util.List;

import com.revature.interviewmanagement.entity.Result;
import com.revature.interviewmanagement.model.ResultDto;

public interface ResultService {

	String deleteResult(Long id);

	String updateResult(ResultDto result);

	String addResult(Long interviewId, ResultDto result);

	List<Result> getResultByCandidateId(Long canId);

	List<Result> getResultByEmployeeId(Long empId);

	Result getResultByInterviewId(Long interviewId);

	Result getResultById(Long id);

	List<Result> getAllResult();

	String sendResultMail(ResultDto resultDto);

}
