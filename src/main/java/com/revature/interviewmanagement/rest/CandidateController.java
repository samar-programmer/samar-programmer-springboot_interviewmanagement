package com.revature.interviewmanagement.rest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
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

import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.response.HttpResponseStatus;
import com.revature.interviewmanagement.service.CandidateService;
import com.revature.interviewmanagement.util.markerinterface.AddValidation;
import com.revature.interviewmanagement.util.markerinterface.EmailValidation;
import com.revature.interviewmanagement.util.markerinterface.JobRoleValidation;
import com.revature.interviewmanagement.util.markerinterface.PhoneValidation;
import com.revature.interviewmanagement.util.markerinterface.UpdateValidation;

import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

/**
 * candidate controller class which handles incoming request for candidate
 * operations
 * 
 * @author RagulR
 *
 */
@RestController
@RequestMapping("/candidate")
@CrossOrigin("*")
public class CandidateController {

	private static final Logger logger = LogManager.getLogger(CandidateController.class);

	@Autowired
	private CandidateService candidateSerive;

	/**
	 * returns all the candidates details. If no data found, returns empty list
	 * 
	 * @return details of all candidates currently in the database as a list of
	 *         candidate object
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllCandidate() {
		logger.info("Entering getAllCandidate method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, candidateSerive.getAllCandidate()),
				HttpStatus.OK);

	}

	/**
	 * searches the id provided and if exists, gives a particular candidate details
	 * corresponds to the id provided otherwise returns null
	 * 
	 * @param id id of the candidate
	 * @return candidate details as a candidate object
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getCandidateById(@PathVariable Long id) {
		logger.info("Entering getCandidateById method");
		
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, candidateSerive.getCandidateById(id)),
					HttpStatus.OK);
		
		

	}

	/**
	 * searches the email id provided and gives candidate details which matches with
	 * email id of candidate and if exists, returns candidate list otherwise empty
	 * list
	 * 
	 * @param candidateDto candidate email id is passed as a POJO object of
	 *                     CandidateDto type
	 * @return candidate details whose email id matches with the given email id
	 */
	@PostMapping("/email")
	public ResponseEntity<HttpResponseStatus> getCandidateByEmailId(@RequestBody @Validated(EmailValidation.class) CandidateDto candidateDto) {
		logger.info("Entering getCandidateByEmailId method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				candidateSerive.getCandidateByEmailId(candidateDto)), HttpStatus.OK);

	}

	/**
	 * searches the phone number provided and gives candidate details which matches
	 * with phone number of candidate and if exists, returns candidate list
	 * otherwise empty list
	 * 
	 * @param candidateDto candidate phone number is passed as a POJO object of
	 *                     CandidateDto type
	 * @return candidate details whose phone number matches with the given phone
	 *         number
	 */
	@PostMapping("/phone")
	public ResponseEntity<HttpResponseStatus> getCandidateByPhoneNumber(@RequestBody @Validated(PhoneValidation.class)  CandidateDto candidateDto) {
		logger.info("Entering getCandidateByPhoneNumber method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				candidateSerive.getCandidateByPhoneNumber(candidateDto)), HttpStatus.OK);

	}

	/**
	 * searches the name provided and gives candidate details which matches with
	 * name of candidate and if exists, returns candidate list otherwise empty list
	 * 
	 * @param name candidate name which is to be searched. name can be a combination
	 *             of first name and last name
	 * @return candidate details whose first name or last name or both matches with
	 *         the given name
	 */
	@GetMapping("/name/{name}")
	public ResponseEntity<HttpResponseStatus> getCandidateByName(@PathVariable String name) {
		logger.info("Entering getCandidateByName method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, candidateSerive.getCandidateByName(name)),
				HttpStatus.OK);

	}

	/**
	 * returns candidate details which matches with the experience of the candidate
	 * if no match is found, returns a empty list
	 * 
	 * @param exp experience(in years) to be searched with candidate details
	 * @return candidate details whose experience matches with the given experience
	 */
	@GetMapping("/experience/{exp}")
	public ResponseEntity<HttpResponseStatus> getCandidateByExperience(@PathVariable String exp) {
		logger.info("Entering getCandidateByExperience method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				candidateSerive.getCandidateByExperience(exp)), HttpStatus.OK);

	}

	/**
	 * takes no arguments and returns list of available experience ranges from the
	 * database
	 * 
	 * @return list of experience ranges
	 */
	@GetMapping("/experience")
	public ResponseEntity<HttpResponseStatus> getAllExperience() {
		logger.info("Entering getExperience method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, candidateSerive.getAllExperience()),
				HttpStatus.OK);

	}

	/**
	 * takes no arguments and returns list of available job roles ranges from the
	 * database
	 * 
	 * @return list of job roles
	 */
	@GetMapping("/role")
	public ResponseEntity<HttpResponseStatus> getAllJobRole() {
		logger.info("Entering getAllJobRole method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, candidateSerive.getAllJobRole()),
				HttpStatus.OK);

	}

	/**
	 * returns candidate details which matches with the role of the candidate if no
	 * match is found, returns a empty list
	 * 
	 * @param role job role to be searched
	 * @return candidate details whose role matches with the given role
	 */
	@GetMapping("/role/{role}")
	public ResponseEntity<HttpResponseStatus> getCandidateByRole(@PathVariable String role) {
		logger.info("Entering getCandidateByRole method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION, candidateSerive.getCandidateByRole(role)),
				HttpStatus.OK);

	}

	/**
	 * takes candidate details such as email id, job role and validates whether the
	 * candidate can apply for the specified role
	 * 
	 * @param candidateDto candidate details as candidate dto object
	 * @return boolean value based on the validation
	 */
	@PostMapping("/validate-role")
	public ResponseEntity<HttpResponseStatus> validateJobRole(@RequestBody @Validated({EmailValidation.class,JobRoleValidation.class}) CandidateDto candidateDto) {
		logger.info("Entering getCandidateByRole method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), GET_OPERATION,
				candidateSerive.validateJobRole(candidateDto)), HttpStatus.OK);

	}

	/**
	 * takes candidate details as a CandidateDto object and saves in the database
	 * and returns the status of insert operation
	 * 
	 * @param candidateDto candidate details to be saved
	 * @return status of adding a candidate details
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addCandidate(@RequestBody @Validated(AddValidation.class) CandidateDto candidateDto) {
		logger.info("Entering addCandidate method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.CREATED.value(), candidateSerive.addCandidate(candidateDto)),
				HttpStatus.CREATED);

	}

	/**
	 * takes the existing candidate details and updates the details and returns the
	 * status of update operation if the candidate is not present throws an
	 * exception
	 * 
	 * @param candidateDto candidate details to be updated
	 * @return status of the update operation
	 */
	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateCandidate(@RequestBody @Validated({AddValidation.class,UpdateValidation.class}) CandidateDto candidateDto) {
		logger.info("Entering updateCandidate method");

		return new ResponseEntity<>(
				new HttpResponseStatus(HttpStatus.OK.value(), candidateSerive.updateCandidate(candidateDto)),
				HttpStatus.OK);

	}

	/**
	 * takes id of the candidate details and deletes the corresponding details in
	 * the database and returns the status if candidate is not present, throws an
	 * exception
	 * 
	 * @param id candidate id whose details to be deleted
	 * @return status of the delete operation
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteCandidate(@PathVariable Long id) {
		logger.info("Entering deleteCandidate method");

		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), candidateSerive.deleteCandidate(id)),
				HttpStatus.OK);

	}

}
