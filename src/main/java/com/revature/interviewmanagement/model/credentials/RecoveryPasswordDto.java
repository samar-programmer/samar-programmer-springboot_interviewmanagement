package com.revature.interviewmanagement.model.credentials;

import java.time.LocalDateTime;


import com.revature.interviewmanagement.entity.credentials.CandidateCredential;

public class RecoveryPasswordDto {
	
	private Long id;
	private String emailId;
	private String code;
	private CandidateCredential candidateCredential;
	private LocalDateTime addedOn;
	private LocalDateTime updatedOn;
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
	private String updatedBy;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public CandidateCredential getCandidateCredential() {
		return candidateCredential;
	}
	public void setCandidateCredential(CandidateCredential candidateCredential) {
		this.candidateCredential = candidateCredential;
	}
	
	
}
