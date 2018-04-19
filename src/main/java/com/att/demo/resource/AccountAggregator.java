package com.att.demo.resource;

import com.att.demo.model.Account;

public interface AccountAggregator {
	
	String createAccount(Account account);
	
	Account getAccountById(String accountId);

}
