package com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accolite.internal.project.exemployeeportal.dtomodel.model.ChatRecord;

@Repository
public interface IChatRepository extends JpaRepository<ChatRecord, Long> {

	String tableName = "ChatRecord";

	List<ChatRecord> findAllByTicketId(long ticketId);

	String chatRecord = "select v from ChatRecord v where v.id >= :id order by send_time";

	@Query(value = chatRecord)
	List<ChatRecord> findAllGreaterThanId(@Param("id") long id);

}
