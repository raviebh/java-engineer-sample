package com.att.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.att.demo.exception.CustomError;
import com.att.demo.model.Account;



@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Account> accounts;
	
	public static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	static{
		accounts= populateDummyAccounts();
	}

	public List<Account> findAllAccounts() {
		return accounts;
	}
	
	public Account findById(long id) {
		for(Account account : accounts){
			if(account.getId() == id){
				return account;
			}
		}
		return null;
	}
	
	public Account findByName(String name) {
		for(Account account : accounts){
			if(account.getName().equalsIgnoreCase(name)){
				return account;
			}
		}
		return null;
	}
	
	public void saveAccount(Account account) {
		counter.incrementAndGet();
		accounts.add(account);
	}

	public void updateAccount(Account account) {
		int index = accounts.indexOf(account);
		accounts.set(index, account);
	}

	
	public boolean isAccountExist(Account account) {
		return findByName(account.getName())!=null;
	}
	
	private static List<Account> populateDummyAccounts(){
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account(counter.incrementAndGet(),"Account1"));
		accounts.add(new Account(counter.incrementAndGet(),"Account2"));
		accounts.add(new Account(counter.incrementAndGet(),"Account3"));
		return accounts;
	}

	@Override
	public Account getAccount(long accountId) {
		// TODO Auto-generated method stub
		for(Account account : accounts){
			if(account.getId() == accountId){
				return account;
			}
			else {
				CustomError error = new CustomError("Account with id"+accountId +"not found", "NOT_FOUND");
				logger.error(error.getErrorCode());
				logger.error(error.getErrorMessage());
			}
		}
		return null;
	}
	
public Account createAccount(Account account)  {
	if(isAccountExist(account))
	{
		CustomError error = new CustomError("Unable to create. A Account with name"+ account.getName() +"already exist", "409");
		logger.error(error.getErrorCode());
		logger.error(error.getErrorMessage());
	}
	else {
		updateAccount(account);
	}
	
	return account;
		
	}

@Override
public Account getAccount(Long accountId) {
	// TODO Auto-generated method stub
	return null;
}

	
}
