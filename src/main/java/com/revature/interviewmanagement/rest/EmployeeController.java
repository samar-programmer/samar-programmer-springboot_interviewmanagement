package com.revature.interviewmanagement.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeSerive;
	
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		
		return	new ResponseEntity<>(employeeSerive.getAllEmployees(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
			
		return	new ResponseEntity<>(employeeSerive.getEmployeeById(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	
	 @GetMapping("/employee/email/{email}") 
	 public ResponseEntity<Employee> getEmployeeByEmailId(@PathVariable String email){
		 
		 return new ResponseEntity<>(employeeSerive.getEmployeeByEmailId(email), new HttpHeaders(), HttpStatus.OK);  
	} 
	 
	 @GetMapping("/employee/phone/{phone-number}")  
	 public ResponseEntity<Employee> getEmployeeByPhoneNumber(@PathVariable("phone-number") String phoneNumber){
		//if we don't give @PathVariable("phone-number") here, phone number couldn't be recognized as string
		 return new ResponseEntity<>(employeeSerive.getEmployeeByPhoneNumber(phoneNumber), new HttpHeaders(), HttpStatus.OK);  
	} 
	  
	 @GetMapping("/employee/first-name/{fname}") 
	 public ResponseEntity<List<Employee>> getEmployeeByFirstName(@PathVariable String fname){  
		 
		 return new ResponseEntity<>(employeeSerive.getEmployeeByFirstName(fname), new HttpHeaders(), HttpStatus.OK);  
	}  
	 @GetMapping("/employee/last-name/{lname}") 
	 public ResponseEntity<List<Employee>> getEmployeeByLastName(@PathVariable String lname){  
		 
		 return new ResponseEntity<>(employeeSerive.getEmployeeByLastName(lname), new HttpHeaders(), HttpStatus.OK);  
	}
	 
	 @GetMapping("/employee/employee-id/{empId}")
	public ResponseEntity<List<Employee>> getEmployeeByEmployeeId(@PathVariable String empId){ 
		 
		 return new ResponseEntity<>(employeeSerive.getEmployeeByEmployeeId(empId), new HttpHeaders(), HttpStatus.OK); 
	} 
	
	 
	 @GetMapping("/employee/designation-id/{destId}")  
	 public ResponseEntity<List<Employee>> getEmployeeByDesignationId(@PathVariable String destId){  
		 
		 return new ResponseEntity<>(employeeSerive.getEmployeeByDesignationId(destId), new HttpHeaders(), HttpStatus.OK); 
	}
	
	@PostMapping("/employee")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
		
		return	new ResponseEntity<>(employeeSerive.addEmployee(employee), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
		
		return	new ResponseEntity<>(employeeSerive.updateEmployee(id,employee), new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
		
		return	new ResponseEntity<>(employeeSerive.deleteEmployee(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	
	
	
 @ExceptionHandler(DuplicateIdException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateIdException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}	
 
 @ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}
