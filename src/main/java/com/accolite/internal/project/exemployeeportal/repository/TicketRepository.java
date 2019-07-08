package com.accolite.internal.project.exemployeeportal.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.accolite.internal.project.exemployeeportal.dtomodel.model.Ticket;



public interface TicketRepository  extends JpaRepository<Ticket,Long>{

	@Query("SELECT t FROM Ticket t WHERE t.requesttee_user_id = ?1")
	List<Ticket> findTicketByExEmployeeId(long id);

	@Transactional
	 @Modifying
	@Query("update Ticket set statusid='2' WHERE id = ?1")
	int closeTicket(long tid);
	@Transactional
	  @Modifying
	@Query("update Ticket set statusid='1' WHERE id = ?1")
	int reopenTicket(long tid);
	@Transactional
	  @Modifying
	@Query("update Ticket set summary=?1 WHERE id = ?2")
	int updateTicket(String summary, long id );
	@Query("Select summary from Ticket WHERE id = ?1")
	 String  getSummary(long tid);
	@Query("SELECT  requesttee_user_id FROM Ticket WHERE id = ?1")
	String getEmployeeIdByTicketId(long ticketId);

     
}

