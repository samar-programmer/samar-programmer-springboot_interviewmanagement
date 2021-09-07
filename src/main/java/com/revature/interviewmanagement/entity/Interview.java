package com.revature.interviewmanagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="interview_test")
public class Interview {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private	Long id;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private Candidate candidate;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private Employee employee;
	
	private String interviewType;
	
	@Column(nullable=false)
	private LocalDate callScheduledDate;
	
	@Column(nullable=false)
	private LocalTime callScheduledTime;
	
	private LocalDateTime addedOn;
	
	private LocalDateTime updatedOn;
	
	private String updatedBy;
	
	
	
	
	@Override
	public String toString() {
		return "Interview [id=" + id + ", candidate=" + candidate + ", employee=" + employee + ", interviewType="
				+ interviewType + ", callScheduledDate=" + callScheduledDate + ", callScheduledTime="
				+ callScheduledTime + ", addedOn=" + addedOn + ", updatedOn=" + updatedOn + ", updatedBy=" + updatedBy
				+ "]";
	}
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
	
	
}
