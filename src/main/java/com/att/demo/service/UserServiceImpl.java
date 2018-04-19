package com.att.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.att.demo.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static List<User> usrLst= new ArrayList<User>();
	
	
	static {
		usrLst.add(new User(1,"Josh",26,12345));
		usrLst.add(new User(2,"Jearmy",27,34512));
		usrLst.add(new User(3,"John",28,45111));
	}

	@Override
	public List<User> getUsers() {
		
		return usrLst;
	}

	@Override
	public User createUser(User usr, int accountId) {
	
		for(User user:usrLst) {
			if(user.getId()==usr.getId()) {
				return null;
			}
		}
		usrLst.add(usr);
		return usr;
	}
	
	@Override
	public User findbyUserId(int id) {
		for(User user:usrLst) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}

}
