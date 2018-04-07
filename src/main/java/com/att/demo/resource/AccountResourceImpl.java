package com.att.demo.resource;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
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

	private static final AtomicLong counter = new AtomicLong(System.currentTimeMillis() * 1000);

	public static final Logger logger = LoggerFactory.getLogger(AccountResourceImpl.class);

	@Autowired
	private AccountService accountService; // Service which will do all data
	// retrieval/manipulation work

	private static String baseUrl = "/accounts";

	@Override
	public Response findAllAccounts() {
		List<Account> accounts = accountService.findAllAccounts();
		if (accounts == null) {
			return Response.status(Status.NOT_FOUND).entity("Unable to find accounts").build();
		}
		Link link = Link.fromUri(baseUrl).rel("self").build();
		ResourceCollection<Account> resource = new ResourceCollection<>(accounts);
		return Response.status(Response.Status.OK).entity(resource).links(link).build();
	}

	@Override
	public Response getAccount(long id) {
		Account account = accountService.findById(id);
		if (account == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Unable to find an account with id " + id).build();
		}
		Link link = Link.fromUri(baseUrl).rel("self").build();
		Resource<Account> resource = new Resource<>(account);
		return Response.status(Response.Status.OK).entity(resource).links(link).build();
	}

	@Override
	public Response createAccount(Account account) {
		String accountName = account.getName();
		long accountId = counter.incrementAndGet();
		Account existingAccount = accountService.findByName(accountName.trim());
		if (existingAccount == null) {
			Account newAccount = new Account();
			newAccount.setName(accountName);
			newAccount.setId(accountId);
			accountService.saveAccount(newAccount);
			Link link = Link.fromUri(baseUrl).rel("self").build();
			Resource<Account> resource = new Resource<>(newAccount);
			return Response.status(Response.Status.CREATED).entity(resource).links(link).build();
		} else if (accountService.isAccountExist(account)) {
			return Response.status(Response.Status.CONFLICT)
					.entity("An account already exists with name " + accountName).build();
		} else {
			return Response.status(Response.Status.NOT_MODIFIED).entity("Not Modified").build();
		}
	}

}