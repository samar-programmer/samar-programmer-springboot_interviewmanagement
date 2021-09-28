package com.revature.interviewmanagement.exception;

public class DuplicateIdException extends RuntimeException{
	private static final long serialVersionUID = 5861484630943994486L;

	public DuplicateIdException(String msg) {
		super(msg);
	}
}
