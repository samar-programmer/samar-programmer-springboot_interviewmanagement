package com.revature.interviewmanagement.rest;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.model.InterviewDto;
import com.revature.interviewmanagement.service.InterviewService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class InterviewController {

	private static final Logger logger=LogManager.getLogger(InterviewController.class.getName());
	
	@Autowired
	private InterviewService interviewService;
	
	@GetMapping("/interview")
	public ResponseEntity<List<Interview>> getAllInterview(){
		logger.debug("Entering getAllInterview method");
		return	new ResponseEntity<>(interviewService.getAllInterview(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/interview/{id}")
	public ResponseEntity<Interview> getInterviewById(@PathVariable Long id){
		logger.debug("Entering getInterviewById method");
		return	new ResponseEntity<>(interviewService.getInterviewById(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/interview/type/{type}")
	public ResponseEntity<List<Interview>> getInterviewByType(@PathVariable String type){
		logger.debug("Entering getInterviewByType method");
		return	new ResponseEntity<>(interviewService.getInterviewByType(type), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/interview/scheduled-date/{date}")
	public ResponseEntity<List<Interview>> getInterviewByScheduledDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate scheduledDate){
		logger.debug("Entering getInterviewByScheduledDate method");
		return	new ResponseEntity<>(interviewService.getInterviewByScheduledDate(scheduledDate), new HttpHeaders(), HttpStatus.OK);
	}
	
	
	 @GetMapping("/interview/candidate/{canId}") 
	 public ResponseEntity<List<Interview>> getInterviewByCandidateId(@PathVariable Long canId){
		 logger.debug("Entering getInterviewByCandidateId method");
		 return new ResponseEntity<>(interviewService.getInterviewByCandidateId(canId), new HttpHeaders(), HttpStatus.OK);  
	} 
	 
	 @GetMapping("/interview/candidate/name/{name}")  
	 public ResponseEntity<List<Interview>> getInterviewByCandidateName(@PathVariable String name){
		 logger.debug("Entering getInterviewByCandidateName method");
		 return new ResponseEntity<>(interviewService.getInterviewByCandidateName(name), new HttpHeaders(), HttpStatus.OK);  
	} 
	  
	 @PostMapping("/interview/candidate/phone") 
	 public ResponseEntity<List<Interview>> getInterviewByCandidatePhone(@RequestBody CandidateDto candidateDto){  
		 logger.debug("Entering getInterviewByCandidatePhone method");
		 return new ResponseEntity<>(interviewService.getInterviewByCandidatePhone(candidateDto.getPhoneNumber()), new HttpHeaders(), HttpStatus.OK);  
	}  
	 @PostMapping("/interview/candidate/email") 
	 public ResponseEntity<List<Interview>> getInterviewByCandidateEmail(@RequestBody CandidateDto candidateDto){  
		 logger.debug("Entering getInterviewByCandidateEmail method");
		 return new ResponseEntity<>(interviewService.getInterviewByCandidateEmail(candidateDto.getEmailId()), new HttpHeaders(), HttpStatus.OK);  
	}
	 
	 @GetMapping("/interview/candidate/role/{role}")  
	 public ResponseEntity<List<Interview>> getInterviewByCandidateRole(@PathVariable String role){
		 logger.debug("Entering getInterviewByCandidateRole method");
		 return new ResponseEntity<>(interviewService.getInterviewByCandidateRole(role), new HttpHeaders(), HttpStatus.OK);  
	} 
	 
	 @GetMapping("/interview/candidate/experience/{exp}")  
	 public ResponseEntity<List<Interview>> getInterviewByCandidateExperience(@PathVariable Integer exp){
		 logger.debug("Entering getInterviewByCandidateExperience method");
		 return new ResponseEntity<>(interviewService.getInterviewByCandidateExperience(exp), new HttpHeaders(), HttpStatus.OK);  
	}
	 
	 @GetMapping("/interview/employee/id/{empId}")//this empId is auto generated id(Long) in employee entity not the employeeId(String) of employee entity
	public ResponseEntity<List<Interview>> getInterviewByEmpId(@PathVariable("empId") Long empId){ 
		 logger.debug("Entering getInterviewByEmpId method");
		 return new ResponseEntity<>(interviewService.getInterviewByEmpId(empId), new HttpHeaders(), HttpStatus.OK); 
	} 
	 
	 @PostMapping("/interview/employee/employee-id")//this is the employeeId(String) of employee entity not auto generated id
		public ResponseEntity<List<Interview>> getInterviewByEmployeeId(@RequestBody EmployeeDto employeeDto){ 
		 logger.debug("Entering getInterviewByEmployeeId method");
			 return new ResponseEntity<>(interviewService.getInterviewByEmployeeId(employeeDto.getEmployeeId()), new HttpHeaders(), HttpStatus.OK); 
		}
	 
	 @PostMapping("/interview/employee/designation-id")
		public ResponseEntity<List<Interview>> getInterviewByDesignationId(@RequestBody EmployeeDto employeeDto){ 
		 logger.debug("Entering getInterviewByDesignationId method");
			 return new ResponseEntity<>(interviewService.getInterviewByDesignationId(employeeDto.getDesignationId()), new HttpHeaders(), HttpStatus.OK); 
		} 
	
	 
	 @GetMapping("/interview/employee/name/{name}")  
	 public ResponseEntity<List<Interview>> getInterviewByEmployeeName(@PathVariable String name){  
		 logger.debug("Entering getInterviewByEmployeeName method");
		 return new ResponseEntity<>(interviewService.getInterviewByEmployeeName(name), new HttpHeaders(), HttpStatus.OK); 
	}
	 
	 @PostMapping("/interview/employee/phone") 
	 public ResponseEntity<List<Interview>> getInterviewByEmployeePhone(@RequestBody EmployeeDto employeeDto){  
		//if we don't give @PathVariable("phone") here, phone number couldn't be recognized as string
		 logger.debug("Entering getInterviewByEmployeePhone method");
		 return new ResponseEntity<>(interviewService.getInterviewByEmployeePhone(employeeDto.getPhoneNumber()), new HttpHeaders(), HttpStatus.OK);  
	}  
	 @PostMapping("/interview/employee/email") 
	 public ResponseEntity<List<Interview>> getInterviewByEmployeeEmail(@RequestBody EmployeeDto employeeDto){  
		 logger.debug("Entering getInterviewByEmployeeEmail method");
		 return new ResponseEntity<>(interviewService.getInterviewByEmployeeEmail(employeeDto.getEmailId()), new HttpHeaders(), HttpStatus.OK);  
	}
	
	@PostMapping("/interview/{canId}/{empId}")
	public ResponseEntity<String> addInterview(@PathVariable("canId") Long canId,@PathVariable("empId") Long empId,@RequestBody InterviewDto interviewDto){
		logger.debug("Entering addInterview method");
		return	new ResponseEntity<>(interviewService.addInterview(interviewDto,canId,empId), new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@PutMapping("/interview/{id}")
	public ResponseEntity<String> updateInterview(@PathVariable Long id,@RequestBody InterviewDto interviewDto){
		logger.debug("Entering updateInterview method");
		return	new ResponseEntity<>(interviewService.updateInterview(id,interviewDto), new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/interview/{id}")
	public ResponseEntity<String> deleteInterview(@PathVariable Long id){
		logger.debug("Entering deleteInterview method");
		return	new ResponseEntity<>(interviewService.deleteInterview(id), new HttpHeaders(), HttpStatus.OK);
	}
	

 
 @ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
	 	logger.error("Id not found exception for {}",e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}

