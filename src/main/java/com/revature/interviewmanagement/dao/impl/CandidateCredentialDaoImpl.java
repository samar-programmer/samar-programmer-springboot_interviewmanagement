package com.revature.interviewmanagement.dao.impl;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.interviewmanagement.dao.CandidateCredentialDao;
import com.revature.interviewmanagement.entity.credentials.CandidateCredential;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.model.credentials.CandidateCredentialDto;
import com.revature.interviewmanagement.util.mapper.CandidateCredentialMapper;

@Repository
public class CandidateCredentialDaoImpl implements CandidateCredentialDao {

	private static final Logger logger=LogManager.getLogger(CandidateCredentialDaoImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String EMAIL="email";
	
	private static final String CHECK_EMAIL="SELECT c FROM CandidateCredential c WHERE c.emailId=:email";
	private static final String CHECK_EMAIL_VALIDATE="SELECT c FROM CandidateCredential c WHERE c.emailId=:email";
	
	
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

	@Override
	public CandidateCredential validateCredential(CandidateCredentialDto candidateCredentialDto) {
		Session session=sessionFactory.getCurrentSession();
		
		logger.info("Entering validateCredential method");
		@SuppressWarnings("unchecked")
		List<CandidateCredential> resultList=session.createQuery(CHECK_EMAIL_VALIDATE)
		.setParameter(EMAIL,candidateCredentialDto.getEmailId())
		.getResultList();
		return (resultList.isEmpty()?null:resultList.get(0));
	}

	@Transactional
	@Override
	public String addCredential(CandidateCredentialDto candidateCredentialDto) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering addCredential method");
		@SuppressWarnings("unchecked")
		List<CandidateCredential> resultList=session.createQuery(CHECK_EMAIL)
				.setParameter(EMAIL,candidateCredentialDto.getEmailId())
				.getResultList();
		if(resultList !=null && resultList.isEmpty()) {
			CandidateCredential candidateCredential=CandidateCredentialMapper.candidateCredentialMapper(candidateCredentialDto);
			Long id=(Long) session.save(candidateCredential);
			return "Candidate credentials created successfully with id "+id+" !";
		}
		else {
			throw new DuplicateIdException("Entered Email id already exists in another record");
		}
		
	}

	@Transactional
	@Override
	public String updatePassword(Long id, CandidateCredentialDto candidateCredentialDto) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering updateCredential method");
		
		CandidateCredential updateObj=null;
		try {
			updateObj=session.load(CandidateCredential.class,id);
			if(!updateObj.getEmailId().isEmpty()) {  //necessary line to continue the flow 
						
				CandidateCredential candidateCredential=CandidateCredentialMapper.candidateCredentialMapper(candidateCredentialDto);
				candidateCredential.setId(id);
				candidateCredential.setAddedOn(updateObj.getAddedOn());
				candidateCredential.setUpdatedOn(updateObj.getUpdatedOn());
				candidateCredential.setUpdatedBy(updateObj.getUpdatedBy());
				session.merge(candidateCredential);
				session.flush();
				}
					
		} 
		catch (org.hibernate.ObjectNotFoundException e1) {
			logger.warn("unable to update candidate credentials, message: {}",e1.getMessage(),e1);
			throw new IdNotFoundException("Updation is failed...entered id doesn't exist");
		}
		
		return "Candidate credentials updated successfully for id "+id+" !";
		
	}

	@Transactional
	@Override
	public Boolean resetPassword(CandidateCredentialDto candidateCredentialDto) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering resetPassword method");
		
		@SuppressWarnings("unchecked")
		List<CandidateCredential> resultList=session.createQuery(CHECK_EMAIL)
				.setParameter(EMAIL,candidateCredentialDto.getEmailId())
				.getResultList();
		CandidateCredential candidateCredential=CandidateCredentialMapper.candidateCredentialMapper(candidateCredentialDto);
		candidateCredential.setId(resultList.get(0).getId());
		candidateCredential.setAddedOn(resultList.get(0).getAddedOn());
		candidateCredential.setUpdatedOn(resultList.get(0).getUpdatedOn());
		candidateCredential.setUpdatedBy(resultList.get(0).getUpdatedBy());
		session.merge(candidateCredential);
		session.flush();

		return !resultList.isEmpty();
		
	}

	

}
