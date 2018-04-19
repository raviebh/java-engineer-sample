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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.att.demo.exception.AccountNotFoundException;
import com.att.demo.exception.CustomError;
import com.att.demo.model.Account;
import com.att.demo.model.representation.Resource;
import com.att.demo.model.representation.ResourceCollection;
import com.att.demo.model.swagger.AccountResponse;
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
	public Account findAccountById(Long id) throws Exception {
		if(logger.isDebugEnabled()){ 
			logger.debug("id:" + id);
		}
		return accountService.findById(id);
	}

	@Override
	public void createAccount(Account account) {
		if(logger.isDebugEnabled()) {
			logger.debug("account:" + account);
		}
		accountService.saveAccount(account);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomError accountNotFound(AccountNotFoundException exception) {
	  return exception.getCustomError();
	}
}