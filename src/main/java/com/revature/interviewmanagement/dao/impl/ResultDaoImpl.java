package com.revature.interviewmanagement.dao.impl;

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

import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.entity.Result;
import com.revature.interviewmanagement.exception.DatabaseException;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Repository
public class ResultDaoImpl implements com.revature.interviewmanagement.dao.ResultDao {

	private static final Logger logger = LogManager.getLogger(ResultDaoImpl.class.getName());
	private static final LocalDateTime today = LocalDateTime.now(ZoneOffset.UTC);

	@Autowired
	private SessionFactory sessionFactory;

	private static final String GET_ALLRESULT = "SELECT r FROM Result r";
	private static final String GET_RESULTBYINTERVIEWID = "SELECT r FROM Result r WHERE r.interview.id=?1";
	private static final String GET_RESULTBYEMPID = "SELECT r FROM Result r WHERE r.interview.employee.id=?1";
	private static final String GET_RESULTBYCANDIDATEID = "SELECT r FROM Result r WHERE r.interview.candidate.id=?1 ";

	@Override
	public List<Result> getAllResult() {
		logger.info("entering getAllResult method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_ALLRESULT, Result.class).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public Result getResultById(Long id) {
		logger.info("entering getResultById method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Result.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public Result getResultByInterviewId(Long interviewId) {
		logger.info("entering getResultByInterviewId method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_RESULTBYINTERVIEWID, Result.class).setParameter(1, interviewId)
					.getSingleResult();
		} catch (NoResultException noResultException) {
			logger.warn(noResultException.getMessage());
			return null;
		}

		catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}

	}

	@Override
	public List<Result> getResultByEmployeeId(Long empId) {
		logger.info("entering getResultByEmployeeId method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_RESULTBYEMPID, Result.class).setParameter(1, empId).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Result> getResultByCandidateId(Long canId) {
		logger.info("entering getResultByCandidateId method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery(GET_RESULTBYCANDIDATEID, Result.class).setParameter(1, canId).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Transactional
	@Override
	public String addResult(Long interviewId, Result result) {
		logger.info("entering addResult method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Interview interview = session.load(Interview.class, interviewId);
			interview.setStatus("Finished");
			result.setInterview(interview);
			result.setAddedOn(today);
			session.save(result);
			logger.info("result added for the given interview id {}", interviewId);
			return RESULT_CREATE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_ADDING);
		}

	}

	@Transactional
	@Override
	public String updateResult(Result result) {
		logger.info("entering updateResult method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Long id = result.getId();
			result.setUpdatedOn(today);
			session.merge(result);
			session.flush();
			logger.info("result updated with id: {}", id);
			return RESULT_UPDATE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_UPDATING);
		}

	}

	@Transactional
	@Override
	public String deleteResult(Long id) {
		logger.info("entering deleteResult method");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(session.load(Result.class, id));
			session.flush();
			logger.info("result deleted with id: {}", id);
			return RESULT_DELETE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_DELETING);
		}

	}

}
