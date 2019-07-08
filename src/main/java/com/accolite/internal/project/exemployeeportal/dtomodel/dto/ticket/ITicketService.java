package com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket.TicketRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ChatRecord;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.Response;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.Ticket;
import com.accolite.internal.project.exemployeeportal.repository.TicketRepository;
import com.accolite.internal.project.exemployeeportal.util.ResponseUtil;



public interface ITicketService {
	
	public GenericUtilResponse createTicket(TicketRequest ticketrequest) ;

	public TicketResponse getTicketByExEmployeeId(long id) ;

	public Response closeTicket(long ticketId) ;
		
	public Response reopenTicket(long ticketId) ;
	
	public String getSummary(long tid) ;
	
	public GenericUtilResponse updateTicket(TicketRequest ticketrequest) ;

	public String getEmployeeIdByTicketId(long ticketId) ;
	
}
