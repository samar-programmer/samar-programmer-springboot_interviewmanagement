package com.revature.interviewmanagement.rest;



import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.service.CandidateService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class CandidateController {
	
	private static final Logger logger=LogManager.getLogger(CandidateController.class.getName());
	
	@Autowired
	private CandidateService candidateSerive;
	
	
	@GetMapping("/candidate")
	public ResponseEntity<List<Candidate>> getAllCandidate(){
		logger.debug("Entering getAllCandidate method");
		return	new ResponseEntity<>(candidateSerive.getAllCandidate(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/candidate/{id}")
	public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id){
		logger.debug("Entering getCandidateById method");
		return	new ResponseEntity<>(candidateSerive.getCandidateById(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	
	 @PostMapping("/candidate/email") 
	 public ResponseEntity<Candidate> getCandidateByEmailId(@RequestBody CandidateDto candidateDto){
		 logger.debug("Entering getCandidateByEmailId method");
		 return new ResponseEntity<>(candidateSerive.getCandidateByEmailId(candidateDto.getEmailId()), new HttpHeaders(), HttpStatus.OK);  
	} 
	 
	 @PostMapping("/candidate/phone")  
	 public ResponseEntity<Candidate> getCandidateByPhoneNumber(@RequestBody CandidateDto candidateDto){
		 logger.debug("Entering getCandidateByPhoneNumber method");
		 return new ResponseEntity<>(candidateSerive.getCandidateByPhoneNumber(candidateDto.getPhoneNumber()), new HttpHeaders(), HttpStatus.OK);  
	} 
	  
	 @GetMapping("/candidate/name/{name}") 
	 public ResponseEntity<List<Candidate>> getCandidateByName(@PathVariable String name){  
		 logger.debug("Entering getCandidateByName method");
		 return new ResponseEntity<>(candidateSerive.getCandidateByName(name), new HttpHeaders(), HttpStatus.OK);  
	}  
	 
	 
	 @GetMapping("/candidate/experience/{exp}")
	public ResponseEntity<List<Candidate>> getCandidateByExperience(@PathVariable Integer exp){ 
		 logger.debug("Entering getCandidateByExperience method");
		 return new ResponseEntity<>(candidateSerive.getCandidateByExperience(exp), new HttpHeaders(), HttpStatus.OK); 
	} 
	
	 
	 @GetMapping("/candidate/role/{role}")  
	 public ResponseEntity<List<Candidate>> getCandidateByRole(@PathVariable String role){  
		 logger.debug("Entering getCandidateByRole method");
		 return new ResponseEntity<>(candidateSerive.getCandidateByRole(role), new HttpHeaders(), HttpStatus.OK); 
	}
	
	@PostMapping("/candidate/{credential-id}")
	public ResponseEntity<String> addCandidate(@PathVariable("credential-id") Long id,@RequestBody CandidateDto candidateDto){
		logger.debug("Entering addCandidate method");
		return	new ResponseEntity<>(candidateSerive.addCandidate(id,candidateDto), new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@PutMapping("/candidate/{id}")
	public ResponseEntity<String> updateCandidate(@PathVariable Long id,@RequestBody CandidateDto candidateDto){
		logger.debug("Entering updateCandidate method");
		return	new ResponseEntity<>(candidateSerive.updateCandidate(id,candidateDto), new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/candidate/{id}")
	public ResponseEntity<String> deleteCandidate(@PathVariable Long id){
		logger.debug("Entering deleteCandidate method");
		return	new ResponseEntity<>(candidateSerive.deleteCandidate(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	
	
	
 @ExceptionHandler(DuplicateIdException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateIdException e) {
	 	logger.error("Duplicate Id exception for {}",e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}	
 
 @ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
	 	logger.error("Id not found exception for {}",e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
}
