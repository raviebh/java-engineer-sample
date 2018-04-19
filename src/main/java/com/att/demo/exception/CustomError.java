package com.att.demo.exception;

public class CustomError {

    private String errorMessage;
    
    private String errorCode;
   
    public CustomError(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public CustomError(String errorMessage, String errorCode){
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
