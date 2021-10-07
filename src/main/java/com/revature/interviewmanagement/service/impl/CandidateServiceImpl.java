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
import com.revature.interviewmanagement.exception.DatabaseException;
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
		try {
			List<Candidate> candidates = candidateDao.getAllCandidate();
			if (CollectionUtils.isEmpty(candidates)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return candidates;
			}
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Candidate getCandidateById(Long id) {
		logger.info("entering getCandidateById method");
		try {
			Candidate candidate = candidateDao.getCandidateById(id);
			if (candidate != null) {
				return candidate;
			} else {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			}
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Candidate> getCandidateByEmailId(CandidateDto candidateDto) {
		logger.info("entering getCandidateByEmailId method");
		try {
			List<Candidate> candidates = candidateDao.getCandidateByEmailId(candidateDto.getEmailId());
			if (CollectionUtils.isEmpty(candidates)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return candidates;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Candidate> getCandidateByRole(String role) {
		logger.info("entering getCandidateByRole method");
		try {
			List<Candidate> candidates = candidateDao.getCandidateByRole(role);
			if (CollectionUtils.isEmpty(candidates)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return candidates;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Candidate> getCandidateByExperience(String exp) {
		logger.info("entering getCandidateByExperience method");
		try {
			List<Candidate> candidates = candidateDao.getCandidateByExperience(exp);
			if (CollectionUtils.isEmpty(candidates)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return candidates;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Candidate> getCandidateByName(String name) {
		logger.info("entering getCandidateByName method");
		try {
			List<Candidate> candidates = candidateDao.getCandidateByName(name);
			if (CollectionUtils.isEmpty(candidates)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return candidates;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Candidate> getCandidateByPhoneNumber(CandidateDto candidateDto) {
		logger.info("entering getCandidateByPhoneNumber method");
		try {
			List<Candidate> candidates = candidateDao.getCandidateByPhoneNumber(candidateDto.getPhoneNumber());
			if (CollectionUtils.isEmpty(candidates)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return candidates;
			}
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String deleteCandidate(Long id) {
		logger.info("entering deleteCandidate method");
		try {
			if (candidateDao.getCandidateById(id) != null) {
				return candidateDao.deleteCandidate(id);
			} else {
				throw new IdNotFoundException("Candidate " + ID_NOT_FOUND);
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateCandidate(CandidateDto candidateDto) {
		logger.info("entering updateCandidate method");
		try {
			if (candidateDao.getCandidateById(candidateDto.getId()) != null) {
				if (Boolean.TRUE.equals(validateJobRole(candidateDto))) {
					Candidate candidate = CandidateMapper.candidateEntityMapper(candidateDto);
					return candidateDao.updateCandidate(candidate);
				} else {
					throw new BussinessLogicException(
							"You cannot apply for this role at the moment. You can apply for this role only after 3 months");
				}

			} else {
				throw new IdNotFoundException("Candidate " + ID_NOT_FOUND);
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addCandidate(CandidateDto candidateDto) {
		logger.info("entering addCandidate method");
		try {
			//invokes validateJobRole method which is going to return true if the candidate is able to apply for a specific role
			if (candidateDao.validateJobRole(candidateDto)==null) {
				Candidate candidate = CandidateMapper.candidateEntityMapper(candidateDto);
				return candidateDao.addCandidate(candidate);
			} else {
				throw new BussinessLogicException(
						"You cannot apply for this role at the moment. You can apply for this role only after 3 months");
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<?> getAllExperience() {
		logger.info("entering getExperience method");
		try {
			List<?> experience = candidateDao.getAllExperience();
			if (CollectionUtils.isEmpty(experience)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return experience;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<?> getAllJobRole() {
		logger.info("entering getAllJobRole method");
		try {
			List<?> jobRoles = candidateDao.getAllJobRole();
			if (CollectionUtils.isEmpty(jobRoles)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return jobRoles;
			}
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Boolean validateJobRole(CandidateDto candidateDto) {
		logger.info("entering validateJobRole method");
		try {
			return candidateDao.validateJobRole(candidateDto) == null;
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

}
