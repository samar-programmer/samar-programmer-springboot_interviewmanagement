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
import com.revature.interviewmanagement.model.credentials.EmployeeCredentialDto;
import com.revature.interviewmanagement.util.mapper.EmployeeCredentialMapper;

@Repository
public class EmployeeCredentialDaoImpl implements EmployeeCredentialDao {

	private static final Logger logger=LogManager.getLogger(EmployeeCredentialDaoImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String EMAIL="email";
	
	private static final String CHECK_EMAIL="SELECT e FROM EmployeeCredential e WHERE e.emailId=:email";
	private static final String CHECK_CREDENTIALS="SELECT e FROM EmployeeCredential e WHERE e.emailId=:email AND e.password=:password";
	
	
	
	
	@Transactional
	@Override
	public String updatePassword(Long id, EmployeeCredentialDto employeeCredentialDto) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering updateCredential method");
		
		EmployeeCredential updateObj=null;
		try {
			updateObj=session.load(EmployeeCredential.class,id);
			if(!updateObj.getEmailId().isEmpty()) {  //necessary line to continue the flow 
				
						EmployeeCredential employeeCredential=EmployeeCredentialMapper.candidateCredentialMapper(employeeCredentialDto);
						employeeCredential.setId(id);
						employeeCredential.setAddedOn(updateObj.getAddedOn());
						employeeCredential.setUpdatedOn(updateObj.getUpdatedOn());
						employeeCredential.setUpdatedBy(updateObj.getUpdatedBy());
						session.merge(employeeCredential);
						session.flush();
						
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
	public String addCredential(EmployeeCredentialDto employeeCredentialDto) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering addCredential method");
		@SuppressWarnings("unchecked")
		List<CandidateCredential> resultList=session.createQuery(CHECK_EMAIL)
				.setParameter(EMAIL,employeeCredentialDto.getEmailId())
				.getResultList();
		if(resultList !=null && resultList.isEmpty()) {
			EmployeeCredential employeeCredential=EmployeeCredentialMapper.candidateCredentialMapper(employeeCredentialDto);
			Long id=(Long) session.save(employeeCredential);
			return "Employee credentials created successfully with id "+id+" !";
		}
		else {
			throw new DuplicateIdException("Entered Email id already exists in another record");
		}
	}

	@Override
	public Boolean validateCredential(EmployeeCredentialDto employeeCredentialDto) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering validateCredential method");
		@SuppressWarnings("unchecked")
		List<CandidateCredential> resultList=session.createQuery(CHECK_CREDENTIALS)
		.setParameter(EMAIL,employeeCredentialDto.getEmailId())
		.setParameter("password",employeeCredentialDto.getPassword())
		.getResultList();
		return !resultList.isEmpty();
	}


	@Override
	public Boolean validateEmail(String email) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering validateEmailCredential method");
		@SuppressWarnings("unchecked")
		List<CandidateCredential> resultList=session.createQuery(CHECK_EMAIL)
				.setParameter(EMAIL,email)
				.getResultList();
		
		return !resultList.isEmpty();
	}

}
