package com.revature.interviewmanagement.exceptionhandling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import com.revature.interviewmanagement.exception.BussinessLogicException;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.exception.NoRecordFoundException;
import com.revature.interviewmanagement.response.HttpResponseStatus;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.INVALID_INPUT;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.INVALID_REQUESTBODY;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<HttpResponseStatus> idNotFound(IdNotFoundException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<HttpResponseStatus> noRecordFound(NoRecordFoundException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateIdException.class)
	public ResponseEntity<HttpResponseStatus> duplicateIdFound(DuplicateIdException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CONFLICT.value(), e.getMessage()),
				HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<HttpResponseStatus> invalidInputArgumentsFound(MethodArgumentTypeMismatchException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(),INVALID_INPUT),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HttpResponseStatus> invalidRequestBodyFound(MethodArgumentNotValidException e) {
		logger.error(e.getMessage());
		
		//this exception occurs when mandatory input fields are not provided with input request body,
		//as we have notNull or NotBlank in Dto, we can get corresponding input fields which is not satisfying, 
		//those errors are captured here.
		List<String> details= e.getBindingResult().getFieldErrors().stream()
		        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
		
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(),INVALID_REQUESTBODY,details),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<HttpResponseStatus> databaseExceptionFound(DatabaseException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BussinessLogicException.class)
	public ResponseEntity<HttpResponseStatus> bussinessLogicExceptionFound(BussinessLogicException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<HttpResponseStatus> internalServerErrorFound(Exception e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
