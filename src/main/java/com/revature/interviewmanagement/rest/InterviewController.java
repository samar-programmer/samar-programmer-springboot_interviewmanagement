package com.revature.interviewmanagement.rest;

import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.GET_OPERATION;

import java.time.LocalDate;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.model.InterviewDto;
import com.revature.interviewmanagement.response.HttpResponseStatus;
import com.revature.interviewmanagement.service.InterviewService;
import com.revature.interviewmanagement.util.markerinterface.AddValidation;
import com.revature.interviewmanagement.util.markerinterface.EmailValidation;
import com.revature.interviewmanagement.util.markerinterface.MailValidation;
import com.revature.interviewmanagement.util.markerinterface.PhoneValidation;
import com.revature.interviewmanagement.util.markerinterface.UpdateValidation;

/**
 * Interview controller class which handles incoming requests for interview
 * operations
 * 
 * @author RagulR
 *
 */
@RestController
@RequestMapping("/interview")
@CrossOrigin("*")
public class InterviewController {

	private static final Logger logger = LogManager.getLogger(InterviewController.class);

	@Autowired
	private InterviewService interviewService;

	/**
	 * gives all the interviews details. If no data found, returns empty list
	 * 
	 * @return details of all interviews currently in the database as a list of
	 *         Interview object
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllInterview() {
		logger.info("Entering getAllInterview method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, interviewService.getAllInterview()),
				HttpStatus.OK);

	}

	/**
	 * takes no arguments and return available interview types stored in the
	 * database
	 * 
	 * @return list of interview types
	 */
	@GetMapping("/interview-type")
	public ResponseEntity<HttpResponseStatus> getAllInterviewType() {
		logger.info("Entering getAllInterviewType method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, interviewService.getAllInterviewType()),
				HttpStatus.OK);
	}

	/**
	 * searches the id provided and if exists, gives a particular interview details
	 * corresponds to the id provided otherwise returns null
	 * 
	 * @param id id of the interview
	 * @return interview details as a interview object
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getInterviewById(@PathVariable Long id) {
		logger.info("Entering getInterviewById method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, interviewService.getInterviewById(id)),
				HttpStatus.OK);
	}

	/**
	 * searches the type of interview provided and if exists, gives a particular
	 * interview details corresponds to the type provided otherwise returns null
	 * 
	 * @param type type of the interview to be searched
	 * @return interview details as a list of interview object
	 */
	@GetMapping("/type/{type}")
	public ResponseEntity<HttpResponseStatus> getInterviewByType(@PathVariable String type) {
		logger.info("Entering getInterviewByType method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, interviewService.getInterviewByType(type)),
				HttpStatus.OK);
	}

