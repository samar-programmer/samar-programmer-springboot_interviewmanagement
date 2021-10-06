package com.revature.interviewmanagement.exception;

public class IdNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -8541090246035789074L;

	public IdNotFoundException(String msg){
		super(msg);
	}

}
