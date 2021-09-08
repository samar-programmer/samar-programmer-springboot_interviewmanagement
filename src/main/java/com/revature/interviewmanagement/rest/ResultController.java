package com.revature.interviewmanagement.rest;

import java.util.List;

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

import com.revature.interviewmanagement.entity.Result;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.service.ResultService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	@GetMapping("/result")
	public ResponseEntity<List<Result>> getAllResult(){
		
		return	new ResponseEntity<>(resultService.getAllResult(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/result/{id}")
	public ResponseEntity<Result> getResultById(@PathVariable Long id){
		
		return	new ResponseEntity<>(resultService.getResultById(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/result/interview/{id}")
	public ResponseEntity<List<Result>> getResultByInterviewId(@PathVariable("id") Long interviewId){
		
		return	new ResponseEntity<>(resultService.getResultByInterviewId(interviewId), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/result/employee/{id}")
	public ResponseEntity<List<Result>> getResultByEmployeeId(@PathVariable("id") Long empId){
		
		return	new ResponseEntity<>(resultService.getResultByEmployeeId(empId), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/result/candidate/{id}")
	public ResponseEntity<List<Result>> getResultByCandidateId(@PathVariable("id") Long canId){
		
		return	new ResponseEntity<>(resultService.getResultByCandidateId(canId), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/result/{interview-id}")
	public ResponseEntity<String> addResult(@PathVariable("interview-id") Long interviewId,@RequestBody Result result){
		
		return	new ResponseEntity<>(resultService.addResult(interviewId,result), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping("/result/{id}")
	public ResponseEntity<String> updateResult(@PathVariable Long id,@RequestBody Result result){
		
		return	new ResponseEntity<>(resultService.updateResult(id,result), new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/result/{id}")
	public ResponseEntity<String> deleteResult(@PathVariable Long id){
		
		return	new ResponseEntity<>(resultService.deleteResult(id), new HttpHeaders(), HttpStatus.OK);
	}
	

 
 @ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
 
 @ExceptionHandler(DuplicateIdException.class)
	public ResponseEntity<String> duplicateResultFound(DuplicateIdException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}
