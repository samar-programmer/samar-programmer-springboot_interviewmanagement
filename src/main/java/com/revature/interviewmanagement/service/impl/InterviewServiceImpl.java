package com.revature.interviewmanagement.service.impl;

import static com.revature.interviewmanagement.utils.InterviewManagementConstantsUtil.*;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.revature.interviewmanagement.dao.CandidateDao;
import com.revature.interviewmanagement.dao.EmployeeDao;
import com.revature.interviewmanagement.dao.InterviewDao;
import com.revature.interviewmanagement.entity.Candidate;
import com.revature.interviewmanagement.entity.Employee;
import com.revature.interviewmanagement.entity.Interview;
import com.revature.interviewmanagement.exception.BussinessLogicException;
import com.revature.interviewmanagement.exception.DatabaseException;
import com.revature.interviewmanagement.exception.IdNotFoundException;
import com.revature.interviewmanagement.exception.NoRecordFoundException;
import com.revature.interviewmanagement.model.CandidateDto;
import com.revature.interviewmanagement.model.EmployeeDto;
import com.revature.interviewmanagement.model.InterviewDto;
import com.revature.interviewmanagement.service.InterviewService;
import com.revature.interviewmanagement.util.mapper.InterviewMapper;
import com.revature.interviewmanagement.utils.ScheduledInterviewMailUtil;

@Service
public class InterviewServiceImpl implements InterviewService {

	private static final Logger logger = LogManager.getLogger(InterviewServiceImpl.class);

	@Autowired
	private InterviewDao interviewDao;

	@Autowired
	private CandidateDao candidateDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private JavaMailSender javaMailSender;
	
	private Candidate candidate=null;
	private Employee employee=null;

