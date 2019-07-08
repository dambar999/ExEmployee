package com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.message.Request;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ChatRecord;

public class ChatRequest extends Request {
	
	private String message;
	private long ticketId;
	private long employeeId;
	private long supportId;
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public long getTicketId() {
		return ticketId;
	}


	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}


	public long getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}


	public long getSupportId() {
		return supportId;
	}


	public void setSupportId(long supportId) {
		this.supportId = supportId;
	}


	public boolean convertRequestToChatRecord(ChatRecord chat, ChatRequest request) {
		
		if(null==request.getMessage() || request.getMessage().isEmpty() ||
			request.getEmployeeId()<=0 || request.getSupportId()<=0 )
		{
			return false;
		}
		
		chat.setEmployeeId(request.getEmployeeId());
		chat.setMessage(request.getMessage());
		chat.setSupportId(request.getSupportId());
		chat.setTicketId(request.getTicketId());
		
		return true;
	}

}
