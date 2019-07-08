 package com.accolite.internal.project.exemployeeportal.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.ExEmployeeRepository;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket.ITicketDao;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket.TicketResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.Response;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.Ticket;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.User;
import com.accolite.internal.project.exemployeeportal.repository.TicketRepository;
import com.accolite.internal.project.exemployeeportal.util.ResponseUtil;



@Service
public class TicketDaoImpl implements ITicketDao {
	
	
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	ExEmployeeRepository exEmployeeRepositoryImpl;

	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public GenericUtilResponse createTicket(Ticket ticket) {
		ExEmployee exEmployee = exEmployeeRepositoryImpl.findById(ticket.getRequesttee_user_id());
		if (null == exEmployee) {
			GenericUtilResponse response = new GenericUtilResponse(false, "Ex Employee does not exist");
			if (logger.isInfoEnabled()) {
				logger.info("Ex Employee with id: " + ticket.getRequesttee_user_id() + "does not exist");
			}
			
			return response;
		}
		ticketRepository.save(ticket);
		GenericUtilResponse response = new GenericUtilResponse(true, "Ticket Details created");
		ResponseUtil.success(response);
		return response;
			
	}

	@Override
	public TicketResponse getTicketByExEmployeeId(long id) {
		// TODO Auto-generated method stub
		
		TicketResponse response = new TicketResponse();
		List<Ticket>tickets= ticketRepository.findTicketByExEmployeeId(id);
		if (null == tickets || tickets.size() == 0) {
			if (logger.isInfoEnabled()) {
				logger.info("No Records found with ticket Id :  " + id);
			}
			ResponseUtil.error(response, "No Records found with ticket Id :  " +id);
			return response;
		}
		response.setTicketRecords(tickets);
		ResponseUtil.success(response);
		return response;
	}

	@Override
	public Response closeTicket(long ticketId) {
	 if(ticketRepository.closeTicket(ticketId) == 1)
			return new Response(1,"ticket closed");
		return new Response(0,"ticket close falied");
	}

	@Override
	public Response reopenTicket(long ticketId) {
		if(ticketRepository.reopenTicket(ticketId) == 1)
			return new Response(1,"ticket reopened");
		return new Response(0,"ticket reopen falied");
	}

	@Override
	public String getSummary(long tid) {
		// TODO Auto-generated method stub
		return ticketRepository.getSummary(tid);
	}

	@Override
	public GenericUtilResponse updateTicket(Ticket ticket) {
		if(ticketRepository.updateTicket(ticket.getSummary(), ticket.getId())>=1) {
		GenericUtilResponse response = new GenericUtilResponse(true, "Ticket Details updated");
		ResponseUtil.success(response);
		return response;
		}
		else {
			GenericUtilResponse response = new GenericUtilResponse(true, "Ticket Details could Not be updated");
			ResponseUtil.fail(response);
			return response;
		}
	}

	@Override
	public String getEmployeeIdByTicketId(long ticketId) {
		return ticketRepository.getEmployeeIdByTicketId(ticketId);
	}

}
