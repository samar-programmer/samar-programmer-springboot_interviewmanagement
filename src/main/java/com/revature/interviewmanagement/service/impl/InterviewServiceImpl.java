package com.revature.interviewmanagement.service.impl;

import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.revature.interviewmanagement.dao.CandidateDao;
import com.revature.interviewmanagement.dao.EmployeeDao;
import com.revature.interviewmanagement.dao.InterviewDao;
import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.exception.BussinessLogicException;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.model.InterviewDto;
import com.revature.interviewmanagement.service.InterviewService;
import com.revature.interviewmanagement.utils.ScheduledInterviewMailUtil;

@Service
public class InterviewServiceImpl implements InterviewService {

	private static final Logger logger=LogManager.getLogger(InterviewServiceImpl.class);
	
	@Autowired
	private InterviewDao interviewDao;
	
	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public List<Interview> getAllInterview() {
		logger.info("entering getAllInterview method");
		try {
			return interviewDao.getAllInterview();
		}  catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Interview getInterviewById(Long id) {
		logger.info("entering getInterviewById method");
		try {
			return interviewDao.getInterviewById(id);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByScheduledDate(LocalDate scheduledDate) {
		logger.info("entering getInterviewByScheduledDate method");
		try {
			return interviewDao.getInterviewByScheduledDate(scheduledDate);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateId(Long canId) {
		logger.info("entering getInterviewByCandidateId method");
		try {
			return interviewDao.getInterviewByCandidateId(canId);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateName(String name) {
		logger.info("entering getInterviewByCandidateName method");
		try {
			return interviewDao.getInterviewByCandidateName(name);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByCandidatePhone(CandidateDto candidateDto) {
		logger.info("entering getInterviewByCandidatePhone method");
		try {
			return interviewDao.getInterviewByCandidatePhone(candidateDto.getPhoneNumber());
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateEmailId(CandidateDto candidateDto) {
		logger.info("entering getInterviewByCandidateEmailId method");
		try {
			return interviewDao.getInterviewByCandidateEmailId(candidateDto.getEmailId());
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateRole(String role) {
		logger.info("entering getInterviewByCandidateRole method");
		try {
			return interviewDao.getInterviewByCandidateRole(role);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateExperience(Integer exp) {
		logger.info("entering getInterviewByCandidateExperience method");
		try {
			return interviewDao.getInterviewByCandidateExperience(exp);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}
	
	@Override
	public List<Interview> getInterviewByEmpId(Long empId) {
		logger.info("entering getInterviewByEmpId method");
		try {
			return interviewDao.getInterviewByEmpId(empId);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeId(EmployeeDto employeeDto) {
		logger.info("entering getInterviewByEmployeeId method");
		try {
			return interviewDao.getInterviewByEmployeeId(employeeDto.getEmployeeId());
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByDesignationId(EmployeeDto employeeDto) {
		logger.info("entering getInterviewByDesignationId method");
		try {
			return interviewDao.getInterviewByDesignationId(employeeDto.getDesignationId());
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeName(String name) {
		logger.info("entering getInterviewByEmployeeName method");
		try {
			return interviewDao.getInterviewByEmployeeName(name);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeePhone(EmployeeDto employeeDto) {
		logger.info("entering getInterviewByEmployeePhone method");
		try {
			return interviewDao.getInterviewByEmployeePhone(employeeDto.getPhoneNumber());
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeEmailId(EmployeeDto employeeDto) {
		logger.info("entering getInterviewByEmployeeEmailId method");
		try {
			return interviewDao.getInterviewByEmployeeEmailId(employeeDto.getEmailId());
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String deleteInterview(Long id) {
		logger.info("entering deleteInterview method");
		try {
			if(interviewDao.getInterviewById(id)!=null) {
				return interviewDao.deleteInterview(id);
			}else {
				throw new BussinessLogicException("Interview "+ID_NOT_FOUND);
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateInterview(InterviewDto interview) {
		logger.info("entering updateInterview method");
		try {
			if(interviewDao.getInterviewById(interview.getId())!=null) {
				return interviewDao.updateInterview(interview);
			}
			else {
				throw new BussinessLogicException("Interview "+ID_NOT_FOUND);
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addInterview(InterviewDto interview,Long canId,Long empId) {
		logger.info("entering addInterview method");
		try {
			if(candidateDao.getCandidateById(canId)!=null && employeeDao.getEmployeeById(empId)!=null) {
				return interviewDao.addInterview(interview,canId,empId);
			}
			else {
				throw new BussinessLogicException("Candidate "+ID_NOT_FOUND+" or Employee "+ID_NOT_FOUND);
			}
			
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByType(String type) {
		logger.info("entering getInterviewByType method");
		try {
			return interviewDao.getInterviewByType(type);
		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String sendScheduledInterviewMail(Long canId,Long empId,InterviewDto interviewDto) {
		logger.info("entering sendScheduledInterviewMail method");
		try {
			return ScheduledInterviewMailUtil.sendScheduledInterviewMail(javaMailSender,candidateDao.getCandidateById(canId),employeeDao.getEmployeeById(empId),interviewDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BussinessLogicException(ERROR_IN_SENDING_MAIL);
		}
	}

	@Override
	public String sendRescheduledInterviewMail(Long canId,Long empId,InterviewDto interviewDto) {
		logger.info("entering sendRescheduledInterviewMail method");
		try {
			return ScheduledInterviewMailUtil.sendReScheduledInterviewMail(javaMailSender,candidateDao.getCandidateById(canId),employeeDao.getEmployeeById(empId),interviewDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BussinessLogicException(ERROR_IN_SENDING_MAIL);
		}
	}

	

	

}
