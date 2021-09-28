package com.revature.interviewmanagement.dao.impl;



import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.interviewmanagement.dao.CandidateDao;
import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.util.mapper.CandidateMapper;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Repository
public class CandidateDaoImpl implements CandidateDao{
	
	private static final Logger logger=LogManager.getLogger(CandidateDaoImpl.class);
	private static final LocalDateTime today=LocalDateTime.now(ZoneOffset.UTC);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String GET_CANDIDATE_BYPHONE="SELECT c FROM Candidate c WHERE c.phoneNumber=?1";
	private static final String GET_CANDIDATE_BYNAME="SELECT c FROM Candidate c WHERE  CONCAT(c.firstName,' ', c.lastName) LIKE ?1";
	private static final String GET_CANDIDATE_BYEMAIL="SELECT c FROM Candidate c WHERE c.emailId=?1";
	private static final String GET_CANDIDATE_BYEXPERIENCE="SELECT c FROM Candidate c WHERE c.experience=?1";
	private static final String GET_CANDIDATE_BYROLE="SELECT c FROM Candidate c WHERE c.jobRole=?1";
	private static final String GET_ALLCANDIDATE="SELECT c FROM Candidate c";
	
	

	@Transactional
	@Override
	public String addCandidate(CandidateDto candidateDto) {
		logger.trace("Entered addCandidate method");
		
		try {
			Session session=sessionFactory.getCurrentSession();
			Candidate candidate=CandidateMapper.candidateEntityMapper(candidateDto);
			candidate.setAddedOn(today);
			session.save(candidate);
			logger.info("candidate details added");
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_ADDING);
		}
		return CANDIDATE_CREATE;
	}

	@Transactional
	@Override
	public String updateCandidate(CandidateDto candidateDto) {
		logger.trace("Entered updateCandidate method");
		try {
			Session session=sessionFactory.getCurrentSession();
			final Long id=candidateDto.getId();
			Candidate candidate=CandidateMapper.candidateEntityMapper(candidateDto);
			candidate.setUpdatedOn(today);
			session.merge(candidate);
			session.flush();
			logger.info("Candidate updated with id: {}",id);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_UPDATING);
		}
		return CANDIDATE_UPDATE;
	
	}

	@Transactional
	@Override
	public String deleteCandidate(Long id) {
		logger.trace("Entered deleteCandidate method");
		try {
			Session session=sessionFactory.getCurrentSession();
			session.delete(session.load(Candidate.class,id));
			session.flush();
			logger.info("candidate was deleted with id: {}",id);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_DELETING);
		}
		return CANDIDATE_DELETE;
	
	}

	@Override
	public List<Candidate> getCandidateByPhoneNumber(String phoneNumber) {
		logger.trace("Entered getCandidateByPhoneNumber method");
		List<Candidate> resultList=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			resultList = session.createQuery(GET_CANDIDATE_BYPHONE,Candidate.class).setParameter(1,phoneNumber).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
		return resultList;
	}

	@Override
	public List<Candidate> getCandidateByName(String name) {
		logger.trace("Entered getCandidateByName method");
		List<Candidate> resultList=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			resultList = session.createQuery(GET_CANDIDATE_BYNAME,Candidate.class).setParameter(1, "%"+name+"%").getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
		return resultList;
	}

	@Override
	public List<Candidate> getCandidateByExperience(Integer exp) {
		logger.trace("Entered getCandidateByExperience method");
		List<Candidate> resultList=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			resultList = session.createQuery(GET_CANDIDATE_BYEXPERIENCE,Candidate.class).setParameter(1,exp).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
		return resultList;
	}

	@Override
	public List<Candidate> getCandidateByRole(String role) {
		logger.trace("Entered getCandidateByPhoneNumber method");
		List<Candidate> resultList=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			resultList = session.createQuery(GET_CANDIDATE_BYROLE,Candidate.class).setParameter(1,role).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
		return resultList;
	}

	@Override
	public List<Candidate> getCandidateByEmailId(String email) {
		logger.trace("Entered getCandidateByEmailId method");
		List<Candidate> resultList=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			resultList = session.createQuery(GET_CANDIDATE_BYEMAIL,Candidate.class).setParameter(1,email).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
		return resultList;
	}

	@Override
	public Candidate getCandidateById(Long id) {
		logger.trace("Entered getCandidateById method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.get(Candidate.class,id);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Candidate> getAllCandidate() {
		logger.trace("Entered getAllCandidate method");
		List<Candidate> resultList=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			resultList = session.createQuery(GET_ALLCANDIDATE,Candidate.class).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
		return resultList;
	}

}
