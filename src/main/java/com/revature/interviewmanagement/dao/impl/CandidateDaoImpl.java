package com.revature.interviewmanagement.dao.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.interviewmanagement.dao.CandidateDao;
import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.model.CandidateDto;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Repository
public class CandidateDaoImpl implements CandidateDao {

	private static final Logger logger = LogManager.getLogger(CandidateDaoImpl.class);
	private static final LocalDateTime today = LocalDateTime.now(ZoneOffset.UTC);

	@Autowired
	private SessionFactory sessionFactory;

	private static final String GET_CANDIDATE_BYPHONE = "SELECT c FROM Candidate c WHERE c.phoneNumber=?1";
	private static final String GET_CANDIDATE_BYNAME = "SELECT c FROM Candidate c WHERE  CONCAT(c.firstName,' ', c.lastName) LIKE ?1";
	private static final String GET_CANDIDATE_BYEMAIL = "SELECT c FROM Candidate c WHERE c.emailId=?1";
	private static final String GET_CANDIDATE_BYEXPERIENCE = "SELECT c FROM Candidate c WHERE c.experience=?1";
	private static final String GET_CANDIDATE_BYROLE = "SELECT c FROM Candidate c WHERE c.jobRole=?1";
	private static final String GET_ALLCANDIDATE = "SELECT c FROM Candidate c";
	private static final String GET_EXPERIENCE = "SELECT * FROM experience";
	private static final String GET_JOBROLE = "SELECT * FROM job_role";
	private static final String VALIDATE_JOBROLE = "SELECT c FROM Candidate c WHERE c.emailId=:email AND (c.jobRole=:role AND c.addedOn >= :dueDate) ";

	@Transactional
	@Override
	public String addCandidate(Candidate candidate) {
		logger.info("Entered addCandidate method");

		try {
			Session session = sessionFactory.getCurrentSession();
			candidate.setAddedOn(today);
			session.save(candidate);
			logger.info("candidate details added");
			return CANDIDATE_CREATE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_ADDING);
		}

	}

	@Transactional
	@Override
	public String updateCandidate(Candidate candidate) {
		logger.info("Entered updateCandidate method");
		try {
			Session session = sessionFactory.getCurrentSession();
			final Long id = candidate.getId();
			candidate.setUpdatedOn(today);
			session.merge(candidate);
			session.flush();
			logger.info("Candidate updated with id: {}", id);
			return CANDIDATE_UPDATE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_UPDATING);
		}

	}

	@Transactional
	@Override
	public String deleteCandidate(Long id) {
		logger.info("Entered deleteCandidate method");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(session.load(Candidate.class, id));
			session.flush();
			logger.info("candidate was deleted with id: {}", id);
			return CANDIDATE_DELETE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_DELETING);
		}

	}

	@Override
	public List<Candidate> getCandidateByPhoneNumber(String phoneNumber) {
		logger.info("Entered getCandidateByPhoneNumber method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_CANDIDATE_BYPHONE, Candidate.class).setParameter(1, phoneNumber)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}

	}

	@Override
	public List<Candidate> getCandidateByName(String name) {
		logger.info("Entered getCandidateByName method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_CANDIDATE_BYNAME, Candidate.class).setParameter(1, "%" + name + "%")
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Candidate> getCandidateByExperience(String exp) {
		logger.info("Entered getCandidateByExperience method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_CANDIDATE_BYEXPERIENCE, Candidate.class).setParameter(1, exp)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Candidate> getCandidateByRole(String role) {
		logger.info("Entered getCandidateByPhoneNumber method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_CANDIDATE_BYROLE, Candidate.class).setParameter(1, role).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Candidate> getCandidateByEmailId(String email) {
		logger.info("Entered getCandidateByEmailId method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_CANDIDATE_BYEMAIL, Candidate.class).setParameter(1, email).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}

	}

	@Override
	public Candidate getCandidateById(Long id) {
		logger.info("Entered getCandidateById method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Candidate.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Candidate> getAllCandidate() {
		logger.info("Entered getAllCandidate method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_ALLCANDIDATE, Candidate.class).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}

	}

	@Override
	public List<?> getAllExperience() {
		logger.info("Entered getExperience method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createNativeQuery(GET_EXPERIENCE).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}

	}

	@Override
	public List<?> getAllJobRole() {
		logger.info("Entered getAllJobRole method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createNativeQuery(GET_JOBROLE).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}

	}

	/*
	 * The logic is to set dueDate as 'today minus 3 months', first it checks for
	 * applied job role, if matches, then it checks for applied date, we cannot
	 * apply if applied date is greater than or equal to dueDate(which is today
	 * minus 3 months). If result set is null then we can apply for the role that
	 * means a candidate didn't apply for the role or applied but it satisfies
	 * dueDate condition
	 */
	@Override
	public Candidate validateJobRole(CandidateDto candidateDto) {
		logger.info("Entered getAllJobRole method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(VALIDATE_JOBROLE, Candidate.class)
					.setParameter("email", candidateDto.getEmailId()).setParameter("role", candidateDto.getJobRole())
					.setParameter("dueDate", today.minusMonths(3)).getSingleResult();

		} catch (NoResultException noResultException) {
			logger.warn(noResultException.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_OCCURED);
		}

	}
}
