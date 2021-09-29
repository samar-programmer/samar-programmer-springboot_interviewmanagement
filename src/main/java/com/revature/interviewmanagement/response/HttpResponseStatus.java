package com.revature.interviewmanagement.response;

public class HttpResponseStatus {
	private Integer statusCode;
	private String message;
	private Object data;
	
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
	
	
}
