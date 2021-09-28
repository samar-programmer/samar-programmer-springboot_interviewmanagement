package com.revature.interviewmanagement.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.dao.EmployeeDao;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.exception.BussinessLogicException;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.service.EmployeeService;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger=LogManager.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public String deleteEmployee(Long id) {
		logger.info("entering deleteEmployee method");
		if(employeeDao.getEmployeeById(id)!=null) {
			return employeeDao.deleteEmployee(id);
		}else {
			throw new BussinessLogicException("Employee "+ID_NOT_FOUND);
		}
		
	}

	@Override
	public String updateEmployee(EmployeeDto employee) {
		logger.info("entering updateEmployee method");
		if(employeeDao.getEmployeeById(employee.getId())!=null) {
			
			//1 denotes updating, 2nd parameter is the auto-generated id of employee 
			List<Boolean> stateArr=employeeDao.checkState(employee,Integer.valueOf(1),employee.getId());
			
			//if any one of the constraint is false, this variable will have true as result, which denotes uniqueness violation
			boolean updateState=stateArr.stream().anyMatch(Boolean.FALSE::equals);
			
			if(!updateState) {
				return employeeDao.updateEmployee(employee);
			}
			else if(Boolean.FALSE.equals(stateArr.get(0))){
				throw new BussinessLogicException("Entered Email Id "+DUPLICATE_ID);
			}
			else if(Boolean.FALSE.equals(stateArr.get(1))){
				throw new BussinessLogicException("Entered Phone Number "+DUPLICATE_ID);
			}
			else if(Boolean.FALSE.equals(stateArr.get(2))){
				throw new BussinessLogicException("Entered Employee Id "+DUPLICATE_ID);
			}
			else {
				throw new BussinessLogicException("Entered Designation Id "+DUPLICATE_ID);
			}
			
		}else {
			throw new BussinessLogicException("Employee "+ID_NOT_FOUND);
		}
		
	}

	@Override
	public String addEmployee(EmployeeDto employeeDto) {
		logger.info("entering addEmployee method");
		
		//0 denotes adding, -1 for for id as we don't have id while inserting so it is kept as -1
		List<Boolean> stateArr=employeeDao.checkState(employeeDto,Integer.valueOf(0),Long.valueOf(-1));
		
		//if any one of the constraint is false, this variable will have true as result, which denotes uniqueness violation
		boolean addState=stateArr.stream().anyMatch(Boolean.FALSE::equals);
		
		if(!addState) {
			return employeeDao.addEmployee(employeeDto);
		}
		
		else if(Boolean.FALSE.equals(stateArr.get(0))){
			throw new BussinessLogicException("Entered Email Id already exists");
		}
		else if(Boolean.FALSE.equals(stateArr.get(1))){
			throw new BussinessLogicException("Entered Phone number already exists");
		}
		else if(Boolean.FALSE.equals(stateArr.get(2))){
			throw new BussinessLogicException("Entered Employee Id already exists");
		}
		else {
			throw new BussinessLogicException("Entered Designation Id already exists");
		}
		
		
	}

	@Override
	public Employee getEmployeeByEmployeeId(EmployeeDto employeeDto) {
		logger.info("entering getEmployeeByEmployeeId method");
		try {
			return employeeDao.getEmployeeByEmployeeId(employeeDto.getEmployeeId());
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeByDesignationId(EmployeeDto employeeDto) {
		logger.info("entering getEmployeeByDesignationId method");
		try {
			return employeeDao.getEmployeeByDesignationId(employeeDto.getDesignationId());
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	

	@Override
	public Employee getEmployeeByPhoneNumber(EmployeeDto employeeDto) {
		logger.info("entering getEmployeeByPhoneNumber method");
		try {
			return employeeDao.getEmployeeByPhoneNumber(employeeDto.getPhoneNumber());
		}catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeByEmailId(EmployeeDto employeeDto) {
		logger.info("entering getEmployeeByEmailId method");
		try {
			return employeeDao.getEmployeeByEmailId(employeeDto.getEmailId());
		}catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeById(Long id) {
		logger.info("entering getEmployeeById method");
		try {
			return employeeDao.getEmployeeById(id);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Employee> getAllEmployee() {
		logger.info("entering getAllEmployee method");
		try {
			return employeeDao.getAllEmployee();
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		logger.info("entering getEmployeeByName method");
		try {
			return employeeDao.getEmployeeByName(name);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}


}
