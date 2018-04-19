package com.att.demo.resource;

import com.att.demo.exception.CustomError;
import com.att.demo.model.Account;
import com.att.demo.model.representation.ResourceCollection;
import com.att.demo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

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
	public Response getAccount(Integer id) {

		Account account = accountService.findById(Long.valueOf(id));
		List<Account> accountList = null;
		if (account == null) {
			CustomError error = new CustomError("Account with id "+id+" not found","NOT_FOUND");
			return Response.status(404)
					.entity(error)
					.type(MediaType.APPLICATION_JSON)
					.build();

		}
		accountList = new ArrayList<Account>();
		accountList.add(account);
		Link link = Link.fromUri(baseUrl).rel("self").build();
		ResourceCollection<Account> resource = new ResourceCollection<>(accountList);
		return Response.ok(resource).links(link).build();

	}

	@Override
	public Response saveAccount(Account account) {
		try {
			if(accountService.isAccountExist(account)){
				CustomError error = new CustomError("Unable to create. A Account with name already exist","CONFLICTED");
				return Response.status(409)
						.entity(error)
						.type(MediaType.APPLICATION_JSON)
						.build();
			}
			accountService.saveAccount(account);
		} catch (Exception e) {
			return Response.noContent().build();
		}

		Link link = Link.fromUri(baseUrl).rel("self").build();
		ResourceCollection<Account> resource = new ResourceCollection<>(null);
		return Response.ok(resource).links(link).build();
	}


}