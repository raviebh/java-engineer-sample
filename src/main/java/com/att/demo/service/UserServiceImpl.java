/**
 * 
 */
package com.att.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.att.demo.model.User;

/**
 * 
 */
@Service("usertService")
public class UserServiceImpl implements UserService {
	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	static {
		users = populateDummyUsers();
	}

	@Override
	public User findById(long id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User findByName(String name) {
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public void saveUser(User user) {
		// counter.incrementAndGet();
		users.add(user);
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}

	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "User1", 20, 1));
		users.add(new User(counter.incrementAndGet(), "User2", 21, 2));
		users.add(new User(counter.incrementAndGet(), "User3", 22, 3));
		return users;
	}

}
