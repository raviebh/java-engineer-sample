package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

	@Override
	public Response getAccount(String accountId) {
		Account account = accountService.findById(Long.parseLong(accountId));		
		if (account == null) {
			 return Response.status(Response.Status.NOT_FOUND).entity("Account with id "+accountId+" not found: ").build();
		}		
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		Resource<Account> resource = new Resource<Account>(account);
		return Response.ok(resource).links(link).build();
	}	
	
	@Override	
	public Response createAccount(Account account) {
		if (accountService.isAccountExist(account)) {
			 return Response.status(Response.Status.CONFLICT).entity("Unable to create. A Account with name "+account.getName()+"  already exist").build();
		}	
		accountService.saveAccount(account);
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		Resource<Account> resource = new Resource<Account>(accountService.findByName(account.getName()));
		return Response.status(201).entity(resource).build();
	}	
}