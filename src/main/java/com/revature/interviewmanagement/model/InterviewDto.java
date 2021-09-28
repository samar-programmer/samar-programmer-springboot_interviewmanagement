package com.revature.interviewmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.entity.Result;


public class InterviewDto {

	private	Long id;
	private String interviewType;
	private String status;
	private LocalDate callScheduledDate;
	private LocalTime callScheduledTime;
	private LocalDateTime addedOn;
	private LocalDateTime updatedOn;
	private String updatedBy;
	private Result result;
	private Candidate candidate;
	private Employee employee;
	
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
