package com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord;

import java.util.List;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ChatRecord;

public interface IChatDao {

	ChatResponse getAllChatsByTicket(long ticketId);

	GenericUtilResponse insertChatByEmployee(ChatRecord chat);

	GenericUtilResponse insertChatBySupport(ChatRecord chat);

	ChatResponse getChatRecordGreaterThanId(long id);

}
