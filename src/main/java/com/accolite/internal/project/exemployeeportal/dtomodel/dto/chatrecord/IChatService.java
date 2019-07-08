package com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord;

import java.util.List;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;

public interface IChatService {

	GenericUtilResponse insertChatByEmployee(ChatRequest request);

	GenericUtilResponse insertDiscussBySupport(ChatRequest request);

	ChatResponse getChatRecordGreaterThanId(long id);

	ChatResponse getAllChatsByTicket(long ticketId);

}
