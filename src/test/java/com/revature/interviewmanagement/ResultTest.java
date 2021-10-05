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

import com.revature.interviewmanagement.dao.ResultDao;
import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.entity.Result;
import com.revature.interviewmanagement.service.ResultService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ResultTest {

	@Autowired
	private ResultService resultService;
	
	@MockBean
	private ResultDao resultDao;
	
	static final Candidate CANDIDATE_DATA1=new Candidate(Long.valueOf(1),"Guna","D","guna@gmail.com","9876564569","Java Developer","0-1 years");
	static final Employee EMPLOYEE_DATA1=new Employee(Long.valueOf(1),"Siva","D","siva@gmail.com","9876564567","Software Developer","Available");
	static final Candidate CANDIDATE_DATA2=new Candidate(Long.valueOf(2),"Bala","M","bala@gmail.com","9846564565","Java Developer","0-1 years");
	static final Employee EMPLOYEE_DATA2=new Employee(Long.valueOf(2),"Silvan","B","silvan@gmail.com","8876564567","Software Developer","Available");
	
	static final Interview INTERVIEW_TEST_DATA1=new Interview(Long.valueOf(1),"Technical Interview 1","Live",LocalDate.now(),LocalTime.now(),CANDIDATE_DATA1,EMPLOYEE_DATA1);		
	static final Interview INTERVIEW_TEST_DATA2=new Interview(Long.valueOf(2),"Technical Interview 2","Live",LocalDate.now(),LocalTime.now(),CANDIDATE_DATA2,EMPLOYEE_DATA2);
	
	static final List<Result> RESULT_DATA=Stream.of(new Result(Long.valueOf(1),"Good skills","Selected",INTERVIEW_TEST_DATA1),
			new Result(Long.valueOf(2),"not good in technical skills","Rejected",INTERVIEW_TEST_DATA2)
		).collect(Collectors.toList());
	static final List<Result> RESULT_DATA1=Stream.of(new Result(Long.valueOf(1),"Good skills","Selected",INTERVIEW_TEST_DATA1)
			
		).collect(Collectors.toList());
	
	static final List<Result> RESULT_DATA2=Stream.of(new Result(Long.valueOf(2),"not good in technical skills","Rejected",INTERVIEW_TEST_DATA2)
			
			).collect(Collectors.toList());
	
	@Test
	 void testGetAllResult() {
		when(resultDao.getAllResult()).thenReturn(RESULT_DATA);
		assertEquals(2, resultService.getAllResult().size());
	}
	
	@Test
	void testGetResultById() {
		 long id=1;
		when(resultDao.getResultById(id)).thenReturn(new Result(Long.valueOf(1),"Good skills","Selected",INTERVIEW_TEST_DATA1));
		assertNotNull(resultService.getResultById(id));
	}
	
	@Test
	void testGetResultByCandidateId() {
		 long id=1;
		when(resultDao.getResultByCandidateId(id)).thenReturn(RESULT_DATA1);
		assertEquals(1,resultService.getResultByCandidateId(id).size());
	}
	
	@Test
	void testGetResultByEmployeeId() {
		long id=2;
		when(resultDao.getResultByEmployeeId(id)).thenReturn(RESULT_DATA2);
		assertEquals(1,resultService.getResultByEmployeeId(id).size());
	}
	
	@Test
	void testGetResultByInterviewId() {
		 long id=2;
		when(resultDao.getResultByInterviewId(id)).thenReturn(new Result(Long.valueOf(2),"not good in technical skills","Rejected",INTERVIEW_TEST_DATA2));
		assertNotNull(resultService.getResultByInterviewId(id));
	}
	
	@Test
	void testDeleteResult() {
		long id=1;
		when(resultDao.deleteResult(id)).thenReturn("Result details has been deleted successfully!");
		when(resultDao.getResultById(id)).thenReturn(
				new Result(Long.valueOf(1),"Good skills","Selected",INTERVIEW_TEST_DATA1)
				);
		assertEquals("Result details has been deleted successfully!", resultDao.deleteResult(id));
	}
	

	@Test
	void testAddResult() {
		long id=1;
		Result result= new Result(Long.valueOf(1),"Good skills","Selected",INTERVIEW_TEST_DATA1);
		resultDao.addResult(id,result);
		verify(resultDao,times(1)).addResult(id,result);
	}
	
	@Test
	void testUpdateResult() {
		long id=1;
		Result result= new Result(Long.valueOf(1),"Good skills","Selected",INTERVIEW_TEST_DATA1);
		when(resultDao.getResultById(id)).thenReturn(new Result(Long.valueOf(1),"Good skills","Selected",INTERVIEW_TEST_DATA1));
		resultDao.updateResult(result);
		verify(resultDao,times(1)).updateResult(result);
	}
	



	
}
