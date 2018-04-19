package com.att.demo.exception;

public class AccountNotFoundException extends Exception{
	
	public AccountNotFoundException(String errorCode, String errorMessage) {
		super();
		this.customError.setErrorCode(errorCode);
		this.getCustomError().setErrorMessage(errorMessage);
	}

	CustomError customError;

	public CustomError getCustomError() {
		return customError;
	}

	public void setCustomError(CustomError customError) {
		this.customError = customError;
	}
	
}