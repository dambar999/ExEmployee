package com.accolite.internal.project.exemployeeportal.dtomodel.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Ticket")
public class Ticket {

	
	@Column
	private long  assignee_user_id;
	@Column
	private Date creationdate;
	@Column
	private Date lastupdated;
	@Column
	private long requesttee_user_id;
	@Column
	private long statusid;
	@Column
	private String summary;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
	public long getAssignee_user_id() {
		return assignee_user_id;
	}
	public void setAssignee_user_id(long assignee_user_id) {
		this.assignee_user_id = assignee_user_id;
	}
	public Date getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	public Date getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	public long getRequesttee_user_id() {
		return requesttee_user_id;
	}
	public void setRequesttee_user_id(long requesttee_user_id) {
		this.requesttee_user_id = requesttee_user_id;
	}
	public long getStatusid() {
		return statusid;
	}
	public void setStatusid(long statusid) {
		this.statusid = statusid;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	

	
		
}
