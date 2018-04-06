package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	AccountService accountService; // Service which will do all data
									// retrieval/manipulation work

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
	public Response getAccount(long id) {
		Account account = accountService.findById(id);
		if (account == null) {
			return Response.status(Status.NOT_FOUND).entity("Unable to find an account with id " + id).build();
		}
		Link link = Link.fromUri(baseUrl).rel("self").build();
		Resource<Account> resource = new Resource<>(account);
		return Response.ok(resource).links(link).build();
	}

	@Override
	public Response createAccount(String name) {
		Account account = accountService.findByName(name.trim());
		if (accountService.isAccountExist(account)) {
			return Response.status(Status.CONFLICT).entity("An account already exists with name " + name).build();
		}
		else{
			account.setName(name);
			accountService.saveAccount(account);
			return Response.status(Status.OK).entity("Success").build();
		}
	}

}