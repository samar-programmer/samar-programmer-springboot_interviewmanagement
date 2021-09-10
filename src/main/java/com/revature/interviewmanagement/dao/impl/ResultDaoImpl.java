package com.revature.interviewmanagement.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.entity.Result;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;

@Repository
public class ResultDaoImpl implements com.revature.interviewmanagement.dao.ResultDao {
	
	private static final Logger logger=LogManager.getLogger(ResultDaoImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	static final String CHECK_RESULT_ALLRESULT="SELECT r FROM Result r";
	static final String CHECK_RESULT_RESULTBYINTERVIEWID="SELECT r FROM Result r WHERE r.interview.id=?1";
	static final String CHECK_RESULT_RESULTBYEMPID="SELECT r FROM Result r WHERE r.interview.employee.id=?1";
	static final String CHECK_RESULT_RESULTBYCANDIDATEID="SELECT r FROM Result r WHERE r.interview.candidate.id=?1 ";
	
	
	@Override
	public List<Result> getAllResult() {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entered getAllResult method");
		@SuppressWarnings("unchecked")
		List<Result> resultList=session.createQuery(CHECK_RESULT_ALLRESULT).getResultList();
		return resultList;
	}

	@Override
	public Result getResultById(Long id) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entered getResultById method");
		return session.get(Result.class,id);
	}

	
	@Override
	public List<Result> getResultByInterviewId(Long interviewId) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entered getResultByInterviewId method");
		@SuppressWarnings("unchecked")
		List<Result> result=session.createQuery(CHECK_RESULT_RESULTBYINTERVIEWID).setParameter(1,interviewId).getResultList();
		return result;
		
	}

	@Override
	public List<Result> getResultByEmployeeId(Long empId) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entered getResultByEmployeeId method");
		@SuppressWarnings("unchecked")
		List<Result> resultList=session.createQuery(CHECK_RESULT_RESULTBYEMPID).setParameter(1,empId).getResultList();
		return resultList;
	}

	@Override
	public List<Result> getResultByCandidateId(Long canId) {
		Session session=sessionFactory.getCurrentSession();
		logger.info("Entered getResultByCandidateId method");
		@SuppressWarnings("unchecked")
		List<Result> resultList=session.createQuery(CHECK_RESULT_RESULTBYCANDIDATEID).setParameter(1,canId).getResultList();
		return resultList;
	}

	@Transactional
	@Override
	public String addResult(Long interviewId, Result result) {
		Session session=sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Result> interviewCheck=session.createQuery(CHECK_RESULT_RESULTBYINTERVIEWID).setParameter(1,interviewId).getResultList();
		
		if(interviewCheck!=null && !interviewCheck.isEmpty()) {
			throw new DuplicateIdException("Can't add Result for this interview..This interview already has a result");
		}
		
		Long id=null;
		try {
				Interview interview=session.load(Interview.class,interviewId);
				result.setInterview(interview);
				id=(Long)session.save(result);
				logger.info("result added with id: {}",id);
				
			} 
		catch (HibernateException e1) {
			logger.error("unable to add result, message: {}",e1.getMessage(),e1);
				throw new IdNotFoundException("Entered Interview id doesn't exist");
			}
		
		return (id!=null)?"Result details inserted with id: "+id:"Couldn't create Result...Error occured while inserting";
	
	}

	@Transactional
	@Override
	public String updateResult(Long id, Result result) {
		Session session=sessionFactory.getCurrentSession();
		boolean check=false;
		String output=null;
		Result updateObj=null;
		try {
			updateObj=session.load(Result.class,id);
			if(updateObj.getStatus()!=null) {  //necessary line to continue the flow 
				check=true;
			}
		} 
		catch (org.hibernate.ObjectNotFoundException e1) {
			logger.error("unable to update result, message: {}",e1.getMessage(),e1);
			throw new IdNotFoundException("Updation is failed...entered id doesn't exist");
		}
		
		
			
		if(check) {
					result.setId(id);
					session.merge(result);
					session.flush();
					logger.info("result updated with id: {}",id);
					output="Updation is successful for id: "+id;
			} 
		return output;
		
	}

	@Transactional
	@Override
	public String deleteResult(Long id) {
		Session session=sessionFactory.getCurrentSession();
		String result=null;
		boolean check=false;
		Result deleteObject=null;
		try {
			deleteObject=session.load(Result.class, id);
			if(deleteObject.getStatus()!=null) { //necessary line to continue the flow 
				check=true;
			}
		}catch(org.hibernate.ObjectNotFoundException e) {
			logger.error("unable to delete result, message: {}",e.getMessage(),e);
			throw new IdNotFoundException("Deletion is failed...Entered Id doesn't exists");
		}
		 
		if(check) {
			session.delete(deleteObject);
			session.flush();
			logger.info("result deleted with id: {}",id);
			result="Deletion is successful for id: "+id;
		}
		
		return result;
		
	}

}
