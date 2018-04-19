package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.att.demo.exception.AccountAlreadyExistException;
import com.att.demo.exception.AccountNotFoundException;
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
	public ResponseEntity<Account> getAccount(@PathVariable int id ) {
		Account acct=accountService.findById(id);
		if(acct==null) {
			 throw new AccountNotFoundException("Account with id"+  id +"not found");
		}
		
		return ResponseEntity.ok(acct);
	}
	
	public ResponseEntity<Account> createAccount(@RequestBody Account acct ) {
		
		Account account=accountService.createAccount(acct);
		if(account==null) {
			throw new AccountAlreadyExistException("Unable to create. A Account with name already exist");
		}
		return ResponseEntity.ok(acct);
	}

}