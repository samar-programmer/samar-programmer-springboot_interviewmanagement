package com.revature.interviewmanagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Interview {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private	Long id;
	
	@Column(name="interview_type",nullable=false)
	private String interviewType;
	
	@Column(nullable=false,name="call_scheduled_date")
	private LocalDate callScheduledDate;
	
	@Column(nullable=false,name="call_scheduled_time")
	private LocalTime callScheduledTime;
	
	@Column(name="added_on")
	private LocalDateTime addedOn;
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@JsonIgnore
	@OneToOne(mappedBy="interview",cascade=CascadeType.ALL)
	private Result result;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private Candidate candidate;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private Employee employee;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public String getInterviewType() {
		return interviewType;
	}
	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
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
	
	
}
