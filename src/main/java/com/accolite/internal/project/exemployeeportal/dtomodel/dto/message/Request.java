package com.accolite.internal.project.exemployeeportal.dtomodel.dto.message;

public class Request {
	private String user;
	private String userRole;
	
	//used for pagination
	private int fetchSize;
	private int pageNumber;

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getUserRole()
	{
		return userRole;
	}

	public void setUserRole(String userRole)
	{
		this.userRole = userRole;
	}

	public int getFetchSize ()
	{
		return fetchSize;
	}

	public void setFetchSize (int fetchSize)
	{
		this.fetchSize = fetchSize;
	}

	public int getPageNumber ()
	{
		return pageNumber;
	}

	public void setPageNumber (int pageNumber)
	{
		this.pageNumber = pageNumber;
	}

	public void copy (Request request)
	{
		setUser(request.getUser());
		setUserRole(request.getUserRole());
		setPageNumber(request.getPageNumber());
		setFetchSize(request.getFetchSize());
	}

}
