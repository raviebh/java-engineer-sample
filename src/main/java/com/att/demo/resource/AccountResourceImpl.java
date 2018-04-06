package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.att.demo.exception.CustomError;
import com.att.demo.model.Account;
import com.att.demo.model.representation.Resource;
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
	
	/***
	 * Get Account
	 */
	public Response getAccount(long id)
	{
		Account account = accountService.findById(id);	
		if (account == null) {
			return Response.status(404).entity(new CustomError("Account with id "+id+"not found","404")).build();
		}		
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		Resource<Account> resource = new Resource<>(account);
		return Response.ok(resource).links(link).build();
	}

	public Response createAccount(Account account) {
		
		Account existingAccount = accountService.findByName(account.getName());
		if(null!=existingAccount)
		{
			return Response.status(409).entity(new CustomError("A Account with name already exist","409")).build();
		}
		
		Account newaccount = accountService.saveAccount(account);
		if (newaccount == null) {
			return Response.noContent().build();//TODO  {"errorMessage": "Account with id 123 not found", "errorCode": "NOT_FOUND" }
		}		
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		Resource<Account> resource = new Resource<>(newaccount);
		return Response.ok(resource).links(link).build();
	}

	

}