	/**
	 * searches the scheduled date of interview provided and if exists, gives a
	 * particular interview details corresponds to the scheduled date provided
	 * otherwise returns null
	 * 
	 * @param scheduledDate scheduled date of the interview to be searched
	 * @return interview details as a list of interview object
	 */
	@GetMapping("/scheduled-date/{date}")
	public ResponseEntity<HttpResponseStatus> getInterviewByScheduledDate(
			@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate scheduledDate) {
		logger.info("Entering getInterviewByScheduledDate method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByScheduledDate(scheduledDate)), HttpStatus.OK);

	}

	/**
	 * searches the candidate id associated interview provided and if exists, gives
	 * a particular interview details corresponds to the candidate id provided
	 * otherwise returns null
	 * 
	 * @param canId candidate id of a candidate which is associated with the
	 *              interview
	 * @return interview details as a list of interview object
	 */
	@GetMapping("/candidate/{canId}")
	public ResponseEntity<HttpResponseStatus> getInterviewByCandidateId(@PathVariable Long canId) {
		logger.info("Entering getInterviewByCandidateId method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByCandidateId(canId)), HttpStatus.OK);

	}

	/**
	 * searches the candidate name associated interview provided and if exists,
	 * gives a particular interview details corresponds to the candidate name
	 * provided otherwise returns null
	 * 
	 * @param name candidate name(name can be first name, last name or both) of a
	 *             candidate which is associated with the interview
	 * @return interview details as a list of interview object
	 */
	@GetMapping("/candidate/name/{name}")
	public ResponseEntity<HttpResponseStatus> getInterviewByCandidateName(@PathVariable String name) {
		logger.info("Entering getInterviewByCandidateName method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByCandidateName(name)), HttpStatus.OK);

	}

	/**
	 * searches the candidate phone number associated interview provided and if
	 * exists, gives a particular interview details corresponds to the candidate
	 * phone number provided otherwise returns null
	 * 
	 * @param candidateDto candidate phone number of a candidate which is associated
	 *                     with the interview is passed as a CandidateDto object
	 * @return interview details as a list of interview object
	 */
	@PostMapping("/candidate/phone")
	public ResponseEntity<HttpResponseStatus> getInterviewByCandidatePhone(@RequestBody @Validated({PhoneValidation.class}) CandidateDto candidateDto) {
		logger.info("Entering getInterviewByCandidatePhone method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByCandidatePhone(candidateDto)), HttpStatus.OK);
	}

	/**
	 * searches the candidate email id associated interview provided and if exists,
	 * gives a particular interview details corresponds to the candidate email id
	 * provided otherwise returns null
	 * 
	 * @param candidateDto candidate email id of a candidate which is associated
	 *                     with the interview is passed as a CandidateDto object
	 * @return interview details as a list of interview object
	 */
	@PostMapping("/candidate/email")
	public ResponseEntity<HttpResponseStatus> getInterviewByCandidateEmailId(@RequestBody @Validated({EmailValidation.class}) CandidateDto candidateDto) {
		logger.info("Entering getInterviewByCandidateEmail method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByCandidateEmailId(candidateDto)), HttpStatus.OK);

	}

	/**
	 * searches the candidate role associated interview provided and if exists,
	 * gives a particular interview details corresponds to the candidate role
	 * provided otherwise returns null
	 * 
	 * @param role candidate role of a candidate which is associated with the
	 *             interview
	 * @return interview details as a list of interview object
	 */
	@GetMapping("/candidate/role/{role}")
	public ResponseEntity<HttpResponseStatus> getInterviewByCandidateRole(@PathVariable String role) {
		logger.info("Entering getInterviewByCandidateRole method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByCandidateRole(role)), HttpStatus.OK);
	}

	/**
	 * searches the candidate experience associated interview provided and if
	 * exists, gives a particular interview details corresponds to the candidate
	 * experience provided otherwise returns null
	 * 
	 * @param exp candidate experience of a candidate which is associated with the
	 *            interview
	 * @return interview details as a list of interview object
	 */
	@GetMapping("/candidate/experience/{exp}")
	public ResponseEntity<HttpResponseStatus> getInterviewByCandidateExperience(@PathVariable String exp) {
		logger.info("Entering getInterviewByCandidateExperience method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByCandidateExperience(exp)), HttpStatus.OK);

	}

	/**
	 * searches the auto-generated id of employee associated interview provided and
	 * if exists, gives a particular interview details corresponds to the employee
	 * auto-generated id provided otherwise returns null
	 * 
	 * @param empId auto-generated id of an employee which is associated with the
	 *              interview
	 * @return interview details as a list of interview object
	 */
	@GetMapping("/employee/{id}")
	public ResponseEntity<HttpResponseStatus> getInterviewByEmployeeId(@PathVariable Long id) {
		logger.info("Entering getInterviewByEmployeeId method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByEmployeeId(id)), HttpStatus.OK);

	}

	/**
	 * searches the designation of employee associated interview provided and if
	 * exists, gives a particular interview details corresponds to the designation
	 * provided otherwise returns null
	 * 
	 * @param designation designation of an employee to be searched
	 * @return interview details as a list of interview object
	 */
	@GetMapping("/employee/designation/{designation}")
	public ResponseEntity<HttpResponseStatus> getInterviewByEmployeeDesignation(@PathVariable String designation) {
		logger.info("Entering getInterviewByEmployeeDesignation method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByEmployeeDesignation(designation)), HttpStatus.OK);

	}

	/**
	 * takes status and return list of employee status according to the status, the
	 * status can be available or left
	 * 
	 * @param status status of the employee to be searched
	 * @return employee details whose status matches with the given status
	 */
	@GetMapping("/employee/status/{status}")
	public ResponseEntity<HttpResponseStatus> getInterviewByEmployeeStatus(@PathVariable String status) {
		logger.info("Entering getInterviewByEmployeeStatus method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByEmployeeStatus(status)), HttpStatus.OK);
	}

	/**
	 * searches the name of employee associated interview provided and if exists,
	 * gives a particular interview details corresponds to the name provided
	 * otherwise returns null
	 * 
	 * @param name name of employee(name can be first name, last name or both) to be
	 *             searched
	 * @return interview details as a list of interview object
	 */
	@GetMapping("/employee/name/{name}")
	public ResponseEntity<HttpResponseStatus> getInterviewByEmployeeName(@PathVariable String name) {
		logger.info("Entering getInterviewByEmployeeName method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByEmployeeName(name)), HttpStatus.OK);
	}

	/**
	 * searches the phone number of employee associated interview provided and if
	 * exists, gives a particular interview details corresponds to the phone number
	 * provided otherwise returns null
	 * 
	 * @param employeeDto phone number of employee to be searched is passed as an
	 *                    EmployeeDto object
	 * @return interview details as a list of interview object
	 */
	@PostMapping("/employee/phone")
	public ResponseEntity<HttpResponseStatus> getInterviewByEmployeePhone(@RequestBody @Validated({PhoneValidation.class}) EmployeeDto employeeDto) {
		logger.info("Entering getInterviewByEmployeePhone method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByEmployeePhone(employeeDto)), HttpStatus.OK);

	}

	/**
	 * searches the email id of employee associated interview provided and if
	 * exists, gives a particular interview details corresponds to the email id
	 * provided otherwise returns null
	 * 
	 * @param employeeDto email id of employee to be searched is passed as an
	 *                    EmployeeDto object
	 * @return interview details as a list of interview object
	 */
	@PostMapping("/employee/email")
	public ResponseEntity<HttpResponseStatus> getInterviewByEmployeeEmailId(@RequestBody @Validated({EmailValidation.class}) EmployeeDto employeeDto) {
		logger.info("Entering getInterviewByEmployeeEmail method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				interviewService.getInterviewByEmployeeEmailId(employeeDto)), HttpStatus.OK);

	}

	/**
	 * takes candidate id, employee auto-generated id and interview details and
	 * saves in the database and returns status of the insert operation
	 * 
	 * @param canId        id of candidate who is going to have the interview
	 * @param empId        auto-generated id of employee who is going to take the
	 *                     interview
	 * @param interviewDto interview details to be added are passed as an
	 *                     InterviewDto object
	 * @return status of the insert operation
	 */
	@PostMapping("/{canId}/{empId}")
	public ResponseEntity<HttpResponseStatus> addInterview(@PathVariable("canId") Long canId,
			@PathVariable("empId") Long empId, @RequestBody @Validated({AddValidation.class}) InterviewDto interviewDto) {
		logger.info("Entering addInterview method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(),
				interviewService.addInterview(interviewDto, canId, empId)), HttpStatus.CREATED);

	}

	/**
	 * takes interview details and updates the interview details in the database if
	 * it is already present, otherwise throws an exception
	 * 
	 * @param interviewDto interview details to be updated are passed as an
	 *                     InterviewDto object
	 * @return status of the update operation
	 */
	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateInterview(@RequestBody @Validated({AddValidation.class,UpdateValidation.class}) InterviewDto interviewDto) {
		logger.info("Entering updateInterview method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), interviewService.updateInterview(interviewDto)),
				HttpStatus.OK);
	}

	/**
	 * takes id of the interview as a argument and deletes the interview if it is
	 * already present, otherwise throws an exception
	 * 
	 * @param id id of the interview to be deleted
	 * @return status of the delete operation
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteInterview(@PathVariable Long id) {
		logger.info("Entering deleteInterview method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), interviewService.deleteInterview(id)),
				HttpStatus.OK);

	}

	/* Interview mail end points */
	/**
	 * takes candidate id and auto-generated id of employee and interview details
	 * and sends email to both candidate and employee about the interview and
	 * returns the status of the operation
	 * 
	 * @param canId        id of candidate who is going to have the interview
	 * @param empId        auto-generated id of employee who is going to take the
	 *                     interview
	 * @param interviewDto interview details are passed as an InterviewDto object
	 * @return status of the operation
	 */
	@PostMapping("/scheduled-interview/{canId}/{empId}")
	public ResponseEntity<HttpResponseStatus> sendScheduledInterviewMail(@PathVariable("canId") Long canId,
			@PathVariable("empId") Long empId, @RequestBody @Validated({MailValidation.class}) InterviewDto interviewDto) {
		logger.info("Entering sendScheduledInterviewMail method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),
				interviewService.sendScheduledInterviewMail(canId, empId, interviewDto)), HttpStatus.OK);

	}

	/**
	 * takes candidate id and auto-generated id of employee and rescheduled
	 * interview details and sends email to both candidate and employee about the
	 * rescheduled interview and returns the status of the operation
	 * 
	 * @param canId        id of candidate who is going to have the rescheduled
	 *                     interview
	 * @param empId        auto-generated id of employee who is going to take the
	 *                     rescheduled interview
	 * @param interviewDto rescheduled interview details are passed as an
	 *                     InterviewDto object
	 * @return status of the operation
	 */
	@PutMapping("/rescheduled-interview/{canId}/{empId}")
	public ResponseEntity<HttpResponseStatus> sendRescheduledInterviewMail(@PathVariable("canId") Long canId,
			@PathVariable("empId") Long empId, @RequestBody @Validated({MailValidation.class}) InterviewDto interviewDto) {
		logger.info("Entering sendRescheduledInterviewMail method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),
				interviewService.sendRescheduledInterviewMail(canId, empId, interviewDto)), HttpStatus.OK);

	}

}
