package com.revature.interviewmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.interviewmanagement.dao.CandidateDao;
import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;

@Repository
@Transactional
public class CandidateDaoImpl implements CandidateDao{
	
	static final LocalDateTime localTime=LocalDateTime.now();
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String addCandidate(Candidate candidate) {
		Session session=sessionFactory.getCurrentSession();
		
		Long id=null;
		try {
			
			Query<?> emailQuery = session.getNamedQuery("callCandidateByEmailProcedure")
				    .setParameter("email",candidate.getEmailId());
			Query<?> phoneQuery = session.getNamedQuery("callCandidateByPhoneProcedure")
				    .setParameter("phone",candidate.getPhoneNumber());
			
				if(emailQuery.list().isEmpty() && phoneQuery.list().isEmpty() ) {
					id=(Long)session.save(candidate);
				}
				else if(!emailQuery.list().isEmpty()){
					throw new DuplicateIdException("Entered email id already exists");
				}
				else {
					throw new DuplicateIdException("Entered phone number already exists");
				}
			
			
			
		} catch (HibernateException e1) {
			
			e1.printStackTrace();
		}
		
		return (id!=null)?"Candidate details inserted with id: "+id+" at "+localTime:"Couldn't create candidate...Error occured while inserting";
	}

	@Override
	public String updateCandidate(Long id,Candidate candidate) {
		Session session=sessionFactory.getCurrentSession();
		boolean check=false;
		String result=null;
		Candidate updateObj=null;
	try {
		updateObj=session.load(Candidate.class,id);
			if(!updateObj.getEmailId().isEmpty()) { //necessary line to continue the flow 
				check=true;
			}
		} 
		catch(org.hibernate.ObjectNotFoundException e) {
			throw new IdNotFoundException("Updation is failed...entered id doesn't exist");
		}
			
		if(check) {
			Query<?> emailQuery = session.getNamedQuery("callCandidateByEmailUpdateProcedure")
				    .setParameter("email",candidate.getEmailId())
				    .setParameter("id",id);
			Query<?> phoneQuery = session.getNamedQuery("callCandidateByPhoneUpdateProcedure")
				    .setParameter("phone",candidate.getPhoneNumber())
				    .setParameter("id",id);
			
				if(emailQuery.list().isEmpty() && phoneQuery.list().isEmpty()) {
					candidate.setId(id);
					session.merge(candidate);
					session.flush();
					result="updation is successful for id: "+id;
				}
				else if(!emailQuery.list().isEmpty()){
					throw new DuplicateIdException("Updation is failed...Entered email id already exists in another record");
				}
				else {
					throw new DuplicateIdException("Updation is failed...Entered phone number already exists in another record");
				}
			}
		return result;
	
	}

	@Override
	public String deleteCandidate(Long id) {
		Session session=sessionFactory.getCurrentSession();
		String result=null;
		Candidate deleteObject=null;
		 deleteObject=session.get(Candidate.class, id);
		if(deleteObject!=null) {
			session.delete(deleteObject);
			session.flush();
			result="Deletion is successful for id: "+id;
		}
		else {
			throw new IdNotFoundException("Deletion is failed...Entered Id doesn't exists");
		}
		return result;
	
	}

	@Override
	public Candidate getCandidateByPhoneNumber(String phoneNumber) {
		Session session=sessionFactory.getCurrentSession();
		Query<?> query=session.createQuery("select c from Candidate c where c.phoneNumber=?1").setParameter(1,phoneNumber);
		return (query.getResultList().isEmpty()?null:(Candidate) query.getResultList().get(0));
	}

	@Override
	public List<Candidate> getCandidateByFirstName(String fname) {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Candidate> resultList=session.createQuery("select c from Candidate c where c.firstName=?1").setParameter(1,fname).getResultList();
		return (resultList.isEmpty()?null:resultList);
	}
	
	@Override
	public List<Candidate> getCandidateByLastName(String lname) {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Candidate> resultList=session.createQuery("select c from Candidate c where c.lastName=?1").setParameter(1,lname).getResultList();
		return (resultList.isEmpty()?null:resultList);
	}

	@Override
	public List<Candidate> getCandidateByExperience(Integer exp) {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Candidate> resultList=session.createQuery("select c from Candidate c where c.experience=?1").setParameter(1,exp).getResultList();
		return (resultList.isEmpty()?null:resultList);
	}

	@Override
	public List<Candidate> getCandidateByRole(String role) {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Candidate> resultList=session.createQuery("select c from Candidate c where c.jobRole=?1").setParameter(1,role).getResultList();
		return (resultList.isEmpty()?null:resultList);
	}

	@Override
	public Candidate getCandidateByEmailId(String email) {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Candidate> resultList=session.createQuery("select c from Candidate c where c.emailId=?1").setParameter(1,email).getResultList();
		return (resultList.isEmpty()?null:resultList.get(0));
	}

	@Override
	public Candidate getCandidateById(Long id) {
		Session session=sessionFactory.getCurrentSession();
		return session.get(Candidate.class,id);
	}

	@Override
	public List<Candidate> getAllCandidate() {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Candidate> query=session.createQuery("select c from Candidate c");
		return (query.getResultList().isEmpty()?null:query.getResultList());
	}

}
