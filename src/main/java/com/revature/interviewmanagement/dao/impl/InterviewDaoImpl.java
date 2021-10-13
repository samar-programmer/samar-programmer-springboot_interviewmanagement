package com.revature.interviewmanagement.dao.impl;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.interviewmanagement.dao.InterviewDao;
import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.exception.DatabaseException;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Repository
public class InterviewDaoImpl implements InterviewDao {

	private static final Logger logger = LogManager.getLogger(InterviewDaoImpl.class.getName());
	private static final LocalDateTime today = LocalDateTime.now(ZoneOffset.UTC);

	@Autowired
	private SessionFactory sessionFactory;

	private static final String GET_ALLINTERVIEW = "SELECT i FROM Interview i";
	private static final String GET_INTERVIEW_SCHEDULEDDATE = "SELECT i FROM Interview i WHERE i.callScheduledDate=?1";
	private static final String GET_INTERVIEW_CANDIDATEID = "SELECT i FROM Interview i WHERE i.candidate.id=?1";
	private static final String GET_INTERVIEW_CANDIDATENAME = "SELECT i FROM Interview i WHERE CONCAT(i.candidate.firstName,' ', i.candidate.lastName) LIKE :name ";
	private static final String GET_INTERVIEW_CANDIDATEEMAIL = "SELECT i FROM Interview i WHERE i.candidate.emailId=?1";
	private static final String GET_INTERVIEW_CANDIDATEPHONE = "SELECT i FROM Interview i WHERE i.candidate.phoneNumber=?1";
	private static final String GET_INTERVIEW_CANDIDATEROLE = "SELECT i FROM Interview i WHERE i.candidate.jobRole=?1";
	private static final String GET_INTERVIEW_CANDIDATEEXPERIENCE = "SELECT i FROM Interview i WHERE i.candidate.experience=?1";
	private static final String GET_INTERVIEW_EMPLOYEEID = "SELECT i FROM Interview i WHERE i.employee.id=?1";
	private static final String GET_INTERVIEW_EMPLOYEENAME = "SELECT i FROM Interview i WHERE CONCAT(i.employee.firstName,' ', i.employee.lastName) LIKE :name ";
	private static final String GET_INTERVIEW_EMPLOYEEDESIGNATION = "SELECT i FROM Interview i WHERE i.employee.designation=?1";
	private static final String GET_INTERVIEW_EMPLOYEESTATUS = "SELECT i FROM Interview i WHERE i.employee.status=?1";
	private static final String GET_INTERVIEW_EMPLOYEEEMAIL = "SELECT i FROM Interview i WHERE i.employee.emailId=?1 and i.status NOT IN ('Cancelled','Finished') ";
	private static final String GET_INTERVIEW_EMPLOYEEPHONE = "SELECT i FROM Interview i WHERE i.employee.phoneNumber=?1";
	private static final String GET_INTERVIEW_TYPE = "SELECT i FROM Interview i WHERE i.interviewType=?1";
	private static final String GET_ALLINTERVIEWTYPE = "SELECT * FROM interview_type";
	private static final String VALIDATE_CANDIDATE_FOR_INTERVIEW = "SELECT i FROM Interview i WHERE i.candidate.id=:canId AND i.status IN ('Live','Rescheduled') ";

