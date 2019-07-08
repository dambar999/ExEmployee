package com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord.ChatRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ChatRecord;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.Ticket;

public class TicketRequest {
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
	private long ticketid;
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
	public long getTicketid() {
		return ticketid;
	}
	public void setTicketid(long ticketid) {
		this.ticketid = ticketid;
	}

	public boolean convertRequestToTicketRecord(Ticket ticket, TicketRequest ticketrequest) {
		
		if(null==ticketrequest.getSummary() || ticketrequest.getSummary().isEmpty() ||
				ticketrequest.getRequesttee_user_id()<=0 || ticketrequest.getAssignee_user_id()<=0 )
		{
			return false;
		}
		
		ticket.setAssignee_user_id(ticketrequest.getAssignee_user_id());
		ticket.setCreationdate(ticketrequest.getCreationdate());
		ticket.setLastupdated(ticketrequest.getLastupdated());
		ticket.setRequesttee_user_id(ticketrequest.getRequesttee_user_id());
		ticket.setSummary(ticketrequest.getSummary());
		ticket.setStatusid(ticketrequest.getStatusid());
		ticket.setId(ticketrequest.getTicketid());
		
		return true;
	}

}
