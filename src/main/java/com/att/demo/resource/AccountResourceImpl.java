package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

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
	
	public static final String ACCOUNT_TXT = "Account with id ";
	public static final String NOT_FOUND_TXT = " not found";
	public static final String ACCOUNT_ALREADY_EXIST = "Unable to create. A Account with name already exist";
	public static final String NOT_FOUND_ERROR_CODE = "200001";
	public static final String ALREADY_EXIST_ERROR_CODE = "200002";

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
	public Response getAccount(long id) {
		Account account = accountService.findById(id);		
		if (account == null) {
			return Response.status(Status.NOT_FOUND).entity(new CustomError(ACCOUNT_TXT + id + NOT_FOUND_TXT, NOT_FOUND_ERROR_CODE)).build();
		}		
		Link link = Link.fromUri(baseUrl+"/"+account.getId()).rel("self").build();		
		Resource<Account> resource = new Resource<>(account);
		return Response.ok(resource).links(link).build();
	}
	
	@Override
	public Response createAccount(@RequestBody Account account) {
		if (accountService.isAccountExist(account)) {
			return Response.status(Status.CONFLICT).entity(new CustomError(ACCOUNT_ALREADY_EXIST, ALREADY_EXIST_ERROR_CODE)).build();	
		}
		accountService.saveAccount(account);	
		return Response.status(Status.CREATED).build();
	}
	

}