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

@Repository
public class CandidateCredentialDaoImpl implements CandidateCredentialDao {

	private static final Logger logger=LogManager.getLogger(CandidateCredentialDaoImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String EMAIL="email";
	
	private static final String CHECK_EMAIL_ADD="SELECT c FROM CandidateCredential c WHERE c.emailId=:email";
	private static final String CHECK_EMAIL_UPDATE="SELECT c FROM CandidateCredential c WHERE c.emailId=:email AND c.id!=:id";
	private static final String CHECK_CREDENTIALS="SELECT c FROM CandidateCredential c WHERE c.emailId=:email AND c.password=:password";
	
	@Override
	public CandidateCredential getCredentialById(Long id) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering getCredentialById method");
		return session.get(CandidateCredential.class, id);
	}

	@Override
	public Boolean validateCredential(CandidateCredential candidateCredential) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering validateCredential method");
		@SuppressWarnings("unchecked")
		List<CandidateCredential> resultList=session.createQuery(CHECK_CREDENTIALS)
		.setParameter(EMAIL,candidateCredential.getEmailId())
		.setParameter("password",candidateCredential.getPassword())
		.getResultList();
		return !resultList.isEmpty();
	}

	@Transactional
	@Override
	public String addCredential(CandidateCredential candidateCredential) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering addCredential method");
		@SuppressWarnings("unchecked")
		List<CandidateCredential> resultList=session.createQuery(CHECK_EMAIL_ADD)
				.setParameter(EMAIL,candidateCredential.getEmailId())
				.getResultList();
		if(resultList !=null && resultList.isEmpty()) {
			Long id=(Long) session.save(candidateCredential);
			return "Candidate credentials created successfully with id "+id+" !";
		}
		else {
			throw new DuplicateIdException("Entered Email id already exists in another record");
		}
		
	}

	@Transactional
	@Override
	public String updateCredential(Long id, CandidateCredential candidateCredential) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entering updateCredential method");
		
		CandidateCredential updateObj=null;
		try {
			updateObj=session.load(CandidateCredential.class,id);
			if(!updateObj.getEmailId().isEmpty()) {  //necessary line to continue the flow 
				
					@SuppressWarnings("unchecked")
					List<CandidateCredential> resultList=session.createQuery(CHECK_EMAIL_UPDATE)
							.setParameter(EMAIL,candidateCredential.getEmailId())
							.setParameter("id",id)
							.getResultList();
					if(resultList !=null && resultList.isEmpty()) {
						candidateCredential.setId(id);
						session.merge(candidateCredential);
						session.flush();
					}
					else {
						throw new DuplicateIdException("Entered Email id already exists in another record");
					}
			}
		} 
		catch (org.hibernate.ObjectNotFoundException e1) {
			logger.warn("unable to update candidate credentials, message: {}",e1.getMessage(),e1);
			throw new IdNotFoundException("Updation is failed...entered id doesn't exist");
		}
		
		return "Candidate credentials updated successfully for id "+id+" !";
		
	}

}
