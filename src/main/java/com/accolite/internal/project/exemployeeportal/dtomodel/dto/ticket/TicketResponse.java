package com.accolite.internal.project.exemployeeportal.dtomodel.dto.ticket;

import java.util.List;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.message.Response;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ChatRecord;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.Ticket;

public class TicketResponse extends Response {
	
		private List<Ticket> TicketRecords;

		public List<Ticket> getTicketRecords() {
			return TicketRecords;
		}

		public void setTicketRecords(List<Ticket> ticketRecords) {
			this.TicketRecords = ticketRecords;
		}

	}
