package com.revature.interviewmanagement.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.dao.CandidateDao;
import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.exception.BussinessLogicException;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.service.CandidateService;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Service
public class CandidateServiceImpl implements CandidateService {
	
	private static final Logger logger=LogManager.getLogger(CandidateServiceImpl.class);
	
	@Autowired
	private CandidateDao candidateDao;
	
	@Override
	public List<Candidate> getAllCandidate(){
		logger.info("entering getAllCandidate method");
		try {
			return candidateDao.getAllCandidate();
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Candidate getCandidateById(Long id) {
		logger.info("entering getCandidateById method");
		try {
			return candidateDao.getCandidateById(id);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Candidate> getCandidateByEmailId(CandidateDto candidateDto) {
		logger.info("entering getCandidateByEmailId method");
		try {
			return candidateDao.getCandidateByEmailId(candidateDto.getEmailId());
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Candidate> getCandidateByRole(String role) {
		logger.info("entering getCandidateByRole method");
		try {
			return candidateDao.getCandidateByRole(role) ;
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Candidate> getCandidateByExperience(Integer exp) {
		logger.info("entering getCandidateByExperience method");
		try {
			return candidateDao.getCandidateByExperience(exp);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Candidate> getCandidateByName(String name) {
		logger.info("entering getCandidateByName method");
		try {
			return candidateDao.getCandidateByName(name) ;
		}catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Candidate> getCandidateByPhoneNumber(CandidateDto candidateDto) {
		logger.info("entering getCandidateByPhoneNumber method");
		try {
			return candidateDao.getCandidateByPhoneNumber(candidateDto.getPhoneNumber());
		}catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String deleteCandidate(Long id) {
		logger.info("entering deleteCandidate method");
		try {
			if(candidateDao.getCandidateById(id)!=null) {
				return candidateDao.deleteCandidate(id);
			}else {
				throw new BussinessLogicException("Candidate "+ID_NOT_FOUND);
			}
			
		}catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateCandidate(CandidateDto candidateDto) {
		logger.info("entering updateCandidate method");
		try {
			if(candidateDao.getCandidateById(candidateDto.getId())!=null) {
				return candidateDao.updateCandidate(candidateDto);
			}else {
				throw new BussinessLogicException("Candidate "+ID_NOT_FOUND);
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addCandidate(CandidateDto candidateDto) {
		logger.info("entering addCandidate method");
		try {
			return candidateDao.addCandidate(candidateDto);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}


}
