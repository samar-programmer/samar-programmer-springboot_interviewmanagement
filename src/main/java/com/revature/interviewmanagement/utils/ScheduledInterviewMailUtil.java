package com.revature.interviewmanagement.utils;

import org.springframework.mail.javamail.JavaMailSender;
import java.time.format.DateTimeFormatter;

import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.model.InterviewDto;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

public class ScheduledInterviewMailUtil {
	private ScheduledInterviewMailUtil() {}
	
	private static String message=null;
	private static String subject=null;
	private static String candidateName=null;
	private static String resumeLink=null;
	private static String employeeName=null;
	private static String interviewRound=null;
	private static String callScheduledDate=null;
	private static String callScheduledTime=null;

	
	
	public static String sendScheduledInterviewMail(JavaMailSender javaMailSender,Candidate candidate,Employee employee,InterviewDto interviewDto) {
		final String candidateMail=candidate.getEmailId();
		final String employeeMail=employee.getEmailId();
		candidateName=candidate.getFirstName()+" "+candidate.getLastName();
		resumeLink=candidate.getResumeLink();
		employeeName=employee.getFirstName()+" "+employee.getLastName();
		interviewRound=interviewDto.getInterviewType();
		callScheduledDate=DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(interviewDto.getCallScheduledDate());
		callScheduledTime=interviewDto.getCallScheduledTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
		
		subject=REVATURE_NAME+"| Interview Details";
		message="Congrats "+candidateName+"!\r\n\n"
				+ "You are through to "+interviewRound+".\r\n\n"
				+ "Thank you for taking the time out to take the online test by Revature yesterday.\r\n"
				+ "\r\n"
				+ "This was the first step in unlocking your career’s true potential. We’re really impressed with your performance in the test and have decided to have a quick chat with you over a virtual conference call.\r\n"
				+ "\r\n"
				+ "This will be a quick 20 minute video call so remember to dress for the camera (meaning shorts are OK as long as we don’t see it 😉) One of our tech leads will be talking to you about your test performance, your programming strengths and your career aspirations. You’ll also have a chance to ask any questions you have about Revature or this position.\r\n"
				+ "\r\n"
				+ "Your Interview Slot: "+callScheduledTime+" on "+callScheduledDate+BACKSLASH_RN
				+ "Please be ready to join the interview link below at the exact time mentioned above:\r\n"
				+ "https://sample.re/interview-panel-3\r\n\n"
				+ "If you are running late, we may have to reschedule you to the end of the queue as we have a series of interviews lined up all day. The interview will use the same software as the orientation session yesterday - Microsoft Teams. It is free to install the software on your computer or phone though it will work directly on the browsers such as Microsoft Edge or Chrome. Please test your Microphone and Camera settings beforehand - the software allows you to test this out before you join the meeting.\n\nWe suggest you take these steps at least 10 minutes before your interview slot time so we can utilize the full 20 minutes to give you the best possible chance to move forward in this process.\r\n"
				+ "\r\n"+MAIL_FOOTER;
				
				
		
		MailSenderUtil.sendMail(javaMailSender, candidateMail, subject, message);
		message="Dear "+employeeName+",\n\nYou have an interview scheduled on "+callScheduledTime+" "+callScheduledDate+"\n\nCandidate Details are\r\n"
				+"Name: "+candidateName+"\nResume Link: "+resumeLink+BACKSLASH_RN
				+MAIL_FOOTER;
				
				
		MailSenderUtil.sendMail(javaMailSender, employeeMail, subject, message);
		
		 return INTERVIEW_SCHEDULED_MAIL;
	}
	
	public static String sendReScheduledInterviewMail(JavaMailSender javaMailSender,Candidate candidate,Employee employee,InterviewDto interviewDto) {
		final String candidateMail=candidate.getEmailId();
		final String employeeMail=employee.getEmailId();
		candidateName=candidate.getFirstName()+" "+candidate.getLastName();
		resumeLink=candidate.getResumeLink();
		employeeName=employee.getFirstName()+" "+employee.getLastName();
		interviewRound=interviewDto.getInterviewType();
		callScheduledDate=DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(interviewDto.getCallScheduledDate());
		callScheduledTime=interviewDto.getCallScheduledTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
		subject=REVATURE_NAME+"| Rescheduled Interview Details";
		message="Congrats "+candidateName+"!\r\n\n"
				+ "You are through to "+interviewRound+".\r\n\n"
				
				+ "This will be a quick 20 minute video call so remember to dress for the camera (meaning shorts are OK as long as we don’t see it 😉) One of our tech leads will be talking to you about your test performance, your programming strengths and your career aspirations. You’ll also have a chance to ask any questions you have about Revature or this position.\r\n"
				+ "\r\n"
				+ "Your Rescheduled Interview Slot: "+callScheduledTime+" on "+callScheduledDate+BACKSLASH_RN
				+ "Please be ready to join the interview link below at the exact time mentioned above:\r\n"
				+ "https://sample.re/interview-panel-3\r\n\n"
				+ "If you are running late, we may have to reschedule you to the end of the queue as we have a series of interviews lined up all day. The interview will use the same software as the orientation session yesterday - Microsoft Teams. It is free to install the software on your computer or phone though it will work directly on the browsers such as Microsoft Edge or Chrome. Please test your Microphone and Camera settings beforehand - the software allows you to test this out before you join the meeting.\n\nWe suggest you take these steps at least 10 minutes before your interview slot time so we can utilize the full 20 minutes to give you the best possible chance to move forward in this process.\r\n"
				+ "\r\n"
				+MAIL_FOOTER;
				
		
		MailSenderUtil.sendMail(javaMailSender, candidateMail, subject, message);
		message="Dear "+employeeName+",\nYou have a rescheduled interview on "+callScheduledTime+" "+callScheduledDate+"\n\nCandidate Details are\r\n"
				+"Name: "+candidateName+"\nResume Link: "+resumeLink+BACKSLASH_RN
				+MAIL_FOOTER;
		
		MailSenderUtil.sendMail(javaMailSender, employeeMail, subject, message);
		return INTERVIEW_RESCHEDULED_MAIL;
	}

}
