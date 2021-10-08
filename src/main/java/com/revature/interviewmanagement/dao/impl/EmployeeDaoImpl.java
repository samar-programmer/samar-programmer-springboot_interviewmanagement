package com.revature.interviewmanagement.dao.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.interviewmanagement.dao.EmployeeDao;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.model.EmployeeDto;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger logger = LogManager.getLogger(EmployeeDaoImpl.class.getName());
	private static final LocalDateTime today = LocalDateTime.now(ZoneOffset.UTC);

	@Autowired
	private SessionFactory sessionFactory;

	private static final String CHECK_EMPLOYEE_DESIGNATION = "SELECT e FROM Employee e WHERE e.designation=:designation AND e.status!='Left'";
	private static final String CHECK_EMPLOYEE_STATUS = "SELECT e FROM Employee e WHERE e.status=:status";
	/*
	 * CHECK_EMPLOYEE_EMAILID is used in getEmployeeByEmailId
	 */
	private static final String CHECK_EMPLOYEE_EMAILID = "SELECT e FROM Employee e WHERE e.emailId=:email AND e.status!='Left'";
	/*
	 * CHECK_EMPLOYEE_EMAILID1 is used by checkState method, because status is not checked here, this is helpful in checking deleted employee too.
	 */
	private static final String CHECK_EMPLOYEE_EMAILID1 = "SELECT e FROM Employee e WHERE e.emailId=:email";
	private static final String CHECK_EMPLOYEE_PHONENUMBER = "SELECT e FROM Employee e WHERE e.phoneNumber=:phone";
	private static final String CHECK_EMPLOYEE_ALLEMPLOYEE = "SELECT e FROM Employee e WHERE e.status!='Left'";
	private static final String CHECK_EMPLOYEE_EMPLOYEEBYNAME = "SELECT e FROM Employee e WHERE CONCAT(e.firstName,' ', e.lastName) LIKE :name AND e.status!='Left'";
	private static final String GET_ALL_DESIGNATION = "SELECT * FROM designation";

	/**
	 * checkState method will check whether email id,phone number of an employee
	 * already present in another record before we add it. The goal of the method is
	 * to check uniqueness of the data. Before adding, we need to ensure that
	 * provided email id, phone number should not already present in existing
	 * record. For updating, we need to ensure that employee email id, phone number
	 * should already present in current employee record and values to be updated
	 * should not present in any of the record, otherwise it will cause uniqueness
	 * constraint violation exception. If status is given as 0, it will go into
	 * create mode, 1 for update mode, id is one of the parameter is used for
	 * updating, id is not necessary, so kept as -1 while adding.
	 * 
	 * @param employee   employee object contains employee details such as email id,
	 *                   phone number
	 * @param statusCode 0 for insert operation, 1 for update operation, which
	 *                   separates values to be checked
	 * @param id         id of employee for update operation, -1 for create
	 *                   operation
	 * @return list of boolean represent state of email id, phone number
	 *         respectively
	 */
	public List<Boolean> checkState(EmployeeDto employee, Integer statusCode, Long id) {
		Session session = sessionFactory.getCurrentSession();
		List<Employee> emailQueryList = session.createQuery(CHECK_EMPLOYEE_EMAILID1, Employee.class)
				.setParameter("email", employee.getEmailId()).getResultList();
		List<Employee> phoneQueryList = session.createQuery(CHECK_EMPLOYEE_PHONENUMBER, Employee.class)
				.setParameter("phone", employee.getPhoneNumber()).getResultList();

		List<Boolean> result = new ArrayList<>(2);

		// status code - 0 for insert and 1 for update
		//
		/*
		 * For case 0: If any index of result array is true that means the corresponding
		 * email id/phone id doesn't exists in the database For case 1:If any index of
		 * result array is true that means, either entered email id/phone don't exists
		 * in the database or exists within the employee to be updated
		 */
		switch (statusCode) {
		case 0: {
			result.add(emailQueryList.isEmpty());
			result.add(phoneQueryList.isEmpty());
			break;
		}
		case 1: {
			result.add(emailQueryList.isEmpty()
					|| emailQueryList.stream().map(p -> p).anyMatch(p -> p.getId().equals(id)));
			result.add(phoneQueryList.isEmpty()
					|| phoneQueryList.stream().map(p -> p).anyMatch(p -> p.getId().equals(id)));
			break;
		}
		default: {
			break;
		}
		}

		return result;
	}

	@Override
	public List<Employee> getAllEmployee() {
		logger.info("Entering getAllEmployee method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(CHECK_EMPLOYEE_ALLEMPLOYEE, Employee.class).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}

	}

	@Override
	public List<?> getAllDesignation() {

		logger.info("Entering getAllDesignation method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createNativeQuery(GET_ALL_DESIGNATION).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public Employee getEmployeeById(Long id) {
		logger.info("Entering getEmployeeById method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Employee.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public Employee getEmployeeByEmailId(String email) {
		logger.info("Entering getEmployeeByEmailId method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(CHECK_EMPLOYEE_EMAILID, Employee.class).setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException noResultException) {
			logger.warn(noResultException.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}

	}

	@Override
	public Employee getEmployeeByPhoneNumber(String phoneNumber) {
		logger.info("Entering getEmployeeByPhoneNumber method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(CHECK_EMPLOYEE_PHONENUMBER, Employee.class).setParameter("phone", phoneNumber)
					.getSingleResult();
		} catch (NoResultException noResultException) {
			logger.warn(noResultException.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		logger.info("Entering getEmployeeByName method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(CHECK_EMPLOYEE_EMPLOYEEBYNAME, Employee.class)
					.setParameter("name", "%" + name + "%").getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}

	}

	@Override
	public List<Employee> getEmployeeByDesignation(String designation) {
		logger.info("Entering getEmployeeByDesignation method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(CHECK_EMPLOYEE_DESIGNATION, Employee.class)
					.setParameter("designation", designation).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Employee> getEmployeeByStatus(String status) {
		logger.info("Entering getEmployeeByStatus method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(CHECK_EMPLOYEE_STATUS, Employee.class).setParameter("status", status)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Transactional
	@Override
	public String addEmployee(Employee employee) {
		logger.info("Entering addEmployee method");

		try {
			Session session = sessionFactory.getCurrentSession();
			employee.setAddedOn(today);
			employee.setStatus("Available");
			session.save(employee);
			logger.info("Employee details inserted");
			return EMPLOYEE_CREATE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_ADDING);
		}

	}

	@Transactional
	@Override
	public String updateEmployee(Employee employee) {
		logger.info("Entering updateEmployee method");
		try {
			Session session = sessionFactory.getCurrentSession();
			final Long id = employee.getId();
			employee.setUpdatedOn(today);
			session.merge(employee);
			session.flush();
			logger.info("Employee updated with id: {}", id);
			return EMPLOYEE_UPDATE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_UPDATING);
		}

	}

	@Transactional
	@Override
	// here delete operation means that we marking status as Left
	public String deleteEmployee(Long id) {
		logger.info("Entering deleteEmployee method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Employee employee = session.load(Employee.class, id);
			employee.setUpdatedOn(today);
			employee.setStatus("Left");
			session.merge(employee);
			logger.info("Employee deleted with id: {}", id);
			return EMPLOYEE_DELETE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_DELETING);
		}

	}

}
