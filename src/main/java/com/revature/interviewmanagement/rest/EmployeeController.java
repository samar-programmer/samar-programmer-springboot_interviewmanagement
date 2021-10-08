package com.revature.interviewmanagement.rest;

import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.GET_OPERATION;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.response.HttpResponseStatus;
import com.revature.interviewmanagement.service.EmployeeService;
import com.revature.interviewmanagement.util.markerinterface.AddValidation;
import com.revature.interviewmanagement.util.markerinterface.EmailValidation;
import com.revature.interviewmanagement.util.markerinterface.PhoneValidation;
import com.revature.interviewmanagement.util.markerinterface.UpdateValidation;

/**
 * employee controller class which handles incoming request for employee
 * operations
 * 
 * @author RagulR
 *
 */
@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeSerive;

	/**
	 * returns all the available employees details. If no data found, returns empty list
	 * 
	 * @return details of all employees currently in the database as a list of
	 *         employee object
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllEmployee() {
		logger.info("Entering getAllEmployees method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, employeeSerive.getAllEmployee()),
				HttpStatus.OK);
	}

	/**
	 * searches the id provided and if exists, gives a particular employee details
	 * corresponds to the id provided otherwise returns null
	 * 
	 * @param id id of the employee
	 * @return employee details as a employee object
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getEmployeeById(@PathVariable Long id) {
		logger.info("Entering getEmployeeById method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, employeeSerive.getEmployeeById(id)),
				HttpStatus.OK);
	}

	/**
	 * searches the email id provided and gives employee details which matches with
	 * email id of employee and if exists, returns employee list otherwise empty
	 * list
	 * 
	 * @param employeeDto employee email id is passed as a POJO object of
	 *                    EmployeeDto type
	 * @return employee details whose email id matches with the given email id
	 */
	@PostMapping("/email")
	public ResponseEntity<HttpResponseStatus> getEmployeeByEmailId(@RequestBody @Validated(EmailValidation.class) EmployeeDto employeeDto) {
		logger.info("Entering getAllCandidate method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				employeeSerive.getEmployeeByEmailId(employeeDto)), HttpStatus.OK);

	}

	/**
	 * searches the phone number provided and gives employee details which matches
	 * with phone number of employee and if exists, returns employee list otherwise
	 * empty list
	 * 
	 * @param employeeDto employee phone number is passed as a POJO object of
	 *                    EmployeeDto type
	 * @return employee details whose phone number matches with the given phone
	 *         number
	 */
	@PostMapping("/phone")
	public ResponseEntity<HttpResponseStatus> getEmployeeByPhoneNumber(@RequestBody @Validated(PhoneValidation.class) EmployeeDto employeeDto) {
		logger.info("Entering getEmployeeByPhoneNumber method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				employeeSerive.getEmployeeByPhoneNumber(employeeDto)), HttpStatus.OK);

	}

	/**
	 * searches the name provided and gives employee details which matches with name
	 * of employee and if exists, returns employee list otherwise empty list
	 * 
	 * @param name employee name which is to be searched. name can be a combination
	 *             of first name and last name
	 * @return employee details whose first name or last name or both matches with
	 *         the given name
	 */
	@GetMapping("/name/{name}")
	public ResponseEntity<HttpResponseStatus> getEmployeeByName(@PathVariable String name) {
		logger.info("Entering getEmployeeByName method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, employeeSerive.getEmployeeByName(name)),
				HttpStatus.OK);
	}

	/**
	 * searches the designation provided and gives employee details which matches
	 * with designation of employee and if exists, returns employee list otherwise
	 * empty list
	 * 
	 * @param designation which designation to be searched
	 * @return employee details whose designation matches with the given designation
	 */
	@GetMapping("/designation/{designation}")
	public ResponseEntity<HttpResponseStatus> getEmployeeByDesignation(@PathVariable String designation) {
		logger.info("Entering getEmployeeByDesignation method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				employeeSerive.getEmployeeByDesignation(designation)), HttpStatus.OK);

	}

	/**
	 * takes status and return list of employee status according to the status, the
	 * status can be available or left
	 * 
	 * @param status status of the employee to be searched
	 * @return employee details whose status matches with the given status
	 */
	@GetMapping("/status/{status}")
	public ResponseEntity<HttpResponseStatus> getEmployeeByStatus(@PathVariable String status) {
		logger.info("Entering getEmployeeByStatus method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				employeeSerive.getEmployeeByStatus(status)), HttpStatus.OK);
	}

	/**
	 * takes no arguments and return list of designations stored in the database
	 * 
	 * @return list of designation
	 */
	@GetMapping("/designation")
	public ResponseEntity<HttpResponseStatus> getAllDesignation() {
		logger.info("Entering getAllDesignation method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, employeeSerive.getAllDesignation()),
				HttpStatus.OK);
	}

	/**
	 * takes employee details as a EmployeeDto object and saves in the database and
	 * returns the status of insert operation
	 * 
	 * @param employeeDto employee details to be saved
	 * @return status of adding a employee details
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addEmployee(@RequestBody @Validated(AddValidation.class) EmployeeDto employeeDto) {
		logger.info("Entering addEmployee method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.CREATED.value(), employeeSerive.addEmployee(employeeDto)),
				HttpStatus.CREATED);

	}

	/**
	 * takes the existing employee details and updates the details and returns the
	 * status of update operation if the employee is not present throws an exception
	 * 
	 * @param employeeDto employee details to be updated
	 * @return status of the update operation
	 */
	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateEmployee(@RequestBody  @Validated({AddValidation.class,UpdateValidation.class}) EmployeeDto employeeDto) {
		logger.info("Entering updateEmployee method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), employeeSerive.updateEmployee(employeeDto)),
				HttpStatus.OK);
	}

	/**
	 * takes id of the employee details and deletes the corresponding details in the
	 * database and returns the status if employee is not present, throws an
	 * exception
	 * 
	 * @param id employee id(auto-generated id) whose details to be deleted
	 * @return status of the delete operation
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteEmployee(@PathVariable Long id) {
		logger.info("Entering deleteEmployee method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), employeeSerive.deleteEmployee(id)),
				HttpStatus.OK);

	}

}
