package com.att.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class UserNotFountException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFountException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNotFountException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
