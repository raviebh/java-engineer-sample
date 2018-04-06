package com.att.demo.model.swagger;

import com.att.demo.model.Account;
import com.att.demo.model.User;
import com.att.demo.model.representation.Resource;

public class UserResponse extends Resource<User> {

	public UserResponse(User content) {
		super(content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public User getContent() {
		return super.getContent();
	}
}
