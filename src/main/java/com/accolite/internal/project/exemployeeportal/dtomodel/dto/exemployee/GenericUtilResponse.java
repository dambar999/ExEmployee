package com.accolite.internal.project.exemployeeportal.dtomodel.dto.exemployee;

import com.accolite.internal.project.exemployeeportal.dtomodel.dto.message.Response;

public class GenericUtilResponse extends Response{
    private boolean isValid;
    private String message;
    
    public GenericUtilResponse() {
    }
    public GenericUtilResponse(boolean success, String message) {
        this.isValid = success;
        this.message = message;
    }
    
    
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean success) {
        this.isValid = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
