package com.revature.interviewmanagement.exception;

public class IdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -4918980763600812322L;

	public IdNotFoundException(String msg) {
		super(msg);
	}
}
