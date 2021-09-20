package com.revature.interviewmanagement.rest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.service.EmployeeService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class EmployeeController {
	
	private static final Logger logger=LogManager.getLogger(EmployeeController.class.getName());
	
	@Autowired
	private EmployeeService employeeSerive;
	
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		logger.debug("Entering getAllEmployees method");
		return	new ResponseEntity<>(employeeSerive.getAllEmployees(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		logger.debug("Entering getEmployeeById method");
		return	new ResponseEntity<>(employeeSerive.getEmployeeById(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	
	 @PostMapping("/employee/email") 
	 public ResponseEntity<Employee> getEmployeeByEmailId(@RequestBody EmployeeDto employeeDto){
		 logger.debug("Entering getAllCandidate method");
		 return new ResponseEntity<>(employeeSerive.getEmployeeByEmailId(employeeDto.getEmailId()), new HttpHeaders(), HttpStatus.OK);  
	} 
	 
	 @PostMapping("/employee/phone")  
	 public ResponseEntity<Employee> getEmployeeByPhoneNumber(@RequestBody EmployeeDto employeeDto){
		 logger.debug("Entering getEmployeeByPhoneNumber method");
		 return new ResponseEntity<>(employeeSerive.getEmployeeByPhoneNumber(employeeDto.getPhoneNumber()), new HttpHeaders(), HttpStatus.OK);  
	} 
	  
	 @GetMapping("/employee/name/{name}") 
	 public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable String name){  
		 logger.debug("Entering getEmployeeByName method");
		 return new ResponseEntity<>(employeeSerive.getEmployeeByName(name), new HttpHeaders(), HttpStatus.OK);  
	}  
	
	 
	 @PostMapping("/employee/employee-id")
	public ResponseEntity<List<Employee>> getEmployeeByEmployeeId(@RequestBody EmployeeDto employeeDto){ 
		 logger.debug("Entering getEmployeeByEmployeeId method");
		 return new ResponseEntity<>(employeeSerive.getEmployeeByEmployeeId(employeeDto.getEmployeeId()), new HttpHeaders(), HttpStatus.OK); 
	} 
	
	 
	 @PostMapping("/employee/designation-id")  
	 public ResponseEntity<List<Employee>> getEmployeeByDesignationId(@RequestBody EmployeeDto employeeDto){  
		 logger.debug("Entering getEmployeeByDesignationId method");
		 return new ResponseEntity<>(employeeSerive.getEmployeeByDesignationId(employeeDto.getDesignationId()), new HttpHeaders(), HttpStatus.OK); 
	}
	
	@PostMapping("/employee/{credential-id}")
	public ResponseEntity<String> addEmployee(@PathVariable("credential-id") Long id,@RequestBody EmployeeDto employeeDto){
		logger.debug("Entering addEmployee method");
		return	new ResponseEntity<>(employeeSerive.addEmployee(id,employeeDto), new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable Long id,@RequestBody EmployeeDto employeeDto){
		logger.debug("Entering updateEmployee method");
		return	new ResponseEntity<>(employeeSerive.updateEmployee(id,employeeDto), new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
		logger.debug("Entering deleteEmployee method");
		return	new ResponseEntity<>(employeeSerive.deleteEmployee(id), new HttpHeaders(), HttpStatus.OK);
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
