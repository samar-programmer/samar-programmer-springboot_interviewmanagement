package com.revature.interviewmanagement.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.revature.interviewmanagement.dao.CandidateDao;
import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.exception.BussinessLogicException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.exception.NoRecordFoundException;
import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.service.CandidateService;
import com.revature.interviewmanagement.util.mapper.CandidateMapper;

import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Service
public class CandidateServiceImpl implements CandidateService {

	private static final Logger logger = LogManager.getLogger(CandidateServiceImpl.class);

	@Autowired
	private CandidateDao candidateDao;

	@Override
	public List<Candidate> getAllCandidate() {
		logger.info("entering getAllCandidate method");

		List<Candidate> candidates = candidateDao.getAllCandidate();
		if (CollectionUtils.isEmpty(candidates)) {
			throw new NoRecordFoundException(NO_DATA_FOUND);
		} else {
			return candidates;
		}

	}

	@Override
	public Candidate getCandidateById(Long id) {
		logger.info("entering getCandidateById method");

		Candidate candidate = candidateDao.getCandidateById(id);
		if (candidate != null) {
			return candidate;
		} else {
			throw new NoRecordFoundException(NO_DATA_FOUND);
		}

	}

	@Override
	public List<Candidate> getCandidateByEmailId(CandidateDto candidateDto) {
		logger.info("entering getCandidateByEmailId method");
		
		List<Candidate> candidates = candidateDao.getCandidateByEmailId(candidateDto.getEmailId());
		if (CollectionUtils.isEmpty(candidates)) {
			throw new NoRecordFoundException(NO_DATA_FOUND);
		} else {
			return candidates;
		}

	}

	@Override
	public List<Candidate> getCandidateByRole(String role) {
		logger.info("entering getCandidateByRole method");

		List<Candidate> candidates = candidateDao.getCandidateByRole(role);
		if (CollectionUtils.isEmpty(candidates)) {
			throw new NoRecordFoundException(NO_DATA_FOUND);
		} else {
			return candidates;
		}

	}

	@Override
	public List<Candidate> getCandidateByExperience(String exp) {
		logger.info("entering getCandidateByExperience method");

		List<Candidate> candidates = candidateDao.getCandidateByExperience(exp);
		if (CollectionUtils.isEmpty(candidates)) {
			throw new NoRecordFoundException(NO_DATA_FOUND);
		} else {
			return candidates;
		}

	}

	@Override
	public List<Candidate> getCandidateByName(String name) {
		logger.info("entering getCandidateByName method");

		List<Candidate> candidates = candidateDao.getCandidateByName(name);
		if (CollectionUtils.isEmpty(candidates)) {
			throw new NoRecordFoundException(NO_DATA_FOUND);
		} else {
			return candidates;
		}

	}

	@Override
	public List<Candidate> getCandidateByPhoneNumber(CandidateDto candidateDto) {
		logger.info("entering getCandidateByPhoneNumber method");

		List<Candidate> candidates = candidateDao.getCandidateByPhoneNumber(candidateDto.getPhoneNumber());
		if (CollectionUtils.isEmpty(candidates)) {
			throw new NoRecordFoundException(NO_DATA_FOUND);
		} else {
			return candidates;
		}

	}

	@Override
	public String deleteCandidate(Long id) {
		logger.info("entering deleteCandidate method");

		if (candidateDao.getCandidateById(id) != null) {
			return candidateDao.deleteCandidate(id);
		} else {
			throw new IdNotFoundException("Candidate " + ID_NOT_FOUND);
		}

	}

	@Override
	public String updateCandidate(CandidateDto candidateDto) {
		logger.info("entering updateCandidate method");
			Candidate candidate=candidateDao.getCandidateById(candidateDto.getId());
		if (candidate != null) {
			if (candidate.getJobRole().equals(candidateDto.getJobRole()) || Boolean.TRUE.equals(validateJobRole(candidateDto))) {
				 candidate = CandidateMapper.candidateEntityMapper(candidateDto);
				return candidateDao.updateCandidate(candidate);
			} else {
				throw new BussinessLogicException(
						"You cannot apply for this role at the moment. You can apply for this role only after 3 months");
			}

		} else {
			throw new IdNotFoundException("Candidate " + ID_NOT_FOUND);
		}

	}

	@Override
	public String addCandidate(CandidateDto candidateDto) {
		logger.info("entering addCandidate method");

		// invokes validateJobRole method which is going to return true if the candidate
		// is able to apply for a specific role
		if (candidateDao.validateJobRole(candidateDto) == null) {
			Candidate candidate = CandidateMapper.candidateEntityMapper(candidateDto);
			return candidateDao.addCandidate(candidate);
		} else {
			throw new BussinessLogicException(
					"You cannot apply for this role at the moment. You can apply for this role only after 3 months");
		}

	}

	@Override
	public List<?> getAllExperience() {
		logger.info("entering getExperience method");

		List<?> experience = candidateDao.getAllExperience();
		if (CollectionUtils.isEmpty(experience)) {
			throw new NoRecordFoundException(NO_DATA_FOUND);
		} else {
			return experience;
		}

	}

	@Override
	public List<?> getAllJobRole() {
		logger.info("entering getAllJobRole method");

		List<?> jobRoles = candidateDao.getAllJobRole();
		if (CollectionUtils.isEmpty(jobRoles)) {
			throw new NoRecordFoundException(NO_DATA_FOUND);
		} else {
			return jobRoles;
		}

	}

	@Override
	public Boolean validateJobRole(CandidateDto candidateDto) {
		logger.info("entering validateJobRole method");

		return candidateDao.validateJobRole(candidateDto) == null;

	}

}
