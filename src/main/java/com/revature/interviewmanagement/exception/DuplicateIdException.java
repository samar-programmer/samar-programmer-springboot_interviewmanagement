package com.revature.interviewmanagement.exception;

public class DuplicateIdException extends RuntimeException {
	private static final long serialVersionUID = -1910907425942711791L;

	public DuplicateIdException(String msg){
		super(msg);
	}
}
