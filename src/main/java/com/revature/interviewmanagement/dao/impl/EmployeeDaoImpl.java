package com.revature.interviewmanagement.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.interviewmanagement.dao.EmployeeDao;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.entity.credentials.EmployeeCredential;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.util.mapper.EmployeeMapper;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger logger=LogManager.getLogger(EmployeeDaoImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String CHECK_EMPLOYEE_EMPLOYEEID="SELECT e FROM Employee e WHERE e.employeeId=:empId";
	private static final String CHECK_EMPLOYEE_DESIGNATIONID="SELECT e FROM Employee e WHERE e.designationId=:destId";
	private static final String CHECK_EMPLOYEE_EMAILID="SELECT e FROM Employee e WHERE e.emailId=:email";
	private static final String CHECK_EMPLOYEE_PHONENUMBER="SELECT e FROM Employee e WHERE e.phoneNumber=:phone";
	private static final String CHECK_EMPLOYEE_ALLEMPLOYEE="SELECT e FROM Employee e";
	private static final String CHECK_EMPLOYEE_EMPLOYEEBYNAME="SELECT e FROM Employee e WHERE CONCAT(e.firstName,' ', e.lastName) LIKE :name";
	
	
	
	private List<Boolean> checkState(Session session, EmployeeDto employee,int statusCode, long id) {
		Query<?> emailQuery = session.createQuery(CHECK_EMPLOYEE_EMAILID).setParameter("email",employee.getEmailId());
		Query<?> phoneQuery = session.createQuery(CHECK_EMPLOYEE_PHONENUMBER).setParameter("phone",employee.getPhoneNumber());
		Query<?> employeeIdQuery = session.createQuery(CHECK_EMPLOYEE_EMPLOYEEID).setParameter("empId",employee.getEmployeeId());
		Query<?> designationIdQuery = session.createQuery(CHECK_EMPLOYEE_DESIGNATIONID).setParameter("destId",employee.getDesignationId());
		
		List<Boolean> result=new ArrayList<>(4);
		
		//status code 0 for insert and 1 for update
		switch(statusCode) {
			case 0:{
				result.add(emailQuery.list().isEmpty());
				result.add(phoneQuery.list().isEmpty());
				result.add(employeeIdQuery.list().isEmpty());
				result.add(designationIdQuery.list().isEmpty());
				break;
			}
			case 1:{
				result.add(emailQuery.list().isEmpty() || emailQuery.list().stream().map(p->p).anyMatch(p->((Employee) p).getId()==id));
				result.add(phoneQuery.list().isEmpty() || phoneQuery.list().stream().map(p->p).anyMatch(p->((Employee) p).getId()==id));
				result.add(employeeIdQuery.list().isEmpty() || employeeIdQuery.list().stream().map(p->p).anyMatch(p->((Employee) p).getId()==id));
				result.add(designationIdQuery.list().isEmpty() || designationIdQuery.list().stream().map(p->p).anyMatch(p->((Employee) p).getId()==id));
				break;
			}
			default:{
				break;
			}
		}
		
		return result;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering getAllEmployees method");
		@SuppressWarnings("unchecked")
		List<Employee> resultList=session.createQuery(CHECK_EMPLOYEE_ALLEMPLOYEE).getResultList();
		return (resultList.isEmpty()?null:resultList);
		}

	@Override
	public Employee getEmployeeById(Long id) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering getEmployeeById method");
		return session.get(Employee.class, id);
	}

	@Override
	public Employee getEmployeeByEmailId(String email) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering getEmployeeByEmailId method");
		@SuppressWarnings("unchecked")
		List<Employee> resultList=session.createQuery(CHECK_EMPLOYEE_EMAILID).setParameter("email",email).getResultList();
		return (resultList.isEmpty()?null:resultList.get(0));
	}

	@Override
	public Employee getEmployeeByPhoneNumber(String phoneNumber) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering getEmployeeByPhoneNumber method");
		@SuppressWarnings("unchecked")
		List<Employee> resultList=session.createQuery(CHECK_EMPLOYEE_PHONENUMBER).setParameter("phone",phoneNumber).getResultList();
		return (resultList.isEmpty()?null:resultList.get(0));
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering getEmployeeByFirstName method");
		@SuppressWarnings("unchecked")
		List<Employee> resultList=session.createQuery(CHECK_EMPLOYEE_EMPLOYEEBYNAME).setParameter("name","%"+name+"%").getResultList();
		return (resultList.isEmpty()?null:resultList);
	}
	
	

	@Override
	public List<Employee> getEmployeeByDesignationId(Long destId) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering getEmployeeByDesignationId method");
		@SuppressWarnings("unchecked")
		List<Employee> resultList=session.createQuery(CHECK_EMPLOYEE_DESIGNATIONID).setParameter("destId",destId).getResultList();
		return (resultList.isEmpty()?null:resultList);
	}

	@Override
	public List<Employee> getEmployeeByEmployeeId(Long empId) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering getEmployeeByEmployeeId method");
		@SuppressWarnings("unchecked")
		List<Employee> resultList=session.createQuery(CHECK_EMPLOYEE_EMPLOYEEID).setParameter("empId",empId).getResultList();
		return (resultList.isEmpty()?null:resultList);
		
	}

	@Transactional
	@Override
	public String addEmployee(Long credentialId,EmployeeDto employeeDto) {
		Session session=sessionFactory.getCurrentSession();
		Long id=null;
		try {
			List<Boolean> stateArr=checkState(session,employeeDto,0,-1);
			
			boolean addState=stateArr.stream().anyMatch(Boolean.FALSE::equals);
			
				if(!addState) {
					EmployeeCredential employeeCredential=session.load(EmployeeCredential.class,credentialId);//assuming that employee credentials always exists
					Employee employee=EmployeeMapper.employeeEntityMapper(employeeDto);
					employee.setEmployeeCredential(employeeCredential);
					id=(Long)session.save(employee);
					logger.info("Employee added with id: {}",id);
				}
				else if(Boolean.FALSE.equals(stateArr.get(0))){
					throw new DuplicateIdException("Entered email id already exists");
				}
				else if(Boolean.FALSE.equals(stateArr.get(1))){
					throw new DuplicateIdException("Entered phone number already exists");
				}
				else if(Boolean.FALSE.equals(stateArr.get(2))){
					throw new DuplicateIdException("Entered Employee Id already exists");
				}
				else {
					throw new DuplicateIdException("Entered Designation Id already exists");
				}
			
			
		} catch (HibernateException e1) {
			
			logger.warn("unable to add employee, message: {}",e1.getMessage(),e1);
		}
		
		return (id!=null)?"Employee details inserted with id: "+id:"Couldn't create employee...Error occured while inserting";
	
	}

	@Transactional
	@Override
	public String updateEmployee(Long id, EmployeeDto employeeDto) {
		Session session=sessionFactory.getCurrentSession();
		boolean check=false;
		String result=null;
		Employee updateObj=null;
		try {
			updateObj=session.load(Employee.class,id);
			if(!updateObj.getEmailId().isEmpty()) {  //necessary line to continue the flow 
				check=true;
			}
		} 
		catch (org.hibernate.ObjectNotFoundException e1) {
			logger.warn("unable to update employee, message: {}",e1.getMessage(),e1);
			throw new IdNotFoundException("Updation is failed...entered id doesn't exist");
		}
			
		if(check) {
				List<Boolean> stateArr=checkState(session,employeeDto,1,id);
				boolean updateState=stateArr.stream().anyMatch(Boolean.FALSE::equals);
			
				if(!updateState) {
					Employee employee=EmployeeMapper.employeeEntityMapper(employeeDto);
					employee.setId(id);
					session.merge(employee);
					session.flush();
					logger.info("Employee updated with id: {}",id);
					result="updation is successful for id: "+id;
				}
				
				else if(Boolean.FALSE.equals(stateArr.get(0))){
					throw new DuplicateIdException("Updation is failed...Entered email id already exists in another record");
				}
				else if(Boolean.FALSE.equals(stateArr.get(1))){
					throw new DuplicateIdException("Updation is failed...Entered phone number already exists in another record");
				}
				else if(Boolean.FALSE.equals(stateArr.get(2))){
					throw new DuplicateIdException("Updation is failed...Entered Employee Id already exists in another record");
				}
				else {
					throw new DuplicateIdException("Updation is failed...Entered Designation Id already exists in another record");
				}
			} 
		return result;
		}

	@Transactional
	@Override
	public String deleteEmployee(Long id) {
		Session session=sessionFactory.getCurrentSession();
		String result=null;
		boolean check=false;
		Employee deleteObject=null;
		try {
			deleteObject=session.load(Employee.class, id);
			if(!deleteObject.getEmailId().isEmpty()) { //necessary line to continue the flow 
				check=true;
			}
		}catch(org.hibernate.ObjectNotFoundException e) {
			logger.warn("unable to delete employee, message: {}",e.getMessage(),e);
			throw new IdNotFoundException("Deletion is failed...Entered Id doesn't exists");
		}
		 
		if(check) {
			session.delete(deleteObject);
			session.flush();
			logger.info("Employee deleted with id: {}",id);
			result="Employee deletion is successful for id: "+id;
		}
		
		return result;
		
	}
	


}


