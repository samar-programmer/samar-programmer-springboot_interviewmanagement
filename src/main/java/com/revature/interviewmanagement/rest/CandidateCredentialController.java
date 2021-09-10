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


import com.revature.interviewmanagement.entity.credentials.CandidateCredential;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.service.CandidateCredentialService;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class CandidateCredentialController {
	
	private static final Logger logger=LogManager.getLogger(CandidateCredentialController.class.getName());
	
	@Autowired
	private CandidateCredentialService candidateCredentialService;
	
	
	@GetMapping("/candidate/credential/{id}")
	public ResponseEntity<CandidateCredential> getCredentialById(@PathVariable Long id){
		logger.debug("Entering getCredentialById method");
		return	new ResponseEntity<>(candidateCredentialService.getCredentialById(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	 @GetMapping("/candidate/credential/validation") 
	 public ResponseEntity<Boolean> validateCredential(@RequestBody CandidateCredential candidateCredential){  
		 logger.debug("Entering validateCredential method");
		 return new ResponseEntity<>(candidateCredentialService.validateCredential(candidateCredential), new HttpHeaders(), HttpStatus.OK);  
	}
	
 	@PostMapping("/candidate/credential")
	public ResponseEntity<String> addCredential(@RequestBody CandidateCredential candidateCredential){
		logger.debug("Entering addCredential method");
		return	new ResponseEntity<>(candidateCredentialService.addCredential(candidateCredential), new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@PutMapping("/candidate/credential/{id}")
	public ResponseEntity<String> updateCredential(@PathVariable Long id,@RequestBody CandidateCredential candidateCredential){
		logger.debug("Entering updateInterview method");
		return	new ResponseEntity<>(candidateCredentialService.updateCredential(id,candidateCredential), new HttpHeaders(), HttpStatus.OK);
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
