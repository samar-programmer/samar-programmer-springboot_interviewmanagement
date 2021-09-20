package com.revature.interviewmanagement.model;

import java.time.LocalDateTime;
import java.util.List;

import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.entity.credentials.EmployeeCredential;


public class EmployeeDto {
	
	private Long id;
	private Long employeeId;
	private Long designationId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailId;
	private LocalDateTime addedOn;
	private LocalDateTime updatedOn;
	private String updatedBy;
	private List<Interview> interview;
	private EmployeeCredential employeeCredential;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	public EmployeeCredential getEmployeeCredential() {
		return employeeCredential;
	}
	public void setEmployeeCredential(EmployeeCredential employeeCredential) {
		this.employeeCredential = employeeCredential;
	}

	
	
	
	
}
