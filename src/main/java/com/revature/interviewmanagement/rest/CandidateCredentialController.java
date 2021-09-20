package com.revature.interviewmanagement.rest;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.revature.interviewmanagement.entity.credentials.CandidateCredential;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.model.credentials.CandidateCredentialDto;
import com.revature.interviewmanagement.model.credentials.RecoveryPasswordDto;
import com.revature.interviewmanagement.service.CandidateCredentialService;
import com.revature.interviewmanagement.service.RecoveryPasswordService;
import com.revature.interviewmanagement.utils.MailSenderUtil;
import com.revature.interviewmanagement.utils.PasswordHasherUtil;
import com.revature.interviewmanagement.utils.RandomCodeGeneratorUtil;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class CandidateCredentialController {
	
	private static final Logger logger=LogManager.getLogger(CandidateCredentialController.class.getName());
	
	private String forgotPasswordCode=null;
	
	@Autowired
	private CandidateCredentialService candidateCredentialService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private RecoveryPasswordService recoveryPasswordService;
	
	
	 @PostMapping("/candidate/credential/login") 
	 public ResponseEntity<Integer> validateCredential(@RequestBody CandidateCredentialDto candidateCredentialDto){  
		 //return values as follows 1- user exits and correct pass, 0-user exits but wrong pass, -1 -user doesn't exist
		 Integer result=null;
		 logger.debug("Entering validateCredential method");
		 CandidateCredential savedCredential=  candidateCredentialService.validateCredential(candidateCredentialDto);
		 if(savedCredential!=null) {
			result=(PasswordHasherUtil.validatePassword(savedCredential.getPassword(),candidateCredentialDto.getPassword(),savedCredential.getSalt())?1:0);
		
		 }
		 else {
			result=-1;
		 }
		 
		 return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);  
	}
	 
	 private void sendCodeUtil(String email) {
		 String code=RandomCodeGeneratorUtil.randomCode();
		 forgotPasswordCode=code;
		 String message="Please enter the following code \n\n"+code+"\n\nThis code will active for 1 hour. If it has expired you can request a new code from the verfication page.";
		 MailSenderUtil.sendMail(javaMailSender,email,"Your candidate account recovery verification code", message);
	 }
	 
	 @PostMapping("/candidate/credential/check-email") 
	 public ResponseEntity<Boolean> validateEmail(@RequestBody RecoveryPasswordDto recoveryPasswordDto){ //this method validates email and sends account recovery code if email already exists
		 logger.debug("Entering validateEmailCredential method");
		 Boolean result=candidateCredentialService.validateEmail(recoveryPasswordDto.getEmailId());
		 if(Boolean.TRUE.equals(result)) {
			 sendCodeUtil(recoveryPasswordDto.getEmailId());
			 recoveryPasswordService.addCode(recoveryPasswordDto,forgotPasswordCode);
		 }
		 
		 return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);  
	}
	 
	
	 
	 @PutMapping("/candidate/credential/resend-code")
	 public ResponseEntity<Boolean> resendCode(@RequestBody RecoveryPasswordDto recoveryPasswordDto){
		 Boolean result=false;
		 sendCodeUtil(recoveryPasswordDto.getEmailId());
		 result=true;
		 recoveryPasswordService.addCode(recoveryPasswordDto,forgotPasswordCode);
		 return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);//true can be replaced with better approach
	 }
	 
	 @PostMapping("/candidate/credential/check-code")
	 public ResponseEntity<Boolean> validateCode(@RequestBody RecoveryPasswordDto recoveryPasswordDto){
		 
		 return new ResponseEntity<>(recoveryPasswordService.validateCode(recoveryPasswordDto), new HttpHeaders(), HttpStatus.OK); 
	 }
	 
	 @PutMapping("/candidate/credential/reset-password")
		public ResponseEntity<Boolean> resetPassword(@RequestBody CandidateCredentialDto candidateCredentialDto){
			logger.debug("Entering resetPassword method");
			String []tempCredentials=PasswordHasherUtil.storePassword(candidateCredentialDto.getPassword());
			candidateCredentialDto.setPassword(tempCredentials[0]);
			candidateCredentialDto.setSalt(tempCredentials[1]);
			return	new ResponseEntity<>(candidateCredentialService.resetPassword(candidateCredentialDto), new HttpHeaders(), HttpStatus.OK);
		}
	 
	
 	@PostMapping("/candidate/credential/signup")
	public ResponseEntity<String> addCredential(@RequestBody CandidateCredentialDto candidateCredentialDto){
		logger.debug("Entering addCredential method");
		String []tempCredentials=PasswordHasherUtil.storePassword(candidateCredentialDto.getPassword());
		candidateCredentialDto.setPassword(tempCredentials[0]);
		candidateCredentialDto.setSalt(tempCredentials[1]);
		return	new ResponseEntity<>(candidateCredentialService.addCredential(candidateCredentialDto), new HttpHeaders(), HttpStatus.CREATED);
	}
 	
	
	@PutMapping("/candidate/credential/change-password/{id}")//for candidate to change(update) password inside candidate dashboard
	public ResponseEntity<String> updatePassword(@PathVariable Long id,@RequestBody CandidateCredentialDto candidateCredentialDto){
		logger.debug("Entering updateCredential method");
		String []tempCredentials=PasswordHasherUtil.storePassword(candidateCredentialDto.getPassword());
		candidateCredentialDto.setPassword(tempCredentials[0]);
		candidateCredentialDto.setSalt(tempCredentials[1]);
		return	new ResponseEntity<>(candidateCredentialService.updatePassword(id,candidateCredentialDto), new HttpHeaders(), HttpStatus.OK);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
	 	logger.error("Id not found exception for {}",e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateIdException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateIdException e) {
	 	logger.error("Duplicate Id exception for {}",e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}	

}
