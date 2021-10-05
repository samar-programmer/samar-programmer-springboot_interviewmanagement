package com.revature.interviewmanagement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.interviewmanagement.dao.CandidateDao;
import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.service.CandidateService;

@RunWith(SpringRunner.class)
@SpringBootTest
class CandidateTest {

	@Autowired
	private CandidateService candidateService;
	
	@MockBean
	private CandidateDao candidateDao;
	
	static final List<Candidate> CANDIDATE_TEST_DATA=Stream.of(
			new Candidate(Long.valueOf(1),"Siva","D","siva@gmail.com","9876564567","Java Developer","0-1 years")
			,new Candidate(Long.valueOf(2),"Mani","Vel","mani@gmail.com","9876564565","Python Developer","0-1 years")
			).collect(Collectors.toList());
	
	static final List<Candidate> CANDIDATE_TEST_DATA1=Stream.of(
			new Candidate(Long.valueOf(1),"Siva","D","siva@gmail.com","9876564567","Java Developer","0-1 years")
			
			).collect(Collectors.toList());
	static final List<Candidate> CANDIDATE_TEST_DATA2=Stream.of(
			new Candidate(Long.valueOf(2),"Mani","Vel","mani@gmail.com","9876564565","Python Developer","0-1 years")
			
			).collect(Collectors.toList());
	
	
	@Test
	 void testGetAllCandidate() {
		when(candidateDao.getAllCandidate()).thenReturn(CANDIDATE_TEST_DATA);
		assertEquals(2, candidateService.getAllCandidate().size());
	}
	
	@Test
	void testGetCandidateById() {
		 long id=1;
		when(candidateDao.getCandidateById(id)).thenReturn(
				new Candidate(Long.valueOf(1),"Siva","D","siva@gmail.com","9876564567","Java Developer","0-1 years")
				);
		assertEquals("siva@gmail.com", candidateService.getCandidateById(id).getEmailId());
	}
	
	
	@Test
	void testGetCandidateByEmailId() {
		String emailId="siva@gmail.com";
		when(candidateDao.getCandidateByEmailId(emailId)).thenReturn(CANDIDATE_TEST_DATA1);
		CandidateDto candidateDto =new CandidateDto();
		candidateDto.setEmailId(emailId);
		assertEquals(1, candidateService.getCandidateByEmailId(candidateDto).size());
	}
	
	@Test
	void testGetCandidateByRole() {
		String jobRole="Java Developer";
		when(candidateDao.getCandidateByRole(jobRole)).thenReturn(CANDIDATE_TEST_DATA1);
		assertEquals(1, candidateService.getCandidateByRole(jobRole).size());
	}
	
	
	@Test
	void testGetCandidateByName() {
		String name="siva";
		when(candidateDao.getCandidateByName(name)).thenReturn(CANDIDATE_TEST_DATA1);
		assertEquals(1, candidateService.getCandidateByName(name).size());
	}
	
	@Test
	void testGetCandidateByPhoneNumber() {
		String phoneNumber="9876564565";
		when(candidateDao.getCandidateByPhoneNumber(phoneNumber)).thenReturn(CANDIDATE_TEST_DATA2);
		CandidateDto candidateDto =new CandidateDto();
		candidateDto.setPhoneNumber(phoneNumber);
		assertEquals(1, candidateService.getCandidateByPhoneNumber(candidateDto).size());
	}
	
	@Test
	void testGetCandidateByExperience() {
		String  experience="0-1 years";
		when(candidateDao.getCandidateByExperience(experience)).thenReturn(CANDIDATE_TEST_DATA);
		assertEquals(2, candidateService.getCandidateByExperience(experience).size());
	}
	
	
	@Test
	void testAddCandidate() {
		Candidate candidate= new Candidate("Siva","D","siva@gmail.com","9876564567","Java Developer","0-1 years","samplelink");
		candidateDao.addCandidate(candidate);
		verify(candidateDao,times(1)).addCandidate(candidate);
	}
	 
	@Test
	void testDeleteCandidate() {
		long id=1;
		when(candidateDao.deleteCandidate(id)).thenReturn("Your application has been deleted successfully!");
		when(candidateDao.getCandidateById(id)).thenReturn(
				new Candidate(Long.valueOf(1),"Siva","D","siva@gmail.com","9876564567","Java Developer","0-1 years")
				);
		assertEquals("Your application has been deleted successfully!", candidateService.deleteCandidate(id));
	}
	
	@Test
	void testUpdateCandidate() {
		long id=2;
		Candidate candidate= new Candidate("Siva","D","siva@gmail.com","9876564567","Java Developer","0-1 years","samplelink");
		candidate.setId(id);
		when(candidateDao.getCandidateById(id)).thenReturn(
				new Candidate(Long.valueOf(2),"Siva","D","siva@gmail.com","9876564567","Java Developer","0-1 years")
				);
		candidateDao.updateCandidate(candidate);
		verify(candidateDao,times(1)).updateCandidate((candidate));
		 
	}
	
	
	@Test
	void testValidateJobRole() {
		CandidateDto candidateDto= new CandidateDto();
		candidateDto.setEmailId("siva@gmail.com");
		candidateDto.setJobRole("Python Devloper");
		
		when(candidateDao.validateJobRole(candidateDto)).thenReturn(null);
		
		assertTrue(candidateService.validateJobRole(candidateDto));
	}
	
	
	
	
	
	
	

}
