package com.accolite.internal.project.exemployeeportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket.ITicketService;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket.TicketRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket.TicketResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.Response;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.Ticket;
import com.accolite.internal.project.exemployeeportal.service.TicketServiceImpl;

import ch.qos.logback.core.net.SyslogOutputStream;

@RequestMapping("/ticket")
@RestController
public class TicketController {
	
	@Autowired
	ITicketService ticketService;
	
	@PostMapping(value="/create")
	public GenericUtilResponse createTicket(@RequestBody TicketRequest ticketrequest) {
		 
	   return ticketService.createTicket(ticketrequest);
  }
	@GetMapping(value="/getall")
	public TicketResponse getTicketByEmployee(@RequestParam("eid") long id){
		
		return ticketService.getTicketByExEmployeeId(id);
	}
	
	@PostMapping(value="/close" ,consumes="application/json")
	public Response closeTicket(@RequestParam("tid") long ticketId) { 
		return ticketService.closeTicket(ticketId);
	}
	
	@PostMapping(value="/reopen", consumes="application/json")
	public Response reopenTicket(@RequestParam("tid") long ticketId) {
		return ticketService.reopenTicket(ticketId);
	}
	
	@GetMapping(value="/summary" )
	public String getSummary(@RequestParam("tid") long ticketId ) {
		return ticketService.getSummary(ticketId);
	}
	
	@PostMapping(value="/updateTicket", consumes="application/json")
	public GenericUtilResponse getTicketUpdate(@RequestBody TicketRequest ticket) {
		return  ticketService.updateTicket(ticket);
		 
	}
	
	@GetMapping(value="/get/empId")
	public String getEmployeeIdByTicketId(@RequestParam("tid") long ticketId)
	{
		return ticketService.getEmployeeIdByTicketId(ticketId);
	}
	
  }

