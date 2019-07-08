package com.accolite.internal.project.exemployeeportal.dtomodel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TicketStatus")
public class TicketStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ;
	private String status_name;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
}
