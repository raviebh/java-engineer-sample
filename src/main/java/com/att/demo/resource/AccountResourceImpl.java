package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	public Response getAccount(@PathParam("id")long id) {
		Account account = accountService.findById(id);
		
		if(account==null) {
			
			String errorMessage = "Account with id" + id + " can't be found";
			String errrorCode= Response.Status.NOT_FOUND.name();
			CustomError error= new CustomError(errorMessage, errrorCode);
			
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error).build();
		}
		Link link = Link.fromUri(baseUrl+"/{id}").rel("self").build();		
		Resource<Account> resource = new Resource<Account>(account);
		return Response.ok(resource).links(link).build();
	}
    
	@Override
    public Response createAccount (Account account) {
    	if(account==null||account.getName()==null) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("Account or Account name is null").build();
    	}
    	if(accountService.findByName(account.getName())!=null) {
    		return Response.status(Response.Status.CONFLICT)
					.entity("Unable to create an Account with name "+account.getName()+" already exists").build();
    	}
    	accountService.saveAccount(account);
    	Link link = Link.fromUri(baseUrl).rel("self").build();
    	return Response.status(Response.Status.CREATED).links(link).build();
    }

}