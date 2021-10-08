package com.revature.interviewmanagement.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.util.markerinterface.AddValidation;
import com.revature.interviewmanagement.util.markerinterface.MailValidation;
import com.revature.interviewmanagement.util.markerinterface.UpdateValidation;



public class ResultDto {
	
	@NotNull(message="Id cannot be null",groups = {UpdateValidation.class})
	@Min(value = 1,message="Id value should not be less than 1",groups = {UpdateValidation.class})
	@Max(value = Long.MAX_VALUE,message="Id value should not be greater than 9,223,372,036,854,775,807",groups = {UpdateValidation.class})
	private	Long id;
	
	@NotBlank(message="Remarks cannot be null or empty",groups={AddValidation.class})
	private	String remarks;
	
	@NotBlank(message="Result status cannot be null or empty",groups={AddValidation.class,MailValidation.class})
	private	String status;
	
	@NotNull(message="Interview cannot be null",groups={UpdateValidation.class,MailValidation.class})
	private Interview interview;
	
	@NotBlank(message="Result message cannot be null or empty",groups={MailValidation.class})
	private String message;//for HR to send custom message to candidate, value possibly can be null while adding result by the employee
	
	@NotNull(message="AddedOn timestamp should not be null",groups = {UpdateValidation.class})
	private LocalDateTime addedOn;
	private LocalDateTime updatedOn;
	private String updatedBy;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(LocalDateTime addedOn) {
		this.addedOn = addedOn;
	}
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Interview getInterview() {
		return interview;
	}
	public void setInterview(Interview interview) {
		this.interview = interview;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
