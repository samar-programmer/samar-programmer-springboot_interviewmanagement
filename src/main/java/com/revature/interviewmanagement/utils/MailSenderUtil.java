package com.revature.interviewmanagement.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class MailSenderUtil {
	private static final Logger logger=LogManager.getLogger(MailSenderUtil.class.getName());
		private MailSenderUtil() {
			throw new IllegalStateException("Utility class");
		}
	   
	    public static  void sendMail(JavaMailSender javaMailSender,String toReceiver,String subject,String message){		
	    	logger.trace("entering sendMail method");
			SimpleMailMessage msg = new SimpleMailMessage();
    		msg.setTo(toReceiver);
	        msg.setSubject(subject);
	        msg.setText(message);

	        javaMailSender.send(msg);	
			
		}
	
	
}
