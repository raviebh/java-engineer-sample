package com.att.demo.resource;

import java.util.List;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public Response addAccount(Account newAccount) {

		if (accountService.findByName(newAccount.getName()) == null) {
			accountService.createAccount(newAccount);
			return Response.status(Response.Status.CREATED).entity(newAccount).build();
		}
		return Response.status(Response.Status.CONFLICT).entity("unable to create. A Account with name already exit")
				.build();
	}


	@Override
	public Response getAccountById(String id) {

		Account account = accountService.getAccount(id);

		if (account != null) {
			return Response.status(Response.Status.OK).entity(account).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Account with " + id + " not found").build();
	}


}