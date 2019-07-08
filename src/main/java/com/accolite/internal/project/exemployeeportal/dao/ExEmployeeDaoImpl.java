package com.accolite.internal.project.exemployeeportal.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.EmployeeResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.ExEmployeeRepository;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.IExEmployeeDao;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.LoginRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;
import com.accolite.internal.project.exemployeeportal.util.GenericUtil;
import com.accolite.internal.project.exemployeeportal.util.ResponseUtil;

@Service
public class ExEmployeeDaoImpl implements IExEmployeeDao {

	@Autowired
	ExEmployeeRepository exEmployeeRepositoryImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public EmployeeResponse getEmployeeDetailsByEmail(String email) {
		EmployeeResponse response = new EmployeeResponse();
		GenericUtilResponse genericUtilResponse = GenericUtil.isValidEmail(email);

		if (!genericUtilResponse.isValid()) {
			ResponseUtil.error(response, genericUtilResponse.getMessage());
			return response;
		}
		ExEmployee exEmployee = exEmployeeRepositoryImpl.findByEmail(email);
		if (null == exEmployee) {
			if (logger.isInfoEnabled()) {
				logger.info("Ex Employee with email: " + email + "does not exist");
			}
			ResponseUtil.error(response, "Ex Employee with email: " + email + "does not exist");
			return response;
		}
		response.convertExEmployeeToResponse(exEmployee, response);
		ResponseUtil.success(response);
		return response;
	}

	@Override
	public GenericUtilResponse updateExEmployeeDetails(ExEmployee employee) {

		ExEmployee exEmployee = exEmployeeRepositoryImpl.findById(employee.getId());
		if (null == exEmployee) {
			GenericUtilResponse response = new GenericUtilResponse(false, "Ex Employee does not exist");
			if (logger.isInfoEnabled()) {
				logger.info("Ex Employee with id: " + employee.getId() + "does not exist");
			}
			ResponseUtil.error(response, "Ex Employee with email: " + employee.getId() + "does not exist");
			return response;
		}
		if (!(exEmployee.getEmail().equals(GenericUtil.getCurrentExEmployeeUser()))) {
			GenericUtilResponse response = new GenericUtilResponse(false, "Update can happen only for yourself");
			if (logger.isInfoEnabled()) {
				logger.info("Updating Other User Information with " + employee.getId() + "is not allowed");
			}
			ResponseUtil.error(response, "Updating Other User Information with " + employee.getId() + "is not allowed");
			return response;
		}
		exEmployee.setEmail(employee.getEmail());
		exEmployee.setFirstName(employee.getFirstName());
		exEmployee.setLastName(employee.getLastName());
		exEmployee.setPhoneNumber(employee.getPhoneNumber());
		exEmployeeRepositoryImpl.save(exEmployee);

		GenericUtilResponse response = new GenericUtilResponse(true, "User Details updated");
		return response;
	}

	@Override
	public GenericUtilResponse updateExEmployeePassword(LoginRequest login) {
		ExEmployee exEmployee = exEmployeeRepositoryImpl.findByEmail(login.getEmail());
		if (null == exEmployee) {
			GenericUtilResponse response = new GenericUtilResponse(false, "Ex Employee does not exist");
			if (logger.isInfoEnabled()) {
				logger.info("Ex Employee with email: " + login.getEmail() + "does not exist");
			}
			ResponseUtil.error(response, "Ex Employee with email: " + login.getEmail() + "does not exist");
			return response;
		}
		exEmployee.setPassword(passwordEncoder.encode(login.getPassword()));
		exEmployeeRepositoryImpl.save(exEmployee);
		GenericUtilResponse response = new GenericUtilResponse(true, "Ex Employee Password updated");
		return response;
	}

	@Override
	public String getEmpName(long eid) {
		ExEmployee exEmployee = exEmployeeRepositoryImpl.findById(eid);
		if (null == exEmployee) {
			if (logger.isInfoEnabled()) {
				logger.info("Ex Employee with id: " + eid + "does not exist");
			}
			return null;
		}
		if (null == exEmployee.getFirstName() && null == exEmployee.getLastName())
			return null;
		if (null == exEmployee.getFirstName())
			return exEmployee.getLastName();
		if (null == exEmployee.getLastName())
			return exEmployee.getFirstName();
		return exEmployee.getFirstName() + " " + exEmployee.getLastName();
	}

	@Override
	public String getSummary(int ticketId) {
		// TODO Auto-generated method stub
		return null;
	}

}
