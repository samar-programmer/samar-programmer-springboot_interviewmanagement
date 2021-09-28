package com.revature.interviewmanagement.exceptionhandling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revature.interviewmanagement.exception.BussinessLogicException;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.response.HttpResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger=LogManager.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<HttpResponseStatus> userNotFound(DatabaseException e) {
	 	logger.error(e.getMessage());
	 	return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BussinessLogicException.class)
	public ResponseEntity<HttpResponseStatus> bussinessLogicExceptionFound(BussinessLogicException e) {
	 	logger.error(e.getMessage());
	 	return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value() ,e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<HttpResponseStatus> internalServerErrorFound(Exception e) {
	 	logger.error(e.getMessage());
	 	return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value() ,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
