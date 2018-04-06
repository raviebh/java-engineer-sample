package com.att.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.att.demo.model.User;
import com.att.demo.model.User;



@Service("userservice")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateDummyUsers();
	}

	public User findUser(long accountId) {
		for(User User : users){
			if(User.getId() == accountId){
				return User;
			}
		}
		return null;
	}
	
	
	public User createUser(User User) {
		counter.incrementAndGet();
		users.add(User);
		return User;
	}

	
	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"User1",30,1));
		users.add(new User(counter.incrementAndGet(),"User2",31,2));
		users.add(new User(counter.incrementAndGet(),"User3",32,3));
		return users;
	}

	@Override
	public List<User> getUsers() {
		return users;
	}



}
