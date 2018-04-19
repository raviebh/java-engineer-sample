package com.att.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.att.demo.exception.UserAlreadyExistException;
import com.att.demo.exception.UserNotFountException;
import com.att.demo.model.User;
import com.att.demo.model.representation.ResourceCollection;
import com.att.demo.service.UserService;

@Controller
public class UserResourceImpl implements UserResource {

	@Autowired
	UserService userServiceImpl;

	private static int count=3;
	@Override
	public ResponseEntity<ResourceCollection<User>> getUsers() {
		List<User> lst=userServiceImpl.getUsers();
		if(lst.size()==0) {
			return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST).body(null);
		}
		ResourceCollection<User> resource= new ResourceCollection<>(lst);
		return  ResponseEntity.ok(resource);
	}

	@Override
	public ResponseEntity<User> createUser(@RequestBody User usr) {
		User user=userServiceImpl.createUser(usr,++count);
		if(user==null) {
			throw new UserAlreadyExistException("User already Exists, id is"+usr.getId());
		}
		
		return ResponseEntity.ok(user);
	}
	
	@Override
	public ResponseEntity<User> findbyUserId(@PathVariable int id) {
		User user=userServiceImpl.findbyUserId(id);
		if(user==null) {
			throw new UserNotFountException("user not found for given id::"+id);
		}
		return ResponseEntity.ok(user);
	}
	
}
