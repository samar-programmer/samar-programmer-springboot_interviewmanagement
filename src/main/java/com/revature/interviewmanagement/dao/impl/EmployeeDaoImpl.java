package com.revature.interviewmanagement.dao.impl;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.interviewmanagement.dao.EmployeeDao;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.util.mapper.EmployeeMapper;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger logger=LogManager.getLogger(EmployeeDaoImpl.class.getName());
	private static final LocalDateTime today=LocalDateTime.now(ZoneOffset.UTC);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String CHECK_EMPLOYEE_EMPLOYEEID="SELECT e FROM Employee e WHERE e.employeeId=:empId";
	private static final String CHECK_EMPLOYEE_DESIGNATIONID="SELECT e FROM Employee e WHERE e.designationId=:destId";
	private static final String CHECK_EMPLOYEE_EMAILID="SELECT e FROM Employee e WHERE e.emailId=:email";
	private static final String CHECK_EMPLOYEE_PHONENUMBER="SELECT e FROM Employee e WHERE e.phoneNumber=:phone";
	private static final String CHECK_EMPLOYEE_ALLEMPLOYEE="SELECT e FROM Employee e";
	private static final String CHECK_EMPLOYEE_EMPLOYEEBYNAME="SELECT e FROM Employee e WHERE CONCAT(e.firstName,' ', e.lastName) LIKE :name";
	
	
	/**
	 * checkState method will check whether email id,phone number,employee id, designation id of an employee 
	 * already present in another record before we add it.
	 * The goal of the method is to check uniqueness of the data.
	 * Before adding, we need to ensure that provided email id, phone number, employee id and designation id should not
	 * already present in existing record.
	 * For updating, we need to ensure that employee email id, phone number, employee id and designation id should already 
	 * present in current employee record and values to be updated should not present in any of the record, otherwise
	 * it will cause uniqueness constraint violation exception. 
	 * If status is given as 0, it will go into create mode, 1 for update mode,
	 * id is also one of the parameter is used for updating, id is not necessary while adding. Session object is passed
	 * to utilize current hibernate session for querying into the database.
	 * @param session current hibernate session used to query the database
	 * @param employee employee object contains employee details such as email id, phone number, employee id and designation id
	 * @param statusCode 0 for insert operation, 1 for update operation, which separates values to be checked
	 * @param id id of employee for update operation, -1 for create operation
	 * @return list of boolean represent state of email id, phone number, employee id and designation id respectively
	 */
	public List<Boolean> checkState(EmployeeDto employee,Integer statusCode, Long id) {
		Session session=sessionFactory.getCurrentSession();
		List<Employee> emailQueryList = session.createQuery(CHECK_EMPLOYEE_EMAILID,Employee.class).setParameter("email",employee.getEmailId()).getResultList();
		List<Employee> phoneQueryList = session.createQuery(CHECK_EMPLOYEE_PHONENUMBER,Employee.class).setParameter("phone",employee.getPhoneNumber()).getResultList();
		List<Employee> employeeIdQueryList = session.createQuery(CHECK_EMPLOYEE_EMPLOYEEID,Employee.class).setParameter("empId",employee.getEmployeeId()).getResultList();
		List<Employee> designationIdQueryList = session.createQuery(CHECK_EMPLOYEE_DESIGNATIONID,Employee.class).setParameter("destId",employee.getDesignationId()).getResultList();
		
		List<Boolean> result=new ArrayList<>(4);
		
		//status code - 0 for insert and 1 for update
		//
		/*For case 0: If any index of result array is true that means
		 *the corresponding email id/phone/other id doesn't exists in the database
		 *For case 1:If any index of result array is true that means, 
		 *either entered email id/phone/other id doesn't exists in the database or exists within the employee to be updated 
		 */
		switch(statusCode) {
			case 0:{
				result.add(emailQueryList.isEmpty());
				result.add(phoneQueryList.isEmpty());
				result.add(employeeIdQueryList.isEmpty());
				result.add(designationIdQueryList.isEmpty());
				break;
			}
			case 1:{
				result.add(emailQueryList.isEmpty() || emailQueryList.stream().map(p->p).anyMatch(p-> p.getId().equals(id)));
				result.add(phoneQueryList.isEmpty() || phoneQueryList.stream().map(p->p).anyMatch(p-> p.getId().equals(id)));
				result.add(employeeIdQueryList.isEmpty() || employeeIdQueryList.stream().map(p->p).anyMatch(p-> p.getId().equals(id)));
				result.add(designationIdQueryList.isEmpty() || designationIdQueryList.stream().map(p->p).anyMatch(p-> p.getId().equals(id)));
				break;
			}
			default:{
				break;
			}
		}
		
		return result;
	}
	
	@Override
	public List<Employee> getAllEmployee() {
		logger.trace("Entering getAllEmployee method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(CHECK_EMPLOYEE_ALLEMPLOYEE,Employee.class).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
		
	}

	@Override
	public Employee getEmployeeById(Long id) {
		logger.trace("Entering getEmployeeById method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.get(Employee.class, id);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public Employee getEmployeeByEmailId(String email) {
		logger.trace("Entering getEmployeeByEmailId method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(CHECK_EMPLOYEE_EMAILID,Employee.class).setParameter("email",email).getSingleResult();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
		
	}

	@Override
	public Employee getEmployeeByPhoneNumber(String phoneNumber) {
		logger.trace("Entering getEmployeeByPhoneNumber method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(CHECK_EMPLOYEE_PHONENUMBER,Employee.class).setParameter("phone",phoneNumber).getSingleResult();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		logger.trace("Entering getEmployeeByName method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(CHECK_EMPLOYEE_EMPLOYEEBYNAME,Employee.class).setParameter("name","%"+name+"%").getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
		
	}
	
	

	@Override
	public Employee getEmployeeByDesignationId(Long destId) {
		logger.trace("Entering getEmployeeByDesignationId method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(CHECK_EMPLOYEE_DESIGNATIONID,Employee.class).setParameter("destId",destId).getSingleResult();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public Employee getEmployeeByEmployeeId(Long empId) {
		logger.trace("Entering getEmployeeByEmployeeId method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(CHECK_EMPLOYEE_EMPLOYEEID,Employee.class).setParameter("empId",empId).getSingleResult();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
		
	}

	@Transactional
	@Override
	public String addEmployee(EmployeeDto employeeDto) {
		logger.trace("Entering addEmployee method");
		
		try {
			Session session=sessionFactory.getCurrentSession();
			Employee employee=EmployeeMapper.employeeEntityMapper(employeeDto);
			employee.setAddedOn(today);
			session.save(employee);
			logger.info("Employee details inserted");
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_ADDING);
		}
		
		return EMPLOYEE_CREATE;
	
	}

	@Transactional
	@Override
	public String updateEmployee(EmployeeDto employeeDto) {
		logger.trace("Entering updateEmployee method");
		try {
			Session session=sessionFactory.getCurrentSession();
			final Long id=employeeDto.getId();
			Employee employee=EmployeeMapper.employeeEntityMapper(employeeDto);
			employee.setUpdatedOn(today);
			session.merge(employee);
			session.flush();
			logger.info("Employee updated with id: {}",id);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_UPDATING);
		}
		return EMPLOYEE_UPDATE;
		}

	@Transactional
	@Override
	public String deleteEmployee(Long id) {
		logger.trace("Entering deleteEmployee method");
		Session session=sessionFactory.getCurrentSession();
		session.delete(session.load(Employee.class, id));
		session.flush();
		logger.info("Employee deleted with id: {}",id);
		return EMPLOYEE_DELETE;
		
	}
	


}


