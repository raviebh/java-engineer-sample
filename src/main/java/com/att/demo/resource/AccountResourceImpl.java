package com.att.demo.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
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
	
	public static final String ERR_CODE_EXISTING_ACCOUNT = "ERR1";

	public static final String ERR_MESSAGE_EXISTING_ACCOUNT = "Unable to create account. Account with name already exists";

	public static final String ERR_CODE_ACCOUNT_NOT_FOUND = "ERR2";

	public static final String ERR_MESSAGE_ACCOUNT_NOT_FOUND = "Account not found for id: ";

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


	/**
	 * Creates a new user from user object
	 * Returns: 400 if validation fails, 201 on success and 409 if user already exists
	 */
	@Override
	public Response createAccount(Account account) {
		Link link = Link.fromUri(baseUrl).rel("self").build();
		if (!accountService.isAccountExist(account)) {
			List<CustomError> errorMessages = new ArrayList<CustomError>(0);
			accountService.validateAccount(account, errorMessages);
			if (!errorMessages.isEmpty()) {
				return Response.status(HttpStatus.SC_BAD_REQUEST).links(link).entity(errorMessages).build();
			}
			accountService.saveAccount(account);
			logger.info("Succesfully created account with id: "+account.getId());
			return Response.status(HttpStatus.SC_CREATED).links(link).build();
		} else {
			logger.info("Error creating account with id: "+account.getId());
			return Response.status(HttpStatus.SC_CONFLICT).links(link)
					.entity(new CustomError(ERR_MESSAGE_EXISTING_ACCOUNT, ERR_CODE_EXISTING_ACCOUNT)).build();
		}
	}
	
	

	/**
	 * Retrieves an existing user with id.
	 * returns 404 if user doesn't exist
	 */
	@Override
	public Response findAccountById(Long id) {
		Account account = accountService.findById(id);
		if (account == null) {
			return Response.status(HttpStatus.SC_NOT_FOUND)
					.entity(new CustomError(ERR_MESSAGE_EXISTING_ACCOUNT, ERR_CODE_EXISTING_ACCOUNT)).build();
		}
		Link link = Link.fromUri(baseUrl).rel("self").build();
		return Response.ok(account).build();
	}
}