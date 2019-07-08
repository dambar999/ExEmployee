package com.accolite.internal.project.exemployeeportal.dao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord.ChatResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord.IChatDao;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord.IChatRepository;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.ExEmployeeRepository;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.user.UserRepository;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ChatRecord;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ExEmployee;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.User;
import com.accolite.internal.project.exemployeeportal.util.GenericUtil;
import com.accolite.internal.project.exemployeeportal.util.ResponseUtil;

@Service
public class ChatDaoImpl implements IChatDao {

	@Autowired
	IChatRepository chatRepoImpl;

	@Autowired
	ExEmployeeRepository exEmployeeRepositoryImpl;

	@Autowired
	UserRepository userRepositoryImpl;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ChatResponse getAllChatsByTicket(long ticketId) {
		ChatResponse response = new ChatResponse();
		List<ChatRecord> chatRecords = chatRepoImpl.findAllByTicketId(ticketId);
		if (null == chatRecords || chatRecords.size() == 0) {
			if (logger.isInfoEnabled()) {
				logger.info("No Records found with ticket Id :  " + ticketId);
			}
			ResponseUtil.error(response, "No Records found with ticket Id :  " + ticketId);
			return response;
		}
		response.setChatRecords(chatRecords);
		ResponseUtil.success(response);
		return response;
	}

	@Override
	public GenericUtilResponse insertChatByEmployee(ChatRecord chat) {
		ExEmployee exEmployee = exEmployeeRepositoryImpl.findById(chat.getEmployeeId());
		User user = userRepositoryImpl.findById(chat.getSupportId());
		if (null == exEmployee) {
			GenericUtilResponse response = new GenericUtilResponse(false, "Ex Employee does not exist");
			if (logger.isInfoEnabled()) {
				logger.info("Ex Employee with id: " + chat.getEmployeeId() + "does not exist");
			}
			ResponseUtil.error(response, "Ex Employee with email: " + chat.getEmployeeId() + "does not exist");
			return response;
		}
		if (null == user) {
			GenericUtilResponse response = new GenericUtilResponse(false, "Support staff does not exist");
			if (logger.isInfoEnabled()) {
				logger.info("Support staff with id: " + chat.getSupportId() + "does not exist");
			}
			ResponseUtil.error(response, "Ex Employee with email: " + chat.getSupportId() + "does not exist");
			return response;
		}
		if (!(exEmployee.getEmail().equals(GenericUtil.getCurrentExEmployeeUser()))) {
			GenericUtilResponse response = new GenericUtilResponse(false, "Update can happen only for yourself");
			if (logger.isInfoEnabled()) {
				logger.info("Updating Other User Information with " + exEmployee.getId() + "is not allowed");
			}
			ResponseUtil.error(response,
					"Updating Other User Information with " + exEmployee.getId() + "is not allowed");
			return response;
		}

		chat.setSendTime(new Date());
		chat.setSentBy(exEmployee.getEmail());
		chatRepoImpl.save(chat);
		GenericUtilResponse response = new GenericUtilResponse(true, "Chat Details updated");
		ResponseUtil.success(response);
		return response;
	}

	@Override
	public GenericUtilResponse insertChatBySupport(ChatRecord chat) {
		ExEmployee exEmployee = exEmployeeRepositoryImpl.findById(chat.getEmployeeId());
		User user = userRepositoryImpl.findById(chat.getSupportId());
		if (null == exEmployee) {
			GenericUtilResponse response = new GenericUtilResponse(false, "Ex Employee does not exist");
			if (logger.isInfoEnabled()) {
				logger.info("Ex Employee with id: " + chat.getEmployeeId() + "does not exist");
			}
			ResponseUtil.error(response, "Ex Employee with email: " + chat.getEmployeeId() + "does not exist");
			return response;
		}
		if (null == user) {
			GenericUtilResponse response = new GenericUtilResponse(false, "Support staff does not exist");
			if (logger.isInfoEnabled()) {
				logger.info("Support staff with id: " + chat.getSupportId() + "does not exist");
			}
			ResponseUtil.error(response, "Support staff with email: " + chat.getSupportId() + "does not exist");
			return response;
		}
		if (!(user.getEmail().equals(GenericUtil.getCurrentUser()))) {
			GenericUtilResponse response = new GenericUtilResponse(false, "Update can happen only for yourself");
			if (logger.isInfoEnabled()) {
				logger.info("Updating Other User Information with " + user.getId() + "is not allowed");
			}
			ResponseUtil.error(response, "Updating Other User Information with " + user.getId() + "is not allowed");
			return response;
		}
		chat.setSendTime(new Date());
		chat.setSentBy(user.getEmail());
		chatRepoImpl.save(chat);
		GenericUtilResponse response = new GenericUtilResponse(true, "Chat Details updated");
		ResponseUtil.success(response);
		return response;
	}

	@Override
	public ChatResponse getChatRecordGreaterThanId(long id) {
		ChatResponse response = new ChatResponse();
		List<ChatRecord> chatRecords = chatRepoImpl.findAllGreaterThanId(id);
		if (null == chatRecords || chatRecords.size() == 0) {
			if (logger.isInfoEnabled()) {
				logger.info("No Records found with greater than ticket Id :  " + id);
			}
			ResponseUtil.error(response, "No Records found with greater than ticket Id :  " + id);
			return response;
		}
		response.setChatRecords(chatRecords);
		ResponseUtil.success(response);
		return response;
	}

}
