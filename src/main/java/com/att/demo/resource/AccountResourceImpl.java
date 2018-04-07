package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.PathParam;
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
	
	
	@Override
	public Response getAccount(@PathParam("id") Long id) {
		Account account = accountService.findById(id);			
				
			if(account.getId()==id){
				return Response.status(200).entity(account).build();		
			}else{
				CustomError error = new CustomError("Account with id "+id+" not found","NOT_FOUND");
				return Response.status(404).entity(error).build();	
			}
	}


	@Override
	public Response createAccount(Account a) {	
		
		if(!accountService.isAccountExist(a)){
			System.out.println("Account does not exit, so adding the account" + a);
			accountService.saveAccount(a);
			return Response.status(201).entity(a).build();		
			
		}else{

			System.out.println("Account already exit" + a);
			return Response.status(404).entity("Unable to create. A Account with name already exist").build();	
			
		}
		
}	
	
}