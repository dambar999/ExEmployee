package com.accolite.internal.project.exemployeeportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord.ChatRequest;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord.ChatResponse;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord.IChatService;
import com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee.GenericUtilResponse;

@RestController
@RequestMapping("/chat")
public class ChatController {

	/** Autowired chat service. */
	@Autowired
	IChatService chatServiceImpl;

	/**
	 * Get list of chats by ticketId.
	 *
	 * @param ticketId
	 * @return list of chats
	 */
	@GetMapping(value = "/getall")
	public ChatResponse getAllDiscuss(@RequestParam("ticketId") long ticketId) {
		return chatServiceImpl.getAllChatsByTicket(ticketId);
	}

	/**
	 * Method to insert new chats entry.
	 *
	 * @param chats details
	 * @return response
	 */
	@PostMapping(value = "/insert/employee")
	public GenericUtilResponse insertChatByEmployee(@RequestBody ChatRequest request) {
		return chatServiceImpl.insertChatByEmployee(request);
	}

	@PostMapping(value = "/insert/support")
	public GenericUtilResponse insertChatBySupport(@RequestBody ChatRequest request) {
		return chatServiceImpl.insertDiscussBySupport(request);
	}

	@GetMapping(value = "/get/empList")
	public ChatResponse getChatGreaterThanId(@RequestParam("id") long id) {
		return chatServiceImpl.getChatRecordGreaterThanId(id);
	}
}
