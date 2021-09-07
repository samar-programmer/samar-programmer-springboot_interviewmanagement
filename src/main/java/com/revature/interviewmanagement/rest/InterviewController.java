package com.revature.interviewmanagement.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.revature.interviewmanagement.service.InterviewService;

@RestController
@RequestMapping("/api")
public class InterviewController {

	@Autowired
	private InterviewService interviewService;
	
	@GetMapping("/interview")
	public ResponseEntity<List<Interview>> getAllInterview(){
		
		return	new ResponseEntity<>(interviewService.getAllInterview(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/interview/{id}")
	public ResponseEntity<Interview> getInterviewById(@PathVariable Long id){
			
		return	new ResponseEntity<>(interviewService.getInterviewById(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/interview/type/{type}")
	public ResponseEntity<List<Interview>> getInterviewByType(@PathVariable String type){
		
		return	new ResponseEntity<>(interviewService.getInterviewByType(type), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/interview/scheduled-date/{date}")
	public ResponseEntity<List<Interview>> getInterviewByScheduledDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate scheduledDate){
			
		return	new ResponseEntity<>(interviewService.getInterviewByScheduledDate(scheduledDate), new HttpHeaders(), HttpStatus.OK);
	}
	
	
	 @GetMapping("/interview/candidate/{canId}") 
	 public ResponseEntity<List<Interview>> getInterviewByCandidateId(@PathVariable Long canId){
		 
		 return new ResponseEntity<>(interviewService.getInterviewByCandidateId(canId), new HttpHeaders(), HttpStatus.OK);  
	} 
	 
	 @GetMapping("/interview/candidate/name/{name}")  
	 public ResponseEntity<List<Interview>> getInterviewByCandidateName(@PathVariable String name){
		
		 return new ResponseEntity<>(interviewService.getInterviewByCandidateName(name), new HttpHeaders(), HttpStatus.OK);  
	} 
	  
	 @GetMapping("/interview/candidate/phone/{phone}") 
	 public ResponseEntity<List<Interview>> getInterviewByCandidatePhone(@PathVariable("phone") String phone){  
		//if we don't give @PathVariable("phone") here, phone number couldn't be recognized as string
		 return new ResponseEntity<>(interviewService.getInterviewByCandidatePhone(phone), new HttpHeaders(), HttpStatus.OK);  
	}  
	 @GetMapping("/interview/candidate/email/{email}") 
	 public ResponseEntity<List<Interview>> getInterviewByCandidateEmail(@PathVariable String email){  
		 
		 return new ResponseEntity<>(interviewService.getInterviewByCandidateEmail(email), new HttpHeaders(), HttpStatus.OK);  
	}
	 
	 @GetMapping("/interview/candidate/role/{role}")  
	 public ResponseEntity<List<Interview>> getInterviewByCandidateRole(@PathVariable String role){
		
		 return new ResponseEntity<>(interviewService.getInterviewByCandidateRole(role), new HttpHeaders(), HttpStatus.OK);  
	} 
	 
	 @GetMapping("/interview/candidate/experience/{exp}")  
	 public ResponseEntity<List<Interview>> getInterviewByCandidateExperience(@PathVariable Integer exp){
		
		 return new ResponseEntity<>(interviewService.getInterviewByCandidateExperience(exp), new HttpHeaders(), HttpStatus.OK);  
	}
	 
	 @GetMapping("/interview/employee/id/{empId}")//this empId is auto generated id(Long) in employee entity not the employeeId(String) of employee entity
	public ResponseEntity<List<Interview>> getInterviewByEmpId(@PathVariable("empId") Long empId){ 
		 
		 return new ResponseEntity<>(interviewService.getInterviewByEmpId(empId), new HttpHeaders(), HttpStatus.OK); 
	} 
	 
	 @GetMapping("/interview/employee/employee-id/{employeeId}")//this is the employeeId(String) of employee entity not auto generated id
		public ResponseEntity<List<Interview>> getInterviewByEmployeeId(@PathVariable("employeeId") String employeeId){ 
			 
			 return new ResponseEntity<>(interviewService.getInterviewByEmployeeId(employeeId), new HttpHeaders(), HttpStatus.OK); 
		}
	 
	 @GetMapping("/interview/employee/designation-id/{destId}")
		public ResponseEntity<List<Interview>> getInterviewByDesignationId(@PathVariable String destId){ 
			 
			 return new ResponseEntity<>(interviewService.getInterviewByDesignationId(destId), new HttpHeaders(), HttpStatus.OK); 
		} 
	
	 
	 @GetMapping("/interview/employee/name/{name}")  
	 public ResponseEntity<List<Interview>> getInterviewByEmployeeName(@PathVariable String name){  
		 
		 return new ResponseEntity<>(interviewService.getInterviewByEmployeeName(name), new HttpHeaders(), HttpStatus.OK); 
	}
	 
	 @GetMapping("/interview/employee/phone/{phone}") 
	 public ResponseEntity<List<Interview>> getInterviewByEmployeePhone(@PathVariable("phone") String phone){  
		//if we don't give @PathVariable("phone") here, phone number couldn't be recognized as string
		 return new ResponseEntity<>(interviewService.getInterviewByEmployeePhone(phone), new HttpHeaders(), HttpStatus.OK);  
	}  
	 @GetMapping("/interview/employee/email/{email}") 
	 public ResponseEntity<List<Interview>> getInterviewByEmployeeEmail(@PathVariable String email){  
		 
		 return new ResponseEntity<>(interviewService.getInterviewByEmployeeEmail(email), new HttpHeaders(), HttpStatus.OK);  
	}
	
	@PostMapping("/interview/{canId}/{empId}")
	public ResponseEntity<String> addInterview(@PathVariable("canId") Long canId,@PathVariable("empId") Long empId,@RequestBody Interview interview){
		
		return	new ResponseEntity<>(interviewService.addInterview(interview,canId,empId), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping("/interview/{id}")
	public ResponseEntity<String> updateInterview(@PathVariable Long id,@RequestBody Interview interview){
		
		return	new ResponseEntity<>(interviewService.updateInterview(id,interview), new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/interview/{id}")
	public ResponseEntity<String> deleteInterview(@PathVariable Long id){
		
		return	new ResponseEntity<>(interviewService.deleteInterview(id), new HttpHeaders(), HttpStatus.OK);
	}
	

 
 @ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}

