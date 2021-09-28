package com.revature.interviewmanagement.rest;

import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.GET_OPERATION;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.interviewmanagement.exception.BussinessLogicException;
import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.response.HttpResponseStatus;
import com.revature.interviewmanagement.service.EmployeeService;

/**
 * employee controller class which handles incoming request for employee operations
 * @author RagulR
 *
 */
@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {
	
	private static final Logger logger=LogManager.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeSerive;
	
	/**
	 * returns all the employees details. If no data found, returns empty list
	 * @return details of all employees currently in the database as a list of employee object
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllEmployee(){
		logger.info("Entering getAllEmployees method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,employeeSerive.getAllEmployee()),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * searches the id provided and if exists, gives a particular employee details corresponds to the id provided
	 * otherwise returns null
	 * @param id id of the employee
	 * @return employee details as a employee object
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getEmployeeById(@PathVariable Long id){
		logger.info("Entering getEmployeeById method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,employeeSerive.getEmployeeById(id)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * searches the email id provided and gives employee details which matches with email id of employee and if exists,
	 * returns employee list otherwise empty list 
	 * @param employeeDto employee email id is passed as a POJO object of EmployeeDto type
	 * @return employee details whose email id matches with the given email id
	 */
	 @PostMapping("/email") 
	 public ResponseEntity<HttpResponseStatus> getEmployeeByEmailId(@RequestBody EmployeeDto employeeDto){
		 logger.info("Entering getAllCandidate method");
		 try {
				return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,employeeSerive.getEmployeeByEmailId(employeeDto)),HttpStatus.OK);
			} catch(BussinessLogicException e) {
				return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		  
	} 
	 
	 /**
	  * searches the phone number provided and gives employee details which matches with phone number of employee and if exists,
	 * returns employee list otherwise empty list
	  * @param employeeDto employee phone number is passed as a POJO object of EmployeeDto type
	  * @return employee details whose phone number matches with the given phone number
	  */
	 @PostMapping("/phone")  
	 public ResponseEntity<HttpResponseStatus> getEmployeeByPhoneNumber(@RequestBody EmployeeDto employeeDto){
		 logger.info("Entering getEmployeeByPhoneNumber method");
		 try {
				return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,employeeSerive.getEmployeeByPhoneNumber(employeeDto)),HttpStatus.OK);
			} catch(BussinessLogicException e) {
				return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	} 
	  
	 /**
	  * searches the name provided and gives employee details which matches with name of employee and if exists,
	 * returns employee list otherwise empty list
	  * @param name employee name which is to be searched. name can be a combination of first name and last name 
	  * @return employee details whose first name or last name or both matches with the given name
	  */
	 @GetMapping("/name/{name}") 
	 public ResponseEntity<HttpResponseStatus> getEmployeeByName(@PathVariable String name){  
		 logger.info("Entering getEmployeeByName method");
		 try {
				return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,employeeSerive.getEmployeeByName(name)),HttpStatus.OK);
			} catch(BussinessLogicException e) {
				return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}  
	
	 /**
	  * searches the employee id provided and gives employee details which matches with employee id of employee and if exists,
	 * returns employee list otherwise empty list
	  * @param employeeDto employee id which is to be searched is passed as a POJO object of type EmployeeDto
	  * @return employee details whose employee id matches with the given employee id
	  */
	 @PostMapping("/employee-id")
	 public ResponseEntity<HttpResponseStatus> getEmployeeByEmployeeId(@RequestBody EmployeeDto employeeDto){ 
		 logger.info("Entering getEmployeeByEmployeeId method");
		 try {
				return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,employeeSerive.getEmployeeByEmployeeId(employeeDto)),HttpStatus.OK);
			} catch(BussinessLogicException e) {
				return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	} 
	
	 
	 /**
	  * searches the designation id provided and gives employee details which matches with designation id of employee and if exists,
	 * returns employee list otherwise empty list
	  * @param employeeDto designation id which is to be searched is passed as a POJO object of type EmployeeDto
	  * @return employee details whose designation id matches with the given designation id
	  */
	 @PostMapping("/designation-id")  
	 public ResponseEntity<HttpResponseStatus> getEmployeeByDesignationId(@RequestBody EmployeeDto employeeDto){  
		 logger.info("Entering getEmployeeByDesignationId method");
		 try {
				return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,employeeSerive.getEmployeeByDesignationId(employeeDto)),HttpStatus.OK);
			} catch(BussinessLogicException e) {
				return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	 /**
	  * takes employee details as a EmployeeDto object and saves in the database and returns the status of insert 
	 * operation
	  * @param employeeDto employee details to be saved
	  * @return status of adding a employee details
	  */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addEmployee(@RequestBody EmployeeDto employeeDto){
		logger.info("Entering addEmployee method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(),employeeSerive.addEmployee(employeeDto)),HttpStatus.CREATED);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * takes the existing employee details and updates the details and returns the status of update operation
	 * if the employee is not present throws an exception
	 * @param employeeDto employee details to be updated
	 * @return status of the update operation
	 */
	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateEmployee(@RequestBody EmployeeDto employeeDto){
		logger.info("Entering updateEmployee method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),employeeSerive.updateEmployee(employeeDto)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		
	}
	
	/**
	 * takes id of the employee details and deletes the corresponding details in the database and returns the status
	 * if employee is not present, throws an exception
	 * @param id employee id(auto-generated id) whose details to be deleted
	 * @return status of the delete operation
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteEmployee(@PathVariable Long id){
		logger.info("Entering deleteEmployee method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),employeeSerive.deleteEmployee(id)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		
	}
	

	
}
