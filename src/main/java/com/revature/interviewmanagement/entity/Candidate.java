package com.revature.interviewmanagement.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.interviewmanagement.entity.credentials.CandidateCredential;


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
public class Candidate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable=false,name="first_name")
	private String firstName;
	
	@Column(nullable=false,name="last_name")
	private String lastName;
	
	
	@Column(nullable=false,unique=true,name="email_id")
	private String emailId;
	
	@Column(nullable=false,unique=true,name="phone_number")
	private String phoneNumber;
	
	@Column(nullable=false,name="job_role")
	private String jobRole;
	
	@Column(nullable=false)
	private Integer experience;
	
	@Column(nullable=false,name="resume_link")
	private String resumeLink;

	@Column(name="added_on")
	private LocalDateTime addedOn;
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@JsonIgnore
	@OneToMany(mappedBy="candidate",cascade=CascadeType.ALL)
	private List<Interview> interview;
	
	
	@OneToOne
	@JoinColumn(name="credential_id",nullable=false)
	private CandidateCredential candidateCredential;
	
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
	
	public String getResumeLink() {
		return resumeLink;
	}

	public void setResumeLink(String resumeLink) {
		this.resumeLink = resumeLink;
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

	public List<Interview> getInterview() {
		return interview;
	}

	public void setInterview(List<Interview> interview) {
		this.interview = interview;
	}

	public CandidateCredential getCandidateCredential() {
		return candidateCredential;
	}

	public void setCandidateCredential(CandidateCredential candidateCredential) {
		this.candidateCredential = candidateCredential;
	}


	
}
