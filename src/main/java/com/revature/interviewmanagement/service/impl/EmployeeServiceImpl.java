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
import com.revature.interviewmanagement.util.mapper.EmployeeMapper;

import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public String deleteEmployee(Long id) {
		logger.info("entering deleteEmployee method");
		try {
			if (employeeDao.getEmployeeById(id) != null) {
				return employeeDao.deleteEmployee(id);
			} else {
				throw new BussinessLogicException("Employee " + ID_NOT_FOUND);
			}
		}  catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}

	}

	@Override
	public String updateEmployee(EmployeeDto employeeDto) {
		logger.info("entering updateEmployee method");
		try {
			if (employeeDao.getEmployeeById(employeeDto.getId()) != null) {

				// 1 denotes updating, 2nd parameter is the auto-generated id of employee
				List<Boolean> stateArr = employeeDao.checkState(employeeDto, Integer.valueOf(1), employeeDto.getId());

				// if any one of the constraint is false, this variable will have true as
				// result, which denotes uniqueness violation
				boolean updateState = stateArr.stream().anyMatch(Boolean.FALSE::equals);

				if (!updateState) {
					Employee employee = EmployeeMapper.employeeEntityMapper(employeeDto);
					return employeeDao.updateEmployee(employee);
				} else if (Boolean.FALSE.equals(stateArr.get(0))) {
					throw new BussinessLogicException("Entered Email Id " + DUPLICATE_ID);
				} else {
					throw new BussinessLogicException("Entered Phone Number " + DUPLICATE_ID);
				}

			} else {
				throw new BussinessLogicException("Employee " + ID_NOT_FOUND);
			}
		}catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}

	}

	@Override
	public String addEmployee(EmployeeDto employeeDto) {
		logger.info("entering addEmployee method");

		try {
			// 0 denotes adding, -1 for for id as we don't have id while inserting so it is
			// kept as -1
			List<Boolean> stateArr = employeeDao.checkState(employeeDto, Integer.valueOf(0), Long.valueOf(-1));

			// if any one of the constraint is false, this variable will have true as
			// result, which denotes uniqueness violation
			boolean addState = stateArr.stream().anyMatch(Boolean.FALSE::equals);

			if (!addState) {
				Employee employee = EmployeeMapper.employeeEntityMapper(employeeDto);
				return employeeDao.addEmployee(employee);
			}

			else if (Boolean.FALSE.equals(stateArr.get(0))) {
				throw new BussinessLogicException("Entered Email Id already exists");
			} else {
				throw new BussinessLogicException("Entered Phone number already exists");
			}
		}  catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}

	}

	@Override
	public List<Employee> getEmployeeByDesignation(String designation) {
		logger.info("entering getEmployeeByDesignation method");
		try {
			return employeeDao.getEmployeeByDesignation(designation);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeByPhoneNumber(EmployeeDto employeeDto) {
		logger.info("entering getEmployeeByPhoneNumber method");
		try {
			return employeeDao.getEmployeeByPhoneNumber(employeeDto.getPhoneNumber());
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeByEmailId(EmployeeDto employeeDto) {
		logger.info("entering getEmployeeByEmailId method");
		try {
			return employeeDao.getEmployeeByEmailId(employeeDto.getEmailId());
		} catch (DatabaseException e) {
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

	@Override
	public List<Employee> getEmployeeByStatus(String status) {
		logger.info("entering getEmployeeByStatus method");
		try {
			return employeeDao.getEmployeeByStatus(status);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<?> getAllDesignation() {
		logger.info("entering getAllDesignation method");
		try {
			return employeeDao.getAllDesignation();
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

}
