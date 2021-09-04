package com.revature.interviewmanagement.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;


//@EntityListeners(AuditingEntityListener.class)
@Table(name="candidate_test")

    @NamedNativeQuery(
    name = "callCandidateByEmailProcedure",
    query = "CALL getCandidateByEmail(:email)",
    resultClass = Candidate.class
    )
    @NamedNativeQuery(
    name = "callCandidateByPhoneProcedure",
    query = "CALL getCandidateByPhone(:phone)",
    resultClass = Candidate.class
    )
    @NamedNativeQuery(
    	    name = "callCandidateByEmailUpdateProcedure",
    	    query = "CALL getCandidateUpdateByEmail(:email,:id)",
    	    resultClass = Candidate.class
    	    )
    @NamedNativeQuery(
    	    name = "callCandidateByPhoneUpdateProcedure",
    	    query = "CALL getCandidateUpdateByPhone(:phone,:id)",
    	    resultClass = Candidate.class
    	    )
@Entity  
public class Candidate implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable=false)
	private String firstName;
	
	@Column(nullable=false)
	private String lastName;
	
	
	@Column(nullable=false,unique=true)
	private String emailId;
	
	@Column(nullable=false,unique=true)
	private String phoneNumber;
	
	@Column(nullable=false)
	private String jobRole;
	
	@Column(nullable=false)
	private Integer experience;
	
	//@Column(updatable = false, nullable = false)
   //@CreatedDate
	private LocalDateTime addedOn;
	
	//@Column(nullable = false)
    //@LastModifiedDate
	private LocalDateTime updatedOn;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
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

	private String updatedBy;
	

	
}
