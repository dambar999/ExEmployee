package com.accolite.internal.project.exemployeeportal.dtomodel.dto.chatrecord;

import java.util.List;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.message.Response;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ChatRecord;

public class ChatResponse extends Response {
	private List<ChatRecord> chatRecords;

	public List<ChatRecord> getChatRecords() {
		return chatRecords;
	}

	public void setChatRecords(List<ChatRecord> chatRecords) {
		this.chatRecords = chatRecords;
	}

}
