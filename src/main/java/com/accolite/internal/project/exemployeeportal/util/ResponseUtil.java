package com.accolite.internal.project.exemployeeportal.util;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.message.Response;
import com.accolite.internal.project.exemployeeportal.dtomodel.model.ResponseStatus;

public final class ResponseUtil {
	public static void error(Response response) {
		response.setStatusCode(ResponseStatus.Error.getCode());
		response.setStatus(Response.Status.ERROR);
	}

	public static void fail(Response response) {
		response.setStatusCode(ResponseStatus.Fail.getCode());
		response.setStatus(Response.Status.ERROR);
	}

	public static void partial(Response response) {
		response.setStatusCode(ResponseStatus.Partial.getCode());
		response.setStatus(Response.Status.ERROR);
	}

	public static void success(Response response) {
		response.setStatusCode(ResponseStatus.Success.getCode());
		response.setStatus(Response.Status.OK);
	}
	
	public static void success(Response response, String infoMessage) {
		response.setStatusCode(ResponseStatus.Success.getCode());
		response.setStatus(Response.Status.OK);
		response.setInfoMessage(infoMessage);
	}

	public static void error(Response response, String message) {
		response.setStatusCode(ResponseStatus.Error.getCode());
		response.setInfoMessage(message);
		response.setStatus(Response.Status.ERROR);
	}

	public static void fail(Response response, String message) {
		response.setStatusCode(ResponseStatus.Fail.getCode());
		response.setInfoMessage(message);
		response.setStatus(Response.Status.ERROR);
	}

	public static void partial(Response response, String message) {
		response.setStatusCode(ResponseStatus.Partial.getCode());
		response.setInfoMessage(message);
		response.setStatus(Response.Status.ERROR);
	}

	public static void successNoData(Response response) {
		// TODO Auto-generated method stub
		response.setStatusCode(ResponseStatus.NoDataFound.getCode());
		response.setStatus(Response.Status.OK);
	}

}
