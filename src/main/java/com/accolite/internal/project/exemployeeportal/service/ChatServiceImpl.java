package com.accolite.internal.project.exemployeeportal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord.ChatRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord.ChatResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord.IChatDao;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord.IChatService;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ChatRecord;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;
import com.accolite.internal.project.exemployeeportal.util.ResponseUtil;

@Service
public class ChatServiceImpl implements IChatService {

	@Autowired
	IChatDao chatDaoImpl;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public GenericUtilResponse insertChatByEmployee(ChatRequest request) {

		GenericUtilResponse response = new GenericUtilResponse();
		ChatRecord chat = new ChatRecord();
		boolean isRequestFine = request.convertRequestToChatRecord(chat, request);
		if (!isRequestFine) {
			if (logger.isInfoEnabled()) {
				logger.info("Attention request failed to update chat record");
			}
			ResponseUtil.error(response, "Request does not contain all parameters");
			return response;

		}
		return chatDaoImpl.insertChatByEmployee(chat);
	}

	@Override
	public GenericUtilResponse insertDiscussBySupport(ChatRequest request) {
		GenericUtilResponse response = new GenericUtilResponse();
		ChatRecord chat = new ChatRecord();
		boolean isRequestFine = request.convertRequestToChatRecord(chat, request);
		if (!isRequestFine) {
			if (logger.isInfoEnabled()) {
				logger.info("Attention request failed to update chat record");
			}
			ResponseUtil.error(response, "Request does not contain all parameters");
			return response;

		}
		return chatDaoImpl.insertChatBySupport(chat);
	}

	@Override
	public ChatResponse getChatRecordGreaterThanId(long id) {
		// TODO Auto-generated method stub
		return chatDaoImpl.getChatRecordGreaterThanId(id);
	}

	@Override
	public ChatResponse getAllChatsByTicket(long ticketId) {

		return chatDaoImpl.getAllChatsByTicket(ticketId);
	}

}
