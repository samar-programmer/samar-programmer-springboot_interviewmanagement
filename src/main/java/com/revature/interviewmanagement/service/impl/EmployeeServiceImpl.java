package com.revature.interviewmanagement.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.revature.interviewmanagement.dao.EmployeeDao;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.exception.BussinessLogicException;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.exception.NoRecordFoundException;
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
		Employee employee=employeeDao.getEmployeeById(id);
		try {
			/*
			 * if the employee is present then check the status, we cannot delete employee who left,
			 *  only available employee can be deleted
			 */
			if (employee != null && !("Left").equals(employee.getStatus())) {
				return employeeDao.deleteEmployee(id);
			} else {
				throw new IdNotFoundException("Employee " + ID_NOT_FOUND);
			}
		} catch (DatabaseException e) {
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
					throw new DuplicateIdException("Entered Email Id " + DUPLICATE_ID);
				} else {
					throw new DuplicateIdException("Entered Phone Number " + DUPLICATE_ID);
				}

			} else {
				throw new IdNotFoundException("Employee " + ID_NOT_FOUND);
			}
		} catch (DatabaseException e) {
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
				throw new DuplicateIdException("Entered Email Id already exists");
			} else {
				throw new DuplicateIdException("Entered Phone number already exists");
			}
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}

	}

	@Override
	public List<Employee> getEmployeeByDesignation(String designation) {
		logger.info("entering getEmployeeByDesignation method");
		try {
			List<Employee> employees = employeeDao.getEmployeeByDesignation(designation);
			if (CollectionUtils.isEmpty(employees)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return employees;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeByPhoneNumber(EmployeeDto employeeDto) {
		logger.info("entering getEmployeeByPhoneNumber method");
		try {
			Employee employee = employeeDao.getEmployeeByPhoneNumber(employeeDto.getPhoneNumber());
			if (employee == null) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return employee;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeByEmailId(EmployeeDto employeeDto) {
		logger.info("entering getEmployeeByEmailId method");
		try {
			Employee employee = employeeDao.getEmployeeByEmailId(employeeDto.getEmailId());
			if (employee == null) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return employee;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeById(Long id) {
		logger.info("entering getEmployeeById method");
		try {
			Employee employee = employeeDao.getEmployeeById(id);
			if (employee == null) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return employee;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Employee> getAllEmployee() {
		logger.info("entering getAllEmployee method");
		try {
			List<Employee> employees = employeeDao.getAllEmployee();
			if (CollectionUtils.isEmpty(employees)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return employees;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		logger.info("entering getEmployeeByName method");
		try {
			List<Employee> employees = employeeDao.getEmployeeByName(name);
			if (CollectionUtils.isEmpty(employees)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return employees;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Employee> getEmployeeByStatus(String status) {
		logger.info("entering getEmployeeByStatus method");
		try {
			List<Employee> employees = employeeDao.getEmployeeByStatus(status);
			if (CollectionUtils.isEmpty(employees)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return employees;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<?> getAllDesignation() {
		logger.info("entering getAllDesignation method");
		try {
			List<?> designations = employeeDao.getAllDesignation();
			if (CollectionUtils.isEmpty(designations)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return designations;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

}
