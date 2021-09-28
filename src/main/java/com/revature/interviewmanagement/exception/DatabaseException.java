package com.revature.interviewmanagement.exception;

public class DatabaseException extends RuntimeException{
	private static final long serialVersionUID = -761917037969875783L;

	public DatabaseException(String msg) {
		super(msg);
	}
}
