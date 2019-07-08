package com.accolite.internal.project.exemployeeportal.dtomodel.model;

public class Response {


	String message;
	int requestStatus;
	
	
	public Response(int resp, String msg) {
		this.requestStatus = resp;
		this.message = msg;
	}

	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(int resp) {
		this.requestStatus = resp;
	}

	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((requestStatus == 0) ? 0 : Integer.valueOf(requestStatus).hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Response other = (Response) obj;
//		if (resp == null) {
//			if (other.resp != null)
//				return false;
//		} else if (!requestStatus.equals(other.requestStatus)) {
//			return false;}
//		return true;
//	}
//	
}
