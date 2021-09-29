package com.revature.interviewmanagement.rest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
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
import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.response.HttpResponseStatus;
import com.revature.interviewmanagement.service.CandidateService;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

/**
 * candidate controller class which handles incoming request for candidate operations
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
	 * @return details of all candidates currently in the database as a list of candidate object
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllCandidate() {
		logger.info("Entering getAllCandidate method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,candidateSerive.getAllCandidate()),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}

	/**
	 * searches the id provided and if exists, gives a particular candidate details corresponds to the id provided
	 * otherwise returns null
	 * @param id id of the candidate
	 * @return candidate details as a candidate object
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getCandidateById(@PathVariable Long id) {
		logger.info("Entering getCandidateById method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,candidateSerive.getCandidateById(id)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}

	/**
	 * searches the email id provided and gives candidate details which matches with email id of candidate and if exists,
	 * returns candidate list otherwise empty list  
	 * @param candidateDto candidate email id is passed as a POJO object of CandidateDto type
	 * @return candidate details whose email id matches with the given email id
	 */
	@PostMapping("/email")
	public ResponseEntity<HttpResponseStatus> getCandidateByEmailId(@RequestBody CandidateDto candidateDto) {
		logger.info("Entering getCandidateByEmailId method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,candidateSerive.getCandidateByEmailId(candidateDto)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}

	/**
	 * searches the phone number provided and gives candidate details which matches with phone number of candidate and if exists,
	 * returns candidate list otherwise empty list
	 * @param candidateDto candidate phone number is passed as a POJO object of CandidateDto type
	 * @return candidate details whose phone number matches with the given phone number
	 */
	@PostMapping("/phone")
	public ResponseEntity<HttpResponseStatus> getCandidateByPhoneNumber(@RequestBody CandidateDto candidateDto) {
		logger.info("Entering getCandidateByPhoneNumber method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,candidateSerive.getCandidateByPhoneNumber(candidateDto)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}

	/**
	 * searches the name provided and gives candidate details which matches with name of candidate and if exists,
	 * returns candidate list otherwise empty list
	 * @param name candidate name which is to be searched. name can be a combination of first name and last name 
	 * @return candidate details whose first name or last name or both matches with the given name
	 */
	@GetMapping("/name/{name}")
	public ResponseEntity<HttpResponseStatus> getCandidateByName(@PathVariable String name) {
		logger.info("Entering getCandidateByName method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,candidateSerive.getCandidateByName(name)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}

	/**
	 * returns candidate details which matches with the experience of the candidate if no match is found, returns 
	 * a empty list
	 * @param exp experience(in years) to be searched with candidate details
	 * @return candidate details whose experience matches with the given experience
	 */
	@GetMapping("/experience/{exp}")
	public ResponseEntity<HttpResponseStatus> getCandidateByExperience(@PathVariable Integer exp) {
		logger.info("Entering getCandidateByExperience method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,candidateSerive.getCandidateByExperience(exp)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}

	/**
	 * returns candidate details which matches with the role of the candidate if no match is found, returns 
	 * a empty list
	 * @param role job role to be searched 
	 * @return candidate details whose role matches with the given role
	 */
	@GetMapping("/role/{role}")
	public ResponseEntity<HttpResponseStatus> getCandidateByRole(@PathVariable String role) {
		logger.info("Entering getCandidateByRole method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),GET_OPERATION,candidateSerive.getCandidateByRole(role)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}

	/**
	 * takes candidate details as a CandidateDto object and saves in the database and returns the status of insert 
	 * operation
	 * @param candidateDto candidate details to be saved
	 * @return status of adding a candidate details
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addCandidate(@RequestBody CandidateDto candidateDto) {
		logger.info("Entering addCandidate method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(),candidateSerive.addCandidate(candidateDto)),HttpStatus.CREATED);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}

	/**
	 * takes the existing candidate details and updates the details and returns the status of update operation
	 * if the candidate is not present throws an exception
	 * @param candidateDto candidate details to be updated
	 * @return status of the update operation
	 */
	@PutMapping
	public ResponseEntity<HttpResponseStatus> updateCandidate(@RequestBody CandidateDto candidateDto) {
		logger.info("Entering updateCandidate method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),candidateSerive.updateCandidate(candidateDto)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
		}
		
	}

	/**
	 * takes id of the candidate details and deletes the corresponding details in the database and returns the status
	 * if candidate is not present, throws an exception
	 * @param id candidate id whose details to be deleted
	 * @return status of the delete operation
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteCandidate(@PathVariable Long id) {
		logger.info("Entering deleteCandidate method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(),candidateSerive.deleteCandidate(id)),HttpStatus.OK);
		} catch(BussinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.NOT_FOUND.value(),e.getMessage()), HttpStatus.NOT_FOUND);
		}
		
	}

}
