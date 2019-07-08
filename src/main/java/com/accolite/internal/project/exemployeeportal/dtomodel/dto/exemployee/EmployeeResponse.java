package com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.message.Response;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;

public class EmployeeResponse extends Response {
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
	
	public void convertExEmployeeToResponse(ExEmployee employee,EmployeeResponse response)
	{
		response.setEmail(employee.getEmail());
		response.setEmployeeId(employee.getEmployeeId());
		response.setFirstName(employee.getFirstName());
		response.setLastName(employee.getLastName());
		response.setId(employee.getId());
		response.setPhoneNumber(employee.getPhoneNumber());
	}
}
