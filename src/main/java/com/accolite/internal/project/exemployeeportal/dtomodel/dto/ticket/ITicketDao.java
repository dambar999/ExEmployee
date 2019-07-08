package com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket;

import java.util.List;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.Response;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.Ticket;

public interface ITicketDao {

	public GenericUtilResponse createTicket(Ticket ticket) ;

	public TicketResponse getTicketByExEmployeeId(long id) ;

	public Response closeTicket(long ticketId) ;
		
	public Response reopenTicket(long ticketId) ;
	
	public String getSummary(long tid) ;
	
	public GenericUtilResponse updateTicket(Ticket ticket) ;

	public String getEmployeeIdByTicketId(long ticketId) ;

	

}
