package com.att.demo.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.att.demo.exception.CustomError;
import com.att.demo.model.Account;


@RestController
@RequestMapping("/api")
public class AccountServiceController {

	public static final Logger logger = LoggerFactory.getLogger(AccountServiceController.class);

	@Autowired
	AccountService accountService; // Service which will do all data retrieval/manipulation work

	//Retrieve Single Account with id

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccount(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		Account account = accountService.findById(id);
		if (account == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomError("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	//Create a Account

		@RequestMapping(value = "/account/", method = RequestMethod.POST)
		public ResponseEntity<?> createAccount(@RequestBody Account account, UriComponentsBuilder ucBuilder) {
			logger.info("Creating User : {}", account);

			if (AccountService.isAccountExist(account)) {
				logger.error("Unable to create. A Account with name {} already exist", account.getName());
				return new ResponseEntity(new CustomError("Unable to create. A Account with name " + 
				account.getName() + " already exist."),HttpStatus.CONFLICT);
			}
			accountService.saveAccount(account);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/account/{id}").buildAndExpand(account.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}

}