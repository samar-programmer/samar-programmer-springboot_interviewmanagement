package com.revature.interviewmanagement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.interviewmanagement.dao.InterviewDao;
import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.service.InterviewService;


@RunWith(SpringRunner.class)
@SpringBootTest
class InterviewTest {

	@Autowired
	private InterviewService interviewService;
	
	@MockBean
	private InterviewDao interviewDao;
	
	
	static final Candidate CANDIDATE_DATA1=new Candidate(Long.valueOf(1),"Guna","D","guna@gmail.com","9876564569","Java Developer","0-1 years");
	static final Employee EMPLOYEE_DATA1=new Employee(Long.valueOf(1),"Siva","D","siva@gmail.com","9876564567","Software Developer","Available");
	static final Candidate CANDIDATE_DATA2=new Candidate(Long.valueOf(2),"Bala","M","bala@gmail.com","9846564565","Java Developer","0-1 years");
	static final Employee EMPLOYEE_DATA2=new Employee(Long.valueOf(2),"Silvan","B","silvan@gmail.com","8876564567","Software Developer","Available");
	
	static final List<Interview> INTERVIEW_TEST_DATA=Stream.of(
			new Interview(Long.valueOf(1),"Technical Interview 1","Live",LocalDate.now(),LocalTime.now(),CANDIDATE_DATA1,EMPLOYEE_DATA1)
			,new Interview(Long.valueOf(2),"Technical Interview 2","Live",LocalDate.now(),LocalTime.now(),CANDIDATE_DATA2,EMPLOYEE_DATA2)
			).collect(Collectors.toList());
	
	static final List<Interview> INTERVIEW_TEST_DATA1=Stream.of(
			new Interview(Long.valueOf(1),"Technical Interview 1","Live",LocalDate.now(),LocalTime.now(),CANDIDATE_DATA1,EMPLOYEE_DATA1)
			
			).collect(Collectors.toList());
	static final List<Interview> INTERVIEW_TEST_DATA2=Stream.of(
			new Interview(Long.valueOf(2),"Technical Interview 2","Live",LocalDate.now(),LocalTime.now(),CANDIDATE_DATA2,EMPLOYEE_DATA2)
			
			).collect(Collectors.toList());
	
	
	@Test
	 void testGetAllInterview() {
		when(interviewDao.getAllInterview()).thenReturn(INTERVIEW_TEST_DATA);
		assertEquals(2, interviewService.getAllInterview().size());
	}
	
	@Test
	void testGetInterviewById() {
		 long id=1;
		when(interviewDao.getInterviewById(id)).thenReturn(
				new Interview(Long.valueOf(1),"Technical Interview 1","Live",LocalDate.now(),LocalTime.now(),CANDIDATE_DATA1,EMPLOYEE_DATA1)
				);
		assertNotNull(interviewService.getInterviewById(id));
	}
	
	@Test
	void testGetInterviewByScheduledDate() {
		LocalDate today=LocalDate.now();
		when(interviewDao.getInterviewByScheduledDate(today)).thenReturn(INTERVIEW_TEST_DATA);
		assertEquals(2, interviewService.getInterviewByScheduledDate(today).size());
	}
	
	@Test
	void testGetInterviewByCandidateId() {
		long id=1;
		when(interviewDao.getInterviewByCandidateId(id)).thenReturn(INTERVIEW_TEST_DATA1);
		assertEquals(1, interviewService.getInterviewByCandidateId(id).size());
	}
	
	@Test
	void testGetInterviewByCandidateName() {
		String name="Bala";
		when(interviewDao.getInterviewByCandidateName(name)).thenReturn(INTERVIEW_TEST_DATA2);
		assertEquals(1, interviewService.getInterviewByCandidateName(name).size());
	}

	@Test
	void testGetInterviewByCandidatePhone() {
		String phoneNumber="9846564565";
		when(interviewDao.getInterviewByCandidatePhone(phoneNumber)).thenReturn(INTERVIEW_TEST_DATA2);
		CandidateDto candidateDto=new CandidateDto();
		candidateDto.setPhoneNumber(phoneNumber);
		assertEquals(1, interviewService.getInterviewByCandidatePhone(candidateDto).size());
	}
	
	@Test
	void testGetInterviewByCandidateEmailId() {
		String emailId="guna@gmail.com";
		when(interviewDao.getInterviewByCandidateEmailId(emailId)).thenReturn(INTERVIEW_TEST_DATA1);
		CandidateDto candidateDto=new CandidateDto();
		candidateDto.setEmailId(emailId);
		assertEquals(1, interviewService.getInterviewByCandidateEmailId(candidateDto).size());
	}