	@Override
	public List<Interview> getAllInterview() {
		logger.info("entering getAllInterview method");
		try {
			List<Interview> interviews = interviewDao.getAllInterview();
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<?> getAllInterviewType() {
		logger.info("entering getAllInterviewType method");
		try {
			List<?> interviewType = interviewDao.getAllInterviewType();
			if (CollectionUtils.isEmpty(interviewType)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviewType;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public Interview getInterviewById(Long id) {
		logger.info("entering getInterviewById method");
		try {
			Interview interview = interviewDao.getInterviewById(id);
			if (interview == null) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interview;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByScheduledDate(LocalDate scheduledDate) {
		logger.info("entering getInterviewByScheduledDate method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByScheduledDate(scheduledDate);
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateId(Long canId) {
		logger.info("entering getInterviewByCandidateId method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByCandidateId(canId);
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateName(String name) {
		logger.info("entering getInterviewByCandidateName method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByCandidateName(name);
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByCandidatePhone(CandidateDto candidateDto) {
		logger.info("entering getInterviewByCandidatePhone method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByCandidatePhone(candidateDto.getPhoneNumber());
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateEmailId(CandidateDto candidateDto) {
		logger.info("entering getInterviewByCandidateEmailId method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByCandidateEmailId(candidateDto.getEmailId());
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateRole(String role) {
		logger.info("entering getInterviewByCandidateRole method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByCandidateRole(role);
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByCandidateExperience(String exp) {
		logger.info("entering getInterviewByCandidateExperience method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByCandidateExperience(exp);
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeId(Long id) {
		logger.info("entering getInterviewByEmployeeId method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByEmployeeId(id);
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeDesignation(String designation) {
		logger.info("entering getInterviewByEmployeeDesignation method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByEmployeeDesignation(designation);
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeName(String name) {
		logger.info("entering getInterviewByEmployeeName method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByEmployeeName(name);
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeePhone(EmployeeDto employeeDto) {
		logger.info("entering getInterviewByEmployeePhone method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByEmployeePhone(employeeDto.getPhoneNumber());
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeEmailId(EmployeeDto employeeDto) {
		logger.info("entering getInterviewByEmployeeEmailId method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByEmployeeEmailId(employeeDto.getEmailId());
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByType(String type) {
		logger.info("entering getInterviewByType method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByType(type);
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Interview> getInterviewByEmployeeStatus(String status) {
		logger.info("entering getInterviewByEmployeeStatus method");
		try {
			List<Interview> interviews = interviewDao.getInterviewByEmployeeStatus(status);
			if (CollectionUtils.isEmpty(interviews)) {
				throw new NoRecordFoundException(NO_DATA_FOUND);
			} else {
				return interviews;
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String deleteInterview(Long id) {
		logger.info("entering deleteInterview method");
		try {
			if (interviewDao.getInterviewById(id) != null) {
				return interviewDao.deleteInterview(id);
			} else {
				throw new IdNotFoundException("Interview " + ID_NOT_FOUND);
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateInterview(InterviewDto interviewDto) {
		logger.info("entering updateInterview method");
		try {
			//interview status will be changed to Finished only after we add result for the interview
			//So while updating the interview itself we cannot make status as finished
			if(("Finished").equals(interviewDto.getStatus()) || ("Live").equals(interviewDto.getStatus())) {
				throw new BussinessLogicException(INVALID_INTERVIEW_UPDATE);
			}
			
			
			//checks whether interview exists for given id
			Interview interview=interviewDao.getInterviewById(interviewDto.getId());
			if (interview != null) {
				// checks whether updated candidate and employee exists in the database
				candidate=candidateDao.getCandidateById(interviewDto.getCandidate().getId());
				employee=employeeDao.getEmployeeById(interviewDto.getEmployee().getId());
				if (candidate != null && employee != null) {
					
					//checks the employee status is available or left
					if(("Left").equals(employee.getStatus())) {
						throw new BussinessLogicException(EMPLOYEE_LEFT);
					}
					//isCandidateHasLiveInterview method checks the candidate has a live/Rescheduled interview and returns true if candidate has
					//a live/Rescheduled interview. If yes, checks id of the interview is same as id of the interview to be updated.
					//if they are not same, throws an exception
					Long interviewId=interviewDao.isCandidateHasLiveInterviewForUpdate(interviewDto.getCandidate().getId());
					//if interviewId equals null then candidate did not match with any interview
					if(interviewId !=null && !interviewDto.getId().equals(interviewId)) {
						throw new BussinessLogicException(CANDIDATE_ALREADY_HAS_LIVE_INTERVIEW);
					}
					else if(("Finished").equals(interview.getStatus())) {
						throw new BussinessLogicException(FINISHED_INTERVIEW);
					} else {
						interview = InterviewMapper.interviewEntityMapper(interviewDto);
						return interviewDao.updateInterview(interview);
					}
					
				} else {
					throw new IdNotFoundException(CANDIDATE + ID_NOT_FOUND + " or "+EMPLOYEE + ID_NOT_FOUND);
				}

			} else {
				throw new IdNotFoundException("Interview " + ID_NOT_FOUND);
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addInterview(InterviewDto interviewDto, Long canId, Long empId) {
		logger.info("entering addInterview method");
		try {
			candidate=candidateDao.getCandidateById(canId);
			employee=employeeDao.getEmployeeById(empId);
			// checks whether candidate and employee exists in the database
			if (candidate != null && employee != null) {
			
				//checks the employee status is available or left
				if(("Left").equals(employee.getStatus())) {
					throw new BussinessLogicException(EMPLOYEE_LEFT);
				}
				//isCandidateHasLiveInterview method checks the candidate has a live/Rescheduled interview and returns true if candidate has
				//a live/Rescheduled interview
				else if(interviewDao.isCandidateHasLiveInterview(canId)) {
					throw new BussinessLogicException(CANDIDATE_ALREADY_HAS_LIVE_INTERVIEW);
				} else {
					Interview interview = InterviewMapper.interviewEntityMapper(interviewDto);
					return interviewDao.addInterview(interview, canId, empId);
				}
				
			} else {
				throw new IdNotFoundException(CANDIDATE + ID_NOT_FOUND + " or "+EMPLOYEE + ID_NOT_FOUND);
			}

		} catch (DatabaseException e) {
			throw new BussinessLogicException(e.getMessage());
		}
	}

	@Override
	public String sendScheduledInterviewMail(Long canId, Long empId, InterviewDto interviewDto) {
		logger.info("entering sendScheduledInterviewMail method");
		
		//checking whether candidate and employee exists in the database
		candidate=candidateDao.getCandidateById(canId);
		if(candidate==null) {
			throw new IdNotFoundException(CANDIDATE+ID_NOT_FOUND);
		}
		employee=employeeDao.getEmployeeById(empId);
		if(employee==null) {
			throw new IdNotFoundException(EMPLOYEE+ID_NOT_FOUND);
		}
		try {
			return ScheduledInterviewMailUtil.sendScheduledInterviewMail(javaMailSender,candidate,employee, interviewDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BussinessLogicException(ERROR_IN_SENDING_MAIL);
		}
	}

	@Override
	public String sendRescheduledInterviewMail(Long canId, Long empId, InterviewDto interviewDto) {
		logger.info("entering sendRescheduledInterviewMail method");
		
		//checking whether candidate and employee exists in the database
		candidate=candidateDao.getCandidateById(canId);
		if(candidate==null) {
			throw new IdNotFoundException(CANDIDATE+ID_NOT_FOUND);
		}
		employee=employeeDao.getEmployeeById(empId);
		if(employee==null) {
			throw new IdNotFoundException(EMPLOYEE+ID_NOT_FOUND);
		}
		
		try {
			return ScheduledInterviewMailUtil.sendReScheduledInterviewMail(javaMailSender,candidate, employee, interviewDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BussinessLogicException(ERROR_IN_SENDING_MAIL);
		}
	}

}
