package com.revature.interviewmanagement.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.revature.interviewmanagement.dao.InterviewDao;
import com.revature.interviewmanagement.dao.ResultDao;
import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.entity.Result;
import com.revature.interviewmanagement.exception.BussinessLogicException;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.exception.DuplicateIdException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.exception.NoRecordFoundException;
import com.revature.interviewmanagement.model.ResultDto;
import com.revature.interviewmanagement.service.ResultService;
import com.revature.interviewmanagement.util.mapper.ResultMapper;
import com.revature.interviewmanagement.utils.ResultMailSenderUtil;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Service
public class ResultServiceImpl implements ResultService {
	
	private static final Logger logger=LogManager.getLogger(ResultServiceImpl.class);
	
	@Autowired
	private ResultDao resultDao;
	
	@Autowired
	private InterviewDao interviewDao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public String deleteResult(Long id) {
		logger.info("entering deleteResult method");
		try {
			if(resultDao.getResultById(id)!=null) {
				return resultDao.deleteResult(id);
			}else {
				throw new IdNotFoundException("Result "+ID_NOT_FOUND);
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateResult(ResultDto resultDto) {
		logger.info("entering updateResult method");
		try {
			//checks whether given result already exists so that we can update it.
			Result result=resultDao.getResultById(resultDto.getId());
			if(result!=null) {
				Interview interview=resultDto.getInterview();
				//checking whether candidate and employee not null
				if(interview.getCandidate()==null) {
					throw new BussinessLogicException(CANDIDATE+NOT_FOUND);
				}else if(interview.getEmployee()==null) {
					throw new BussinessLogicException(EMPLOYEE+NOT_FOUND);
				}
				
				//checks whether previous interview and updated interview remains same
				if(result.getInterview().getId().equals(resultDto.getInterview().getId())) {
					 result=ResultMapper.resultEntityMapper(resultDto);
					return resultDao.updateResult(result);
				}else {
					throw new BussinessLogicException("Conflict while updating the result. Result should not be updated with different interview");
				}
				
			}else {
				throw new IdNotFoundException("Result "+ID_NOT_FOUND);
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addResult(Long interviewId, ResultDto resultDto) {
		logger.info("entering addResult method");
		try {
			if(interviewDao.getInterviewById(interviewId)==null) {
				throw new IdNotFoundException("Interview "+ID_NOT_FOUND);
			}
			else if(resultDao.getResultByInterviewId(interviewId)==null) {
				Result result=ResultMapper.resultEntityMapper(resultDto);
				return resultDao.addResult(interviewId,result);
			}else {
				throw new DuplicateIdException("The Interview already has a result");
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Result> getResultByCandidateId(Long canId) {
		logger.info("entering getResultByCandidateId method");
		try {
			List<Result> results =  resultDao.getResultByCandidateId(canId);
			if (CollectionUtils.isEmpty(results)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return results;
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Result> getResultByEmployeeId(Long empId) {
		logger.info("entering getAllCandidate method");
		try {
			List<Result> results =  resultDao.getResultByEmployeeId(empId);
			if (CollectionUtils.isEmpty(results)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return results;
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Result getResultByInterviewId(Long interviewId) {
		logger.info("entering getResultByInterviewId method");
		try {
			Result result = resultDao.getResultByInterviewId(interviewId);
			if (result==null) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return result;
			}
			 
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Result getResultById(Long id) {
		logger.info("entering getResultById method");
		try {
			Result result = resultDao.getResultById(id);
			if (result==null) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return result;
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Result> getAllResult() {
		logger.info("entering getAllResult method");
		try {
			List<Result> results =  resultDao.getAllResult();
			if (CollectionUtils.isEmpty(results)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return results;
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String sendResultMail(ResultDto resultDto) {
		logger.info("entering sendResultMail method");
		try {
			return ResultMailSenderUtil.sendResultMail(javaMailSender,resultDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BussinessLogicException(ERROR_IN_SENDING_MAIL);
		}
	}

}
