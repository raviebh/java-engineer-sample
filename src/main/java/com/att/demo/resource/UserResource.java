package com.att.demo.resource;

import java.util.List;
import com.att.demo.model.User;

/**
 * This is the Interface UserResource
 * 
 * 
 */
public interface UserResource {

	User findById(long id);

	User findByName(String name);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	void deleteAllUsers();

	boolean isUserExist(User user);

}
