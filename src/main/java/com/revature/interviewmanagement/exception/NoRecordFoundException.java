package com.revature.interviewmanagement.exception;

public class NoRecordFoundException extends RuntimeException{
	private static final long serialVersionUID = -598660957573125025L;

	public NoRecordFoundException(String msg) {
		super(msg);
	}
}