	@Override
	public List<Interview> getAllInterview() {
		logger.info("entering getAllInterview method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_ALLINTERVIEW, Interview.class).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public Interview getInterviewById(Long id) {
		logger.info("entering getInterviewById method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Interview.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	/**
	 * method is used to check whether candidate having a live/Rescheduled interview, returns
	 * true if candidate having a live/Rescheduled interview otherwise false
	 * 
	 * @param id id of the candidate
	 * @return boolean based on the state of interview, true if candidate having a live/Rescheduled interview
	 */
	@Override
	public boolean isCandidateHasLiveInterview(Long canId) {
		logger.info("entering isCandidateHasLiveInterview method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(VALIDATE_CANDIDATE_FOR_INTERVIEW, Interview.class).setParameter("canId", canId)
					.getSingleResult() != null;
		} catch (NoResultException e) {
			logger.warn(e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_OCCURED);
		}
	}
	
	@Override
	public Long isCandidateHasLiveInterviewForUpdate(Long canId) {
		logger.info("entering isCandidateHasLiveInterviewForUpdate method");
		try {
			Session session = sessionFactory.getCurrentSession();
			//check if candidate already has a live or rescheduled interview, if yes, it returns id of the interview
			return session.createQuery(VALIDATE_CANDIDATE_FOR_INTERVIEW, Interview.class).setParameter("canId", canId)
					.getSingleResult().getId();
		} catch (NoResultException e) {
			logger.warn(e.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_OCCURED);
		}
	}

	@Override
	public List<Interview> getInterviewByType(String type) {
		logger.info("entering getInterviewByType method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_TYPE, Interview.class).setParameter(1, type).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByScheduledDate(LocalDate scheduledDate) {
		logger.info("entering getInterviewByScheduledDate method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_SCHEDULEDDATE, Interview.class).setParameter(1, scheduledDate)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateId(Long canId) {
		logger.info("Entered getInterviewByCandidateId method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_CANDIDATEID, Interview.class).setParameter(1, canId)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateName(String name) {
		logger.info("entering getInterviewByCandidateName method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_CANDIDATENAME, Interview.class)
					.setParameter("name", "%" + name + "%").getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByCandidatePhone(String phone) {
		logger.info("entering getInterviewByCandidatePhone method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_CANDIDATEPHONE, Interview.class).setParameter(1, phone)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateEmailId(String email) {
		logger.info("entering getInterviewByCandidateEmail method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_CANDIDATEEMAIL, Interview.class).setParameter(1, email)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateRole(String role) {
		logger.info("entering getInterviewByCandidateRole method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_CANDIDATEROLE, Interview.class).setParameter(1, role)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateExperience(String exp) {
		logger.info("entering getInterviewByCandidateExperience method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_CANDIDATEEXPERIENCE, Interview.class).setParameter(1, exp)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeId(Long employeeId) {
		logger.info("entering getInterviewByEmployeeId method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_EMPLOYEEID, Interview.class).setParameter(1, employeeId)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeStatus(String status) {
		logger.info("entering getInterviewByEmployeeStatus method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_EMPLOYEESTATUS, Interview.class).setParameter(1, status)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeDesignation(String designation) {
		logger.info("entering getInterviewByEmployeeDesignation method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_EMPLOYEEDESIGNATION, Interview.class).setParameter(1, designation)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeName(String name) {
		logger.info("entering getInterviewByEmployeeName method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_EMPLOYEENAME, Interview.class)
					.setParameter("name", "%" + name + "%").getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeePhone(String phone) {
		logger.info("entering getInterviewByEmployeePhone method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_EMPLOYEEPHONE, Interview.class).setParameter(1, phone)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeEmailId(String email) {
		logger.info("entering getInterviewByEmployeeEmail method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_EMPLOYEEEMAIL, Interview.class).setParameter(1, email)
					.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Transactional
	@Override
	public String deleteInterview(Long id) {
		logger.info("entering deleteInterview method");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(session.load(Interview.class, id));
			session.flush();
			logger.info("Interview deleted with id: {}", id);
			return INTERVIEW_DELETE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_DELETING);
		}

	}

	@Transactional
	@Override
	public String updateInterview(Interview interview) {
		logger.info("entering updateInterview method");
		try {
			Session session = sessionFactory.getCurrentSession();
			final Long id = interview.getId();
			interview.setUpdatedOn(today);
			session.merge(interview);
			session.flush();
			logger.info("Interview details updated with id: {}", id);
			return INTERVIEW_UPDATE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_UPDATING);
		}

	}

	@Transactional
	@Override
	public String addInterview(Interview interview, Long canId, Long empId) {
		logger.info("entering addInterview method");
		try {
			Session session = sessionFactory.getCurrentSession();
			interview.setCandidate(session.load(Candidate.class, canId));
			interview.setEmployee(session.load(Employee.class, empId));
			interview.setStatus("Live");
			interview.setAddedOn(today);
			session.save(interview);
			logger.info("Interview details inserted");
			return INTERVIEW_CREATE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_ADDING);
		}

	}

	// This method will return interview types stored in database. There is no
	// relation with entity, so return type is generic
	@Override
	public List<?> getAllInterviewType() {

		logger.info("entering getAllInterviewType method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createNativeQuery(GET_ALLINTERVIEWTYPE).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}



}
