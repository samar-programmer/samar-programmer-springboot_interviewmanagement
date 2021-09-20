package com.revature.interviewmanagement.model.credentials;

import java.time.LocalDateTime;

import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.entity.credentials.RecoveryPassword;



public class CandidateCredentialDto {
	
	private Long id;
	private String emailId;
	private String password;//it is hashed password
	private String salt;
	private LocalDateTime addedOn;
	private LocalDateTime updatedOn;
	private String updatedBy;
	private Candidate candidate;
	private RecoveryPassword recoveryPassword;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
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
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public RecoveryPassword getRecoveryPassword() {
		return recoveryPassword;
	}
	public void setRecoveryPassword(RecoveryPassword recoveryPassword) {
		this.recoveryPassword = recoveryPassword;
	}
	
	
	
	
}
