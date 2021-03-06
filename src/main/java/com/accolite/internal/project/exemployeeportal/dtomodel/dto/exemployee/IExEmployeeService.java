package com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee;

import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;

public interface IExEmployeeService {
	EmployeeResponse getEmployeeDetailsByEmail(String email);
	GenericUtilResponse updateExEmployeeDetails(EmployeeRequest employee);
	GenericUtilResponse updateExEmployeePassword(LoginRequest login);
	String getEmpName(long eid);
	String getSummary(int ticketId);
}
