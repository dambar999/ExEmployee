package com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee;

import javax.persistence.Column;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.message.Request;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;

public class EmployeeRequest extends Request {
	private long id;
	private String employeeId;
	private String firstName; 
	private String lastName;
	private String email;
	private String phoneNumber;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public boolean convertRequestToExEmployee(ExEmployee exEmployee, EmployeeRequest request)
	{
		if(null==request.getEmail()|| request.getEmail().isEmpty() || 
				null==request.getEmployeeId()|| request.getEmployeeId().isEmpty() ||
				null==request.getFirstName()|| request.getFirstName().isEmpty() ||
				null==request.getLastName()|| request.getLastName().isEmpty() ||
				request.getId()<=0 ||
				null==request.getPhoneNumber()|| request.getPhoneNumber().isEmpty())
		{
			return false;
		}
		exEmployee.setEmail(request.getEmail());
		exEmployee.setEmployeeId(request.getEmployeeId());
		exEmployee.setFirstName(request.getFirstName());
		exEmployee.setLastName(request.getLastName());
		exEmployee.setId(request.getId());
		exEmployee.setPhoneNumber(request.getPhoneNumber());
		
		return true;
	}
	
}