	@Test
	void testGetInterviewByCandidateRole() {
		String jobRole="Java Developer";
		when(interviewDao.getInterviewByCandidateRole(jobRole)).thenReturn(INTERVIEW_TEST_DATA);
		assertEquals(2, interviewService.getInterviewByCandidateRole(jobRole).size());
	}
	
	@Test
	void testGetInterviewByCandidateExperience() {
		String experience="0-1 years";
		when(interviewDao.getInterviewByCandidateExperience(experience)).thenReturn(INTERVIEW_TEST_DATA);
		assertEquals(2, interviewService.getInterviewByCandidateExperience(experience).size());
	}
	
	@Test
	void testGetInterviewByEmployeeId() {
		long id=1;
		when(interviewDao.getInterviewByEmployeeId(id)).thenReturn(INTERVIEW_TEST_DATA1);
		assertEquals(1, interviewService.getInterviewByEmployeeId(id).size());
	}
	
	@Test
	void testgetInterviewByEmployeeName() {
		String name="Siva";
		when(interviewDao.getInterviewByCandidateName(name)).thenReturn(INTERVIEW_TEST_DATA1);
		assertEquals(1, interviewService.getInterviewByCandidateName(name).size());
	}

	@Test
	void testGetInterviewByEmployeePhone() {
		String phoneNumber="9846564567";
		when(interviewDao.getInterviewByEmployeePhone(phoneNumber)).thenReturn(INTERVIEW_TEST_DATA1);
		EmployeeDto employeeDto=new EmployeeDto();
		employeeDto.setPhoneNumber(phoneNumber);
		assertEquals(1, interviewService.getInterviewByEmployeePhone(employeeDto).size());
	}
	
	@Test
	void testGetInterviewByEmployeeEmailId() {
		String emailId="silvan@gmail.com";
		when(interviewDao.getInterviewByEmployeeEmailId(emailId)).thenReturn(INTERVIEW_TEST_DATA2);
		EmployeeDto employeeDto=new EmployeeDto();
		employeeDto.setEmailId(emailId);
		assertEquals(1, interviewService.getInterviewByEmployeeEmailId(employeeDto).size());
	}

	@Test
	void testGetInterviewByEmployeeDesignation() {
		String designation="Software Developer";
		when(interviewDao.getInterviewByEmployeeDesignation(designation)).thenReturn(INTERVIEW_TEST_DATA);
		assertEquals(2, interviewService.getInterviewByEmployeeDesignation(designation).size());
	}

	
	@Test
	void testDeleteInterview() {
		long id=1;
		when(interviewDao.deleteInterview(id)).thenReturn("Interview details has been deleted successfully!");
		when(interviewDao.getInterviewById(id)).thenReturn(
				new Interview(Long.valueOf(1),"Technical Interview 1","Live",LocalDate.now(),LocalTime.now(),CANDIDATE_DATA1,EMPLOYEE_DATA1)	);
		assertEquals("Interview details has been deleted successfully!", interviewDao.deleteInterview(id));
	}
	
	@Test
	void testgetInterviewByType() {
		String interviewType="Technical Interview 2";
		when(interviewDao.getInterviewByType(interviewType)).thenReturn(INTERVIEW_TEST_DATA2);
		assertEquals(1, interviewService.getInterviewByType(interviewType).size());
	}

	@Test
	void GetInterviewByEmployeeStatus() {
		String status="Available";
		when(interviewDao.getInterviewByEmployeeStatus(status)).thenReturn(INTERVIEW_TEST_DATA);
		assertEquals(2, interviewService.getInterviewByEmployeeStatus(status).size());
	}


	@Test
	void testAddInterview() {
		long candidateId=1;
		long employeeId=1;
		Interview interview=new Interview(Long.valueOf(1),"Technical Interview 1","Live",LocalDate.now(),LocalTime.now(),CANDIDATE_DATA1,EMPLOYEE_DATA1);
		interviewDao.addInterview(interview,candidateId,employeeId);
		verify(interviewDao,times(1)).addInterview(interview,candidateId,employeeId);
	
	}
	
	@Test
	void testUpdateInterview() {
		long id=1;
		Interview interview=new Interview(Long.valueOf(1),"Technical Interview 1","Live",LocalDate.now(),LocalTime.now(),CANDIDATE_DATA1,EMPLOYEE_DATA1);
		when(interviewDao.getInterviewById(id)).thenReturn(
				new Interview(Long.valueOf(1),"Technical Interview 1","Live",LocalDate.now(),LocalTime.now(),CANDIDATE_DATA1,EMPLOYEE_DATA1)
				);
		interviewDao.updateInterview(interview);
		verify(interviewDao,times(1)).updateInterview(interview);
	
	}



}
