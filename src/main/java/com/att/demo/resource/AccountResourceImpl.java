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
import com.att.demo.model.representation.ResourceCollection;
import com.att.demo.service.AccountService;

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
	public Response createAccount(Account account) {

		if(accountService.findByName(account.getName())!=null)
		{
			CustomError customError = new CustomError("","Unable to create. A Account with name  "+account.getName()+" already exist");
			return Response.status(409).entity(customError).build();
		}else {
			accountService.saveAccount(account);		
			return Response.status(201).build();
		}
	}

	@Override
	public Response getAccount(long acconutId) {
		Account account = accountService.findById(acconutId);		
		if (account == null) {
			CustomError customError = new CustomError("NOT_FOUND","Account with id "+acconutId+" not found");
			return Response.noContent().entity(customError).build();
		}		
		return Response.ok(account).build();
	}	
}