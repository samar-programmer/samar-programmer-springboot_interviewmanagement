package com.revature.interviewmanagement.dao.impl;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
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
import com.revature.interviewmanagement.model.InterviewDto;
import com.revature.interviewmanagement.util.mapper.InterviewMapper;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Repository
public class InterviewDaoImpl implements InterviewDao {

	private static final Logger logger=LogManager.getLogger(InterviewDaoImpl.class.getName());
	private static final LocalDateTime today=LocalDateTime.now(ZoneOffset.UTC);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String GET_ALLINTERVIEW="SELECT i FROM Interview i";
	private static final String GET_INTERVIEW_SCHEDULEDDATE="SELECT i FROM Interview i WHERE i.callScheduledDate=?1";
	private static final String GET_INTERVIEW_CANDIDATEID="SELECT i FROM Interview i WHERE i.candidate.id=?1";
	private static final String GET_INTERVIEW_CANDIDATENAME="SELECT i FROM Interview i WHERE CONCAT(i.candidate.firstName,' ', i.candidate.lastName) LIKE :name ";
	private static final String GET_INTERVIEW_CANDIDATEEMAIL="SELECT i FROM Interview i WHERE i.candidate.emailId=?1";
	private static final String GET_INTERVIEW_CANDIDATEPHONE="SELECT i FROM Interview i WHERE i.candidate.phoneNumber=?1";
	private static final String GET_INTERVIEW_CANDIDATEROLE="SELECT i FROM Interview i WHERE i.candidate.jobRole=?1";
	private static final String GET_INTERVIEW_CANDIDATEEXPERIENCE="SELECT i FROM Interview i WHERE i.candidate.experience=?1";
	private static final String GET_INTERVIEW_EMPID="SELECT i FROM Interview i WHERE i.employee.id=?1";
	private static final String GET_INTERVIEW_EMPLOYEEID="SELECT i FROM Interview i WHERE i.employee.employeeId=?1";
	private static final String GET_INTERVIEW_EMPLOYEENAME="SELECT i FROM Interview i WHERE CONCAT(i.employee.firstName,' ', i.employee.lastName) LIKE :name ";
	private static final String GET_INTERVIEW_EMPLOYEEDESIGNATIONID="SELECT i FROM Interview i WHERE i.employee.designationId=?1";
	private static final String GET_INTERVIEW_EMPLOYEEEMAIL="SELECT i FROM Interview i WHERE i.employee.emailId=?1 and i.status not in ('Cancelled','Finished') ";
	private static final String GET_INTERVIEW_EMPLOYEEPHONE="SELECT i FROM Interview i WHERE i.employee.phoneNumber=?1";
	private static final String GET_INTERVIEW_TYPE="SELECT i FROM Interview i WHERE i.interviewType=?1";
	
	
	
	@Override
	public List<Interview> getAllInterview() {
		logger.trace("entering getAllInterview method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_ALLINTERVIEW,Interview.class).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public Interview getInterviewById(Long id) {
		logger.trace("entering getInterviewById method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.get(Interview.class,id);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}
	
	@Override
	public List<Interview> getInterviewByType(String type) {
		logger.trace("entering getInterviewByType method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_TYPE,Interview.class).setParameter(1,type).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByScheduledDate(LocalDate scheduledDate) {
		logger.trace("entering getInterviewByScheduledDate method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_SCHEDULEDDATE,Interview.class).setParameter(1,scheduledDate).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateId(Long canId) {
		logger.trace("Entered getInterviewByCandidateId method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_CANDIDATEID,Interview.class).setParameter(1,canId).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateName(String name) {
		logger.trace("entering getInterviewByCandidateName method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_CANDIDATENAME,Interview.class).setParameter("name", "%"+name+"%").getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByCandidatePhone(String phone) {
		logger.trace("entering getInterviewByCandidatePhone method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_CANDIDATEPHONE,Interview.class).setParameter(1,phone).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateEmailId(String email) {
		logger.trace("entering getInterviewByCandidateEmail method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_CANDIDATEEMAIL,Interview.class).setParameter(1,email).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateRole(String role) {
		logger.trace("entering getInterviewByCandidateRole method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_CANDIDATEROLE,Interview.class).setParameter(1,role).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateExperience(Integer exp) {
		logger.trace("entering getInterviewByCandidateExperience method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_CANDIDATEEXPERIENCE,Interview.class).setParameter(1,exp).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByEmpId(Long empId) {//this empId is auto generated id(Long) in employee entity not the employeeId(String) of employee entity
		logger.trace("entering getInterviewByEmpId method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_EMPID,Interview.class).setParameter(1,empId).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}
	
	@Override
	public List<Interview> getInterviewByEmployeeId(Long employeeId) {//this empId is auto generated id(Long) in employee entity not the employeeId(String) of employee entity
		logger.trace("entering getInterviewByEmployeeId method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_EMPLOYEEID,Interview.class).setParameter(1,employeeId).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByDesignationId(Long destId) {
		logger.trace("entering getInterviewByDesignationId method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_EMPLOYEEDESIGNATIONID,Interview.class).setParameter(1,destId).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeName(String name) {
		logger.trace("entering getInterviewByEmployeeName method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_EMPLOYEENAME,Interview.class).setParameter("name","%"+name+"%").getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeePhone(String phone) {
		logger.trace("entering getInterviewByEmployeePhone method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_EMPLOYEEPHONE,Interview.class).setParameter(1,phone).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeEmailId(String email) {
		logger.trace("entering getInterviewByEmployeeEmail method");
		try {
			Session session=sessionFactory.getCurrentSession();
			return session.createQuery(GET_INTERVIEW_EMPLOYEEEMAIL,Interview.class).setParameter(1,email).getResultList();
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_READING);
		}
	}

	@Transactional
	@Override
	public String deleteInterview(Long id) {
		logger.trace("entering deleteInterview method");
		try {
			Session session=sessionFactory.getCurrentSession();
			session.delete(session.load(Interview.class, id));
			session.flush();
			logger.info("Interview deleted with id: {}",id);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_DELETING);
		}
		
		return INTERVIEW_DELETE;
		
	}
	
	@Transactional
	@Override
	public String updateInterview(InterviewDto interviewDto) {
		logger.trace("entering updateInterview method");
		try {
			Session session=sessionFactory.getCurrentSession();
			final Long id=interviewDto.getId();
			Interview interview=InterviewMapper.interviewEntityMapper(interviewDto);
			interview.setUpdatedOn(today);
			session.merge(interview);
			session.flush();
			logger.info("Interview details updated with id: {}",id);
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_UPDATING);
		}
		return INTERVIEW_UPDATE;
		
	}
	
	@Transactional
	@Override
	public String addInterview(InterviewDto interviewDto,Long canId,Long empId) {
		logger.trace("entering addInterview method");
		try {
			Session session=sessionFactory.getCurrentSession();
			Interview interview=InterviewMapper.interviewEntityMapper(interviewDto);
			interview.setCandidate(session.load(Candidate.class,canId));
			interview.setEmployee(session.load(Employee.class,empId));
			interview.setAddedOn(today);
			session.save(interview);
			logger.info("Interview details inserted");
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new DatabaseException(ERROR_IN_ADDING);
		}
			
		return INTERVIEW_CREATE;
	
	}

}
