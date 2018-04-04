package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.att.demo.exception.CustomError;
import com.att.demo.model.Account;
import com.att.demo.model.User;
import com.att.demo.model.representation.ResourceCollection;
import com.att.demo.service.AccountService;
import com.att.demo.service.UserService;

/**
 * This is the Controller class for Account mService
 * 
 * 
 */
@Controller
public class AccountResourceImpl implements AccountResource {
	
	public static final Logger logger = LoggerFactory.getLogger(AccountResourceImpl.class);

	@Autowired
	AccountService accountService; //Service which will do all data retrieval/manipulation work
	
	@Autowired
	UserService userService;

	private static String baseUrl = "/accounts";

	@Override
	public Response findAllAccounts() {
		List<Account> accounts = accountService.findAllAccounts();		
		if (accounts == null) {
			return Response.noContent().build();
		}		
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		ResourceCollection<Account> resource = new ResourceCollection<>(accounts);
		return Response.ok(resource).links(link).build();
	}	
	
	@Override
	public Response findAccountById(long id) {
		Account account = accountService.findById(id);
		
		if(account == null) {
			return Response.status(404).type("application/json").entity(new CustomError("Account with id " + id +" not found", "NOT_FOUND")).build();
		}
		return Response.ok(account).build();
	}
	
	@Override
	public Response createAccount(Account account) {
		boolean exists = accountService.isAccountExist(account);
		
		if(exists) {
			return Response.status(409).type("application/json").entity(new CustomError("Unable to create. A Account with name already exist", "CONFLICTED")).build();
		}
		accountService.saveAccount(account);
		return Response.status(201).type("text/plain").entity("Account created!").build();
	}

	@Override
	public Response findAccountByAndUsers(long id) {
		Account account = accountService.findById(id);
		
		if(account == null) {
			return Response.status(404).type("application/json").entity(new CustomError("Account with id " + id +" not found", "NOT_FOUND")).build();
		}
		List<User> usrList = userService.findAccountByAndUsers(id);
		
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		ResourceCollection<User> resource = new ResourceCollection<>(usrList);
		return Response.ok(resource).links(link).build();
	}

}