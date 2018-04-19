package com.att.demo.service;

import java.util.List;

import com.att.demo.model.User;

public interface UserService {
	
	User findById(long id) throws Exception;
	
	User findByName(String name);
	
	void saveUser(User account);
	
	List<User> findAllAccounts();
	
	boolean isAccountExist(User account);

}
