package com.att.demo.resource;


/* 
 * Request Body to capture the request for account
 * 
 */

public class AccountRequest {
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
