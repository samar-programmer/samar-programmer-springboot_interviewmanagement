package com.revature.interviewmanagement.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.interviewmanagement.dao.RecoveryPasswordDao;
import com.revature.interviewmanagement.entity.credentials.CandidateCredential;
import com.revature.interviewmanagement.entity.credentials.RecoveryPassword;
import com.revature.interviewmanagement.model.credentials.RecoveryPasswordDto;
import com.revature.interviewmanagement.util.mapper.RecoveryPasswordMapper;

@Repository
public class RecoveryPasswordDaoImpl implements RecoveryPasswordDao {

private static final Logger logger=LogManager.getLogger(RecoveryPasswordDaoImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	private static final String CHECK_EMAIL_EXISTS="SELECT r FROM RecoveryPassword r WHERE r.emailId=:email";
	private static final String LOG_MESSAGE="Recovery code {} for emailId {} inside addCode method";
	private static final String CHECK_EMAIL_CANDIDATE_CREDENTIAL="SELECT c FROM CandidateCredential c WHERE c.emailId=:email";
	private static final String EMAIL="email";
	
	
	@Transactional
	@Override
	public void addCode(RecoveryPasswordDto recoveryPasswordDto, String forgotPasswordCode) {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<RecoveryPassword> list=session.createQuery(CHECK_EMAIL_EXISTS).setParameter(EMAIL,recoveryPasswordDto.getEmailId()).getResultList();
		if(list!=null && !list.isEmpty()) {
			RecoveryPassword recoveryPasswordExists=list.get(0);
			recoveryPasswordExists.setCode(forgotPasswordCode);
			session.merge(recoveryPasswordExists);
			logger.trace(LOG_MESSAGE,"updated",recoveryPasswordDto.getEmailId());
		}else {
				RecoveryPassword recoveryPassword=RecoveryPasswordMapper.recoveryPasswordMapper(recoveryPasswordDto);
				recoveryPassword.setEmailId(recoveryPasswordDto.getEmailId());
				recoveryPassword.setCode(forgotPasswordCode);
				@SuppressWarnings("unchecked")
				List<CandidateCredential> candidateCredential=session.createQuery(CHECK_EMAIL_CANDIDATE_CREDENTIAL).setParameter(EMAIL,recoveryPasswordDto.getEmailId()).getResultList();
				if(candidateCredential!=null && !candidateCredential.isEmpty())
					recoveryPassword.setCandidateCredential(candidateCredential.get(0));
				session.save(recoveryPassword);
				logger.trace(LOG_MESSAGE,"added",recoveryPasswordDto.getEmailId());
			
		}
		
	}


	@Override
	public Boolean validateCode(RecoveryPasswordDto userInput) {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<RecoveryPassword> list=session.createQuery(CHECK_EMAIL_EXISTS).setParameter(EMAIL,userInput.getEmailId()).getResultList();
		if(list!=null && !list.isEmpty()) {
			RecoveryPassword knownCredential=list.get(0);
			logger.trace(LOG_MESSAGE,"validate",userInput.getEmailId());
			if(knownCredential.getCode().equals(userInput.getCode()) ) {
				return Boolean.TRUE;
			}
			
		}else {
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}



}
