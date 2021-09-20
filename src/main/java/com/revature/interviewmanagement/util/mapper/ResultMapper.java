package com.revature.interviewmanagement.util.mapper;

import com.revature.interviewmanagement.entity.Result;
import com.revature.interviewmanagement.model.ResultDto;

public class ResultMapper {
	private ResultMapper() {
		throw new IllegalStateException("Utility class");

	}
	
	public static Result resultEntityMapper(ResultDto resultDto) {
		Result resultEntity=new Result();
		
		resultEntity.setId(resultDto.getId());
		resultEntity.setRemarks(resultDto.getRemarks());
		resultEntity.setStatus(resultDto.getStatus());
		resultEntity.setInterview(resultDto.getInterview());
		resultEntity.setAddedOn(resultDto.getAddedOn());
		resultEntity.setUpdatedOn(resultDto.getUpdatedOn());
		resultEntity.setUpdatedBy(resultDto.getUpdatedBy());
		
		
		return resultEntity;
	}
}
