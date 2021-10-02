package com.revature.interviewmanagement.utils;

import java.time.format.DateTimeFormatter;

import org.springframework.mail.javamail.JavaMailSender;
import com.revature.interviewmanagement.model.ResultDto;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

public class ResultMailSenderUtil {
	private ResultMailSenderUtil() {
	}

	public static String sendResultMail(JavaMailSender javaMailSender, ResultDto resultDto) {
		String status = resultDto.getStatus();
		String hrMessage = resultDto.getMessage();
		String callScheduledDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(resultDto.getInterview().getCallScheduledDate());
		String callScheduledTime = resultDto.getInterview().getCallScheduledTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
		String interviewRound = resultDto.getInterview().getInterviewType();
		String name = resultDto.getInterview().getCandidate().getFirstName() + " "
				+ resultDto.getInterview().getCandidate().getLastName();
		String subject = REVATURE_NAME+"| Interview Result";
		String message = "Congrats " + name + "!\n\nYour interview result for " + interviewRound + " held on "
				+ callScheduledTime + " " + callScheduledDate + "\n\nResult: " + status + "\n\n "+ hrMessage + "\r\n\n"
				+ MAIL_FOOTER;

		MailSenderUtil.sendMail(javaMailSender, resultDto.getInterview().getCandidate().getEmailId(), subject, message);
		return RESULT_MAIL;
	}

}
