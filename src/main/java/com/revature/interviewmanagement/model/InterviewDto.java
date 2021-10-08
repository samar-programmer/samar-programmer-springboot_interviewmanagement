package com.revature.interviewmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.entity.Result;
import com.revature.interviewmanagement.util.markerinterface.AddValidation;
import com.revature.interviewmanagement.util.markerinterface.MailValidation;
import com.revature.interviewmanagement.util.markerinterface.UpdateValidation;


public class InterviewDto {

	@NotNull(message="Id cannot be null",groups = {UpdateValidation.class})
	@Min(value = 1,message="Id value should not be less than 1",groups = {UpdateValidation.class})
	@Max(value = Long.MAX_VALUE,message="Id value should not be greater than 9,223,372,036,854,775,807",groups = {UpdateValidation.class})
	private	Long id;
	
	@NotBlank(message="Interview Type cannot be null or empty",groups = {AddValidation.class,MailValidation.class})
	private String interviewType;
	
	@NotBlank(message="Interview Status cannot be null or empty",groups = {UpdateValidation.class})
	private String status;
	
	@NotNull(message="Call Scheduled Date cannot be null",groups = {AddValidation.class,MailValidation.class})
	private LocalDate callScheduledDate;
	
	@NotNull(message="Call Scheduled Time cannot be null",groups = {AddValidation.class,MailValidation.class})
	private LocalTime callScheduledTime;
	
	@NotNull(message="Candidate cannot be null",groups = {UpdateValidation.class})
	private Candidate candidate;
	
	@NotNull(message="Employee cannot be null",groups = {UpdateValidation.class})
	private Employee employee;
	
	@NotNull(message="AddedOn timestamp cannot be null",groups = {UpdateValidation.class})
	private LocalDateTime addedOn;
	private LocalDateTime updatedOn;
	private String updatedBy;
	private Result result;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInterviewType() {
		return interviewType;
	}
	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}
	public LocalDate getCallScheduledDate() {
		return callScheduledDate;
	}
	public void setCallScheduledDate(LocalDate callScheduledDate) {
		this.callScheduledDate = callScheduledDate;
	}
	public LocalTime getCallScheduledTime() {
		return callScheduledTime;
	}
	public void setCallScheduledTime(LocalTime callScheduledTime) {
		this.callScheduledTime = callScheduledTime;
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
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
