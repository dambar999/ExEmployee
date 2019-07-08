package com.accolite.internal.project.exemployeeportal.dtomodel.model;

public enum ResponseStatus {
	Success(200),
	Partial(300),
	Error(400),
	Fail(500),
	NoDataFound(600);

	private int code;

	ResponseStatus(int code)
	{
		this.code = code;
	}

	public int getCode()
	{
		return code;
	}

}
