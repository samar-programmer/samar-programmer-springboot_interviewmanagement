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
import com.revature.interviewmanagement.model.ResultDto;
import com.revature.interviewmanagement.response.HttpResponseStatus;
import com.revature.interviewmanagement.service.ResultService;

/**
 * Result controller handles all the incoming requests for result operations
 * @author RagulR
 *
 */
@RestController
@RequestMapping("/result")
@CrossOrigin("*")
public class ResultController {

	private static final Logger logger=LogManager.getLogger(ResultController.class);
	
	@Autowired
	private ResultService resultService;
	
	/**
	 *  gives all the results details. If no data found, returns empty list
	 * @return details of all results currently in the database as a list of Result object
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllResult(){
		logger.info("Entering getAllResult method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,resultService.getAllResult()),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * searches the id provided and if exists, gives a particular result details corresponds to the id provided
	 * otherwise returns null
	 * @param id id of the result
	 * @return result details as a result object
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getResultById(@PathVariable Long id){
		logger.info("Entering getResultById method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,resultService.getResultById(id)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * searches the interview id associated with result and if exists, gives a particular result details 
	 * corresponds to the interview id provided otherwise returns null
	 * @param interviewId interview id associated with the result
	 * @return result details as a result object
	 */
	@GetMapping("/interview/{interviewId}")
	public ResponseEntity<HttpResponseStatus> getResultByInterviewId(@PathVariable Long interviewId){
		logger.info("Entering getResultByInterviewId method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,resultService.getResultByInterviewId(interviewId)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * searches the auto-generated id of employee associated with result and if exists, gives a particular result details 
	 * corresponds to the auto-generated id of employee provided otherwise returns null
	 * @param empId auto-generated id of employee which is associated with result
	 * @return result details as a list of result object
	 */
	@GetMapping("/employee/{id}")
	public ResponseEntity<HttpResponseStatus> getResultByEmployeeId(@PathVariable("id") Long empId){
		logger.info("Entering getResultByEmployeeId method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,resultService.getResultByEmployeeId(empId)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * searches the id of candidate associated with result and if exists, gives a particular result details 
	 * corresponds to the id of candidate provided otherwise returns null
	 * @param canId candidate id associated with result
	 * @return result details as a list of result object
	 */
	@GetMapping("/candidate/{id}")
	public ResponseEntity<HttpResponseStatus> getResultByCandidateId(@PathVariable("id") Long canId){
		logger.info("Entering getResultByCandidateId method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,resultService.getResultByCandidateId(canId)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * takes interview id and result details as arguments, adds result to the interview, saves in the database and returns the status of 
	 * the operation 
	 * @param interviewId id of the interview to which result needs to be added
	 * @param resultDto result details to be added
	 * @return status of insert operation
	 */
	@PostMapping("/{interviewId}")
	public ResponseEntity<HttpResponseStatus> addResult(@PathVariable Long interviewId,@RequestBody ResultDto resultDto){
		logger.info("Entering addResult method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(),resultService.addResult(interviewId,resultDto)),HttpStatus.CREATED);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * takes the result details to be updated, updates the details in the database if already exists and returns the status of 
	 * the operation otherwise throws an exception
	 * @param resultDto result details to be updated
	 * @return status of the create operation
	 */
	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateResult(@RequestBody ResultDto resultDto){
		logger.info("Entering updateResult method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),resultService.updateResult(resultDto)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		
	}
	
	/**
	 * takes the id of result details to be deleted, deletes the details in the database if already exists and returns the status of 
	 * the operation otherwise throws an exception
	 * @param id id of result details to be deleted
	 * @return status of result operation
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteResult(@PathVariable Long id){
		logger.info("Entering deleteResult method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),resultService.deleteResult(id)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		
	}
	
	/**
	 * takes candidate mail id and result details as a result object and sends the mail to the candidate
	 * and returns the status of the operation
	 * @param resultDto result details to be sent
	 * @return status of the operation
	 */
	/*Result mail end points*/
	@PostMapping("/email")
	public ResponseEntity<HttpResponseStatus> sendResultMail(@RequestBody ResultDto resultDto){
		logger.info("Entering sendResultMail method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),resultService.sendResultMail(resultDto)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
