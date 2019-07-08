package com.accolite.internal.project.exemployeeportal.dtomodel.model;

import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Assignee {
	
	@Id
	String id;
	
	String firstName;
	String lastName;
	String roleName;
	@OneToMany
	String ticketId;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
