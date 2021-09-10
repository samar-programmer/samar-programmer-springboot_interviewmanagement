package com.revature.interviewmanagement.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.revature.interviewmanagement.entity.credentials.EmployeeCredential;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.service.EmployeeCredentialService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class EmployeeCredentialController {
	
private static final Logger logger=LogManager.getLogger(EmployeeCredentialController.class.getName());
	
		@Autowired
		private EmployeeCredentialService employeeCredentialService;
	
	
		@GetMapping("/employee/credential/{id}")
		public ResponseEntity<EmployeeCredential> getCredentialById(@PathVariable Long id){
			logger.debug("Entering getCredentialById method");
			return	new ResponseEntity<>(employeeCredentialService.getCredentialById(id), new HttpHeaders(), HttpStatus.OK);
		}
		
		 @GetMapping("/employee/credential/validation") 
		 public ResponseEntity<Boolean> validateCredential(@RequestBody EmployeeCredential employeeCredential){  
			 logger.debug("Entering validateCredential method");
			 return new ResponseEntity<>(employeeCredentialService.validateCredential(employeeCredential), new HttpHeaders(), HttpStatus.OK);  
		}
		
		@PostMapping("/employee/credential")
		public ResponseEntity<String> addCredential(@RequestBody EmployeeCredential employeeCredential){
			logger.debug("Entering addCredential method");
			return	new ResponseEntity<>(employeeCredentialService.addCredential(employeeCredential), new HttpHeaders(), HttpStatus.CREATED);
		}
		
		@PutMapping("/employee/credential/{id}")
		public ResponseEntity<String> updateCredential(@PathVariable Long id,@RequestBody EmployeeCredential employeeCredential){
			logger.debug("Entering updateInterview method");
			return	new ResponseEntity<>(employeeCredentialService.updateCredential(id,employeeCredential), new HttpHeaders(), HttpStatus.OK);
		}
	
		@ExceptionHandler(IdNotFoundException.class)
		public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		 	logger.error("Id not found exception for {}",e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(DuplicateIdException.class)
		public ResponseEntity<String> duplicateIdFound(DuplicateIdException e) {
		 	logger.error("Duplicate Id exception for {}",e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	


}
