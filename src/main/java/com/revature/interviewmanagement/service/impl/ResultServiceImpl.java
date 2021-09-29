package com.revature.interviewmanagement.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.dao.ResultDao;
import com.revature.interviewmanagement.entity.Result;
import com.revature.interviewmanagement.exception.BussinessLogicException;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.model.ResultDto;
import com.revature.interviewmanagement.service.ResultService;
import com.revature.interviewmanagement.utils.ResultMailSenderUtil;
import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

@Service
public class ResultServiceImpl implements ResultService {
	
	private static final Logger logger=LogManager.getLogger(ResultServiceImpl.class);
	
	@Autowired
	private ResultDao resultDao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public String deleteResult(Long id) {
		logger.info("entering deleteResult method");
		try {
			if(resultDao.getResultById(id)!=null) {
				return resultDao.deleteResult(id);
			}else {
				throw new BussinessLogicException("Result "+ID_NOT_FOUND);
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateResult(ResultDto result) {
		logger.info("entering updateResult method");
		try {
			if(resultDao.getResultById(result.getId())!=null) {
				return resultDao.updateResult(result);
			}else {
				throw new BussinessLogicException("Result "+ID_NOT_FOUND);
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addResult(Long interviewId, ResultDto result) {
		logger.info("entering addResult method");
		try {
			if(resultDao.getResultByInterviewId(interviewId)==null) {
				return resultDao.addResult(interviewId,result);
			}else {
				throw new BussinessLogicException("Interview "+ID_NOT_FOUND);
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Result> getResultByCandidateId(Long canId) {
		logger.info("entering getResultByCandidateId method");
		try {
			return resultDao.getResultByCandidateId(canId);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Result> getResultByEmployeeId(Long empId) {
		logger.info("entering getAllCandidate method");
		try {
			return resultDao.getResultByEmployeeId(empId);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Result getResultByInterviewId(Long interviewId) {
		logger.info("entering getResultByInterviewId method");
		try {
			return resultDao.getResultByInterviewId(interviewId);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Result getResultById(Long id) {
		logger.info("entering getResultById method");
		try {
			return resultDao.getResultById(id);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Result> getAllResult() {
		logger.info("entering getAllResult method");
		try {
			return resultDao.getAllResult();
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
