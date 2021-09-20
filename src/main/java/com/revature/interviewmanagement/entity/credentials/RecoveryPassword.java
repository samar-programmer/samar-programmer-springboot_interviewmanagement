package com.revature.interviewmanagement.entity.credentials;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="recovery_password")
public class RecoveryPassword {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="email_id",nullable=false,unique=true)
	private String emailId;
	
	@Column(nullable=false)
	private String code;
	
	@Column(name="added_on")
	private LocalDateTime addedOn;
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@JsonIgnore
	@OneToOne
	private CandidateCredential candidateCredential;

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
