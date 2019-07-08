package com.accolite.internal.project.exemployeeportal.dtomodel.dto.message;

import java.util.List;

public class Response {
	public enum Status {OK, ERROR, ALREADY_EXISTS}

	private Status status;

	private int statusCode;

	//used for pagination

	private String infoMessage = "";

	private long time;

	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getStatusCode()
	{
		return statusCode;
	}
	public void setStatusCode(int statusCode)
	{
		this.statusCode = statusCode;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}
	
	/**
	 * @return the infoMessage
	 */
	public String getInfoMessage() {
		return infoMessage;
	}
	/**
	 * @param infoMessage the infoMessage to set
	 */
	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}
	/**
	 * Returns true if at least one ErrorMessage has been added to this
	 * response.
	 * @return boolean
	 */

	@Override
	public String toString() {
		return "Response [status=" + status + ", statusCode=" + statusCode + ", fetchSize="
				+ ", infoMessage=" + infoMessage + ", time=" + time + "]";
	}

}
