package com.revature.interviewmanagement.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.util.markerinterface.AddValidation;
import com.revature.interviewmanagement.util.markerinterface.EmailValidation;
import com.revature.interviewmanagement.util.markerinterface.PhoneValidation;
import com.revature.interviewmanagement.util.markerinterface.UpdateValidation;


public class EmployeeDto {
	
	@NotNull(message="Id cannot be null",groups = {UpdateValidation.class})
	@Min(value = 1,message="Id value should not be less than 1",groups = {UpdateValidation.class})
	@Max(value = Long.MAX_VALUE,message="Id value should not be greater than 9,223,372,036,854,775,807",groups = {UpdateValidation.class})
	private	Long id;
	
	@NotBlank(message="First name cannot be null or empty",groups = {AddValidation.class})
	private String firstName;
	
	@NotBlank(message="Last name cannot be null or empty",groups = {AddValidation.class})
	private String lastName;
	
	@NotBlank(message="Phone Number cannot be null or empty",groups = {AddValidation.class,PhoneValidation.class})
	@Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$",message = "Invalid Phone Number"
		,groups = {AddValidation.class,PhoneValidation.class})
	private String phoneNumber;
	
	@Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@(?:[a-zA-Z0-9-]+\\.)[a-zA-Z]{2,6}$"
			,message = "Invalid Email Address",groups = {AddValidation.class,EmailValidation.class})
	@NotBlank(message="Email Address cannot be null or empty",groups = {AddValidation.class,EmailValidation.class})
	private String emailId;
	
	@NotBlank(message="Employee Designation cannot be null or empty",groups = {AddValidation.class})
	private String designation;
	
	@NotBlank(message="Employee Status cannot be null or empty",groups = {UpdateValidation.class})
	private String status;
	
	@NotNull(message="AddedOn timestamp cannot be null",groups = {UpdateValidation.class})
	private LocalDateTime addedOn;
	private LocalDateTime updatedOn;
	private String updatedBy;
	private List<Interview> interview;
	
	
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
