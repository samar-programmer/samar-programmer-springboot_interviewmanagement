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

import com.revature.interviewmanagement.dao.EmployeeDao;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeTest {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeDao employeeDao;
	
	static final List<Employee> EMPLOYEE_TEST_DATA=Stream.of(
			new Employee(Long.valueOf(1),"Siva","D","siva@gmail.com","9876564567","Software Developer","Available")
			,new Employee(Long.valueOf(2),"Mani","Vel","mani@gmail.com","9876564565","Project Lead","Available")
			).collect(Collectors.toList());
	
	static final List<Employee> EMPLOYEE_TEST_DATA1=Stream.of(
			new Employee(Long.valueOf(1),"Siva","D","siva@gmail.com","9876564567","Software Developer","Available")
			
			).collect(Collectors.toList());
	static final List<Employee> EMPLOYEE_TEST_DATA2=Stream.of(
			new Employee(Long.valueOf(2),"Mani","Vel","mani@gmail.com","9876564565","Project Lead","Left")
			
			).collect(Collectors.toList());
	
	static final Employee EMPLOYEE_OBJECT=new Employee(Long.valueOf(1),"Siva","D","siva@gmail.com","9876564567","Software Developer","Available");
	
	
	@Test
	 void testGetAllEmployee() {
		when(employeeDao.getAllEmployee()).thenReturn(EMPLOYEE_TEST_DATA);
		assertEquals(2, employeeService.getAllEmployee().size());
	}
	
	@Test
	void testGetEmployeeById() {
		 long id=1;
		when(employeeDao.getEmployeeById(id)).thenReturn(EMPLOYEE_OBJECT);
		assertEquals("siva@gmail.com", employeeService.getEmployeeById(id).getEmailId());
	}

	@Test
	void testGetEmployeeByDesignation() {
		String designation="Software Developer";
		when(employeeDao.getEmployeeByDesignation(designation)).thenReturn(EMPLOYEE_TEST_DATA1);
		assertEquals(1, employeeService.getEmployeeByDesignation(designation).size());
	}

	@Test
	void testGetEmployeeByPhoneNumber() {
		String phoneNumber="9876564567";
		when(employeeDao.getEmployeeByPhoneNumber(phoneNumber)).thenReturn(EMPLOYEE_OBJECT);
		EmployeeDto employeeDto=new EmployeeDto();
		employeeDto.setPhoneNumber(phoneNumber);
		assertNotNull(employeeService.getEmployeeByPhoneNumber(employeeDto));
	}
	
	@Test
	void testGetEmployeeByEmailId() {
		String emailId="mani@gmail.com";
		when(employeeDao.getEmployeeByEmailId(emailId)).thenReturn(
				new Employee(Long.valueOf(2),"Mani","Vel","mani@gmail.com","9876564565","Project Lead","Available")	);
		EmployeeDto employeeDto=new EmployeeDto();
		employeeDto.setEmailId(emailId);
		assertNotNull(employeeService.getEmployeeByEmailId(employeeDto));
	}

	@Test
	void testGetEmployeeByName() {
		String name="mani";
		when(employeeDao.getEmployeeByName(name)).thenReturn(EMPLOYEE_TEST_DATA2);
		assertEquals(1,employeeService.getEmployeeByName(name).size());
	}
	
	@Test
	void testGetEmployeeByStatus() {
		String status="Available";
		when(employeeDao.getEmployeeByStatus(status)).thenReturn(EMPLOYEE_TEST_DATA);
		assertEquals(2,employeeService.getEmployeeByStatus(status).size());
	}


	@Test
	void testDeleteEmployee() {
		long id=1;
		when(employeeDao.deleteEmployee(id)).thenReturn("Employee details has been deleted successfully!");
		when(employeeDao.getEmployeeById(id)).thenReturn(EMPLOYEE_OBJECT);
		assertEquals("Employee details has been deleted successfully!", employeeService.deleteEmployee(id));
	}
	
	@Test
	void testAddEmployee() {
		Employee employee=new Employee(Long.valueOf(2),"Mani","Vel","mani@gmail.com","9876564565","Project Lead","Available");	
				employeeDao.addEmployee((employee));
		verify(employeeDao,times(1)).addEmployee((employee));
	}

	@Test
	void testUpdateEmployee() {
		long id=1;
		Employee employee=new Employee(Long.valueOf(1),"Mani","Vel","mani@gmail.com","9876564565","Project Lead","Available");	
		when(employeeDao.getEmployeeById(id)).thenReturn(EMPLOYEE_OBJECT);
		employeeDao.updateEmployee(employee);
		verify(employeeDao,times(1)).updateEmployee(employee);
	}

}
