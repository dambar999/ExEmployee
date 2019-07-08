package com.accolite.internal.project.exemployeeportal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket.ITicketDao;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket.ITicketService;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket.TicketRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket.TicketResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.Response;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.Ticket;
import com.accolite.internal.project.exemployeeportal.util.ResponseUtil;


@Service
public class TicketServiceImpl  implements ITicketService{

	@Autowired
	ITicketDao ticketDaoImpl;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public GenericUtilResponse createTicket(TicketRequest ticketrequest) {
				
		GenericUtilResponse response = new GenericUtilResponse();
		Ticket ticket = new Ticket();
		boolean isRequestFine = ticketrequest.convertRequestToTicketRecord(ticket, ticketrequest);
		if (!isRequestFine) {
			if (logger.isInfoEnabled()) {
				logger.info("Attention request failed to update Ticket record");
			}
			ResponseUtil.error(response, "Request does not contain all parameters");
			return response;

		}
         		
		return  ticketDaoImpl.createTicket(ticket);
		

	}

	public TicketResponse getTicketByExEmployeeId(long id) {
		
           return  ticketDaoImpl.getTicketByExEmployeeId(id);
		
		}

	public Response closeTicket(long ticketId) {
		
			return ticketDaoImpl.closeTicket(ticketId);
			
	}

	public Response reopenTicket(long ticketId) {
		
		return ticketDaoImpl.reopenTicket(ticketId);
	}
	
	
	public String getSummary(long tid) {
		return ticketDaoImpl.getSummary(tid);
	}
	
	public GenericUtilResponse updateTicket(TicketRequest ticketrequest) {
		GenericUtilResponse response = new GenericUtilResponse();
		Ticket ticket = new Ticket();
		boolean isRequestFine = ticketrequest.convertRequestToTicketRecord(ticket, ticketrequest);
		if (!isRequestFine) {
			if (logger.isInfoEnabled()) {
				logger.info("Attention request failed to update Ticket record");
			}
			ResponseUtil.error(response, "Request does not contain all parameters");
			return response;

		}
          
		
		return  ticketDaoImpl.updateTicket(ticket);
		 
	
	}

	
	public String getEmployeeIdByTicketId(long ticketId) {
		return ticketDaoImpl.getEmployeeIdByTicketId(ticketId);
	}
	
}
