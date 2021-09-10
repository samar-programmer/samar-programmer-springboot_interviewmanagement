package com.revature.interviewmanagement.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.interviewmanagement.dao.EmployeeCredentialDao;
import com.revature.interviewmanagement.entity.credentials.CandidateCredential;
import com.revature.interviewmanagement.entity.credentials.EmployeeCredential;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;

@Repository
public class EmployeeCredentialDaoImpl implements EmployeeCredentialDao {

	private static final Logger logger=LogManager.getLogger(EmployeeCredentialDaoImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String EMAIL="email";
	
	private static final String CHECK_EMAIL_ADD="SELECT e FROM EmployeeCredential e WHERE e.emailId=:email";
	private static final String CHECK_EMAIL_UPDATE="SELECT e FROM EmployeeCredential e WHERE e.emailId=:email AND e.id !=:id";
	private static final String CHECK_CREDENTIALS="SELECT e FROM EmployeeCredential e WHERE e.emailId=:email AND e.password=:password";
	
	
	
	
	@Transactional
	@Override
	public String updateCredential(Long id, EmployeeCredential employeeCredential) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering updateCredential method");
		
		EmployeeCredential updateObj=null;
		try {
			updateObj=session.load(EmployeeCredential.class,id);
			if(!updateObj.getEmailId().isEmpty()) {  //necessary line to continue the flow 
				
					@SuppressWarnings("unchecked")
					List<CandidateCredential> resultList=session.createQuery(CHECK_EMAIL_UPDATE)
							.setParameter(EMAIL,employeeCredential.getEmailId())
							.setParameter("id",id)
							.getResultList();
					if(resultList !=null && resultList.isEmpty()) {
						employeeCredential.setId(id);
						session.merge(employeeCredential);
						session.flush();
						
					}
					else {
						throw new DuplicateIdException("Entered Email id already exists in another record");
					}
			}
		} 
		catch (org.hibernate.ObjectNotFoundException e1) {
			logger.warn("unable to update employee credentials, message: {}",e1.getMessage(),e1);
			throw new IdNotFoundException("Updation is failed...entered Id doesn't exist");
		}
		
		return "Employee credentials updated successfully for id "+id+" !";
		
	}

	@Transactional
	@Override
	public String addCredential(EmployeeCredential employeeCredential) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering addCredential method");
		@SuppressWarnings("unchecked")
		List<CandidateCredential> resultList=session.createQuery(CHECK_EMAIL_ADD)
				.setParameter(EMAIL,employeeCredential.getEmailId())
				.getResultList();
		if(resultList !=null && resultList.isEmpty()) {
			Long id=(Long) session.save(employeeCredential);
			return "Employee credentials created successfully with id "+id+" !";
		}
		else {
			throw new DuplicateIdException("Entered Email id already exists in another record");
		}
	}

	@Override
	public Boolean validateCredential(EmployeeCredential employeeCredential) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering validateCredential method");
		@SuppressWarnings("unchecked")
		List<CandidateCredential> resultList=session.createQuery(CHECK_CREDENTIALS)
		.setParameter(EMAIL,employeeCredential.getEmailId())
		.setParameter("password",employeeCredential.getPassword())
		.getResultList();
		return !resultList.isEmpty();
	}

	@Override
	public EmployeeCredential getCredentialById(Long id) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering getCredentialById method");
		return session.get(EmployeeCredential.class,id);
	}

}
