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
	
	private static String addAccount = "/accounts/createAccount";
	
	private static String getAccount = "/accounts/getAccount";
	
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
	public Response getAccount(Account account) {
			
		if(accountService.isAccountExist(account)) {
			//ResourceCollection<Account> resource = new ResourceCollection<>(account);
			return Response.ok().build();
	}
		else {
			CustomError error = new CustomError("Account with id "+account.getId()+" not found", "NOT_FOUND");
			logger.error(error.getErrorCode());
			logger.error(error.getErrorMessage());
		}
		Link link = Link.fromUri(getAccount).rel("self").build();		
		return Response.ok().links(link).build();
	}	
	
	@Override
	public Response createAccount(Account account) {
		// TODO Auto-generated method stub
		if(account != null)
		{
			accountService.saveAccount(account);	
			return Response.ok().build();
		}
		else {
			CustomError error = new CustomError("Account with id "+account.getId()+" not found", "NOT_FOUND");
			logger.error(error.getErrorCode());
			logger.error(error.getErrorMessage());
		}
		
		Link link = Link.fromUri(addAccount).rel("self").build();		
		return Response.ok().links(link).build();
	}	

	

}