package com.revature.interviewmanagement.dao.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.interviewmanagement.dao.CandidateDao;
import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;


@Repository
@Transactional
public class CandidateDaoImpl implements CandidateDao{
	
	@Autowired
	private SessionFactory sessionFactory;
//	CandidateDaoImpl(SessionFactory sessionFactory){
//		this.sessionFactory=sessionFactory;
//	}
	
	@Override
	public String addCandidate(Candidate candidate) {
		Session session=sessionFactory.getCurrentSession();
		
		Long id=null;
		try {
			
			Query emailQuery = session.getNamedQuery("callCandidateByEmailProcedure")
				    .setParameter("email",candidate.getEmailId());
			Query phoneQuery = session.getNamedQuery("callCandidateByPhoneProcedure")
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
			
			
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		return (id!=null)?"Candidate details inserted with id: "+id:"Couldn't create candidate...Error occured while inserting";
	}

	@Override
	public String updateCandidate(Long id,Candidate candidate) {
		Session session=sessionFactory.getCurrentSession();
		boolean check=false;
		String result=null;
		try {
			session.load(Candidate.class,id);
		} 
		catch (Exception e1) {
			result="updation is failed...entered id doesn't exist";
			check=true;
			
		}
			
		if(!check) {
			Query emailQuery = session.getNamedQuery("callCandidateByEmailUpdateProcedure")
				    .setParameter("email",candidate.getEmailId())
				    .setParameter("id",id);
			Query phoneQuery = session.getNamedQuery("callCandidateByPhoneUpdateProcedure")
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
			try {
				 deleteObject=session.get(Candidate.class, id);
			}
			catch (Exception e1) {
				result="Deletion is failed...Entered Id doesn't exists2";
				//e1.printStackTrace();
			}
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
		Query<Candidate> query=session.createQuery("select c from Candidate c where c.phoneNumber=?1").setParameter(1,phoneNumber);
		return (query.getResultList().isEmpty()?null:query.getResultList().get(0));
	}

	@Override
	public List<Candidate> getCandidateByName(String name) {
		Session session=sessionFactory.getCurrentSession();
		Query<Candidate> query=session.createQuery("select c from Candidate c where c.name=?1").setParameter(1,name);
		return (query.getResultList().isEmpty()?null:query.getResultList());
	}

	@Override
	public List<Candidate> getCandidateByExperience(Integer exp) {
		Session session=sessionFactory.getCurrentSession();
		Query<Candidate> query=session.createQuery("select c from Candidate c where c.experience=?1").setParameter(1,exp);
		return (query.getResultList().isEmpty()?null:query.getResultList());
	}

	@Override
	public List<Candidate> getCandidateByRole(String role) {
		Session session=sessionFactory.getCurrentSession();
		Query<Candidate> query=session.createQuery("select c from Candidate c where c.jobRole=?1").setParameter(1,role);
		return (query.getResultList().isEmpty()?null:query.getResultList());
	}

	@Override
	public Candidate getCandidateByEmailId(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query<Candidate> query=session.createQuery("select c from Candidate c where c.emailId=?1").setParameter(1,email);
		return (query.getResultList().isEmpty()?null:query.getResultList().get(0));
	}

	@Override
	public Candidate getCandidateById(Long id) {
		Session session=sessionFactory.getCurrentSession();
		return session.get(Candidate.class,id);
	}

	@Override
	public List<Candidate> getAllCandidate() {
		Session session=sessionFactory.getCurrentSession();
		Query<Candidate> query=session.createQuery("select c from Candidate c");
		return (query.getResultList().isEmpty()?null:query.getResultList());
	}

}
