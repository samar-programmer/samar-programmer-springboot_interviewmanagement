package com.revature.interviewmanagement.response;

import java.util.List;

public class HttpResponseStatus {
	private Integer statusCode;
	private String message;
	private Object data;
	private List<String> errorDetails;//it is used to display error details
	
	public HttpResponseStatus() {}
	
	public HttpResponseStatus(Integer statusCode, String message, Object data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public HttpResponseStatus(Integer statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public HttpResponseStatus(Integer statusCode, String message, List<String> errorDetails) {
		this.statusCode = statusCode;
		this.message = message;
		this.errorDetails=errorDetails;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<String> getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(List<String> errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	
}
