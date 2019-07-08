package com.accolite.internal.project.exemployeeportal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.EmployeeRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.EmployeeResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.IExEmployeeDao;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.IExEmployeeService;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.LoginRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;
import com.accolite.internal.project.exemployeeportal.util.GenericUtil;
import com.accolite.internal.project.exemployeeportal.util.ResponseUtil;

@Service
public class ExEmployeeServiceImpl implements IExEmployeeService{

	@Autowired
	IExEmployeeDao exEmployeeDaoImpl;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public EmployeeResponse getEmployeeDetailsByEmail(String email) {
		EmployeeResponse response = new EmployeeResponse();
		if(null == email || email.isEmpty())
		{
			if(logger.isInfoEnabled()) {
				logger.info("email is empty");
			}
			ResponseUtil.error(response, "Email is either null or Empty");
			return response;
		}
		return exEmployeeDaoImpl.getEmployeeDetailsByEmail(email);
	}

	@Override
	public GenericUtilResponse updateExEmployeeDetails(EmployeeRequest request) {
		GenericUtilResponse response = new GenericUtilResponse();
		ExEmployee exEmployee = new ExEmployee();
		boolean isRequestFine = request.convertRequestToExEmployee(exEmployee, request);
		if(!isRequestFine)
		{
			if(logger.isInfoEnabled()) {
				logger.info("Attention request failed to update exemployee");
			}
			ResponseUtil.error(response, "Request does not contain all parameters");
			return response;

		}
		return exEmployeeDaoImpl.updateExEmployeeDetails(exEmployee); 
	}

	@Override
	public GenericUtilResponse updateExEmployeePassword(LoginRequest login) {
		GenericUtilResponse response = new GenericUtilResponse();
		response = GenericUtil.isValidEmail(login.getEmail());
		
		if(!response.isValid()) {
			if(logger.isInfoEnabled()) {
				logger.info(response.getMessage());
			}
			ResponseUtil.error(response, response.getMessage());
			return response;
		}	
		
		response = GenericUtil.validatePassword(login.getPassword());
		if(!response.isValid()) {
			if(logger.isInfoEnabled()) {
				logger.info(response.getMessage());
			}
			ResponseUtil.error(response, response.getMessage());
			return response;
		}	
		return exEmployeeDaoImpl.updateExEmployeePassword(login);
	}

	@Override
	public String getEmpName(long eid) {
		if(eid <= 0)
			return null;
		return exEmployeeDaoImpl.getEmpName(eid);
	}

	@Override
	public String getSummary(int ticketId) {
		if(ticketId <= 0)
			return null;
		return exEmployeeDaoImpl.getSummary(ticketId);
	}

}
