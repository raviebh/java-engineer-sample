package com.att.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.att.demo.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static List<User> users ;

	static{
		users= populateDummyUsers();
	}

	@Override
	public List<User> getUsersByAccountId(long id) {
		// TODO Auto-generated method stub
		List<User> returnUsers= new ArrayList<>();
		for(User user : users){
			if(user.getAccountId() == id){
				returnUsers.add(user);
			}
		}
		return returnUsers.isEmpty()?null:returnUsers;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		users.add(user);
		
	}
	
	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(1, "Biniam Shibru" , 56, 1));
		users.add(new User(2, "Biniam Shibru2" , 56, 2));
		users.add(new User(3, "Biniam Shibru3" , 56, 3));
    return users;
    }
}
