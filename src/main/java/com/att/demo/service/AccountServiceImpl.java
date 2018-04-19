package com.att.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.att.demo.exception.CustomError;
import com.att.demo.model.Account;
import com.att.demo.model.User;



@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Account> accounts;
	
	public static final String ERR_CODE_INVALID_ADD_REQUEST= "ERR3";
	
	public static final String ERR_MESSAGE_INVALID_ADD_REQUEST= "Invalid post body";
		
	public static final String ERR_CODE_MISSING_ID= "ERR4";
		
	public static final String ERR_MESSAGE_MISSING_ID= "Missing required value for account id";
		
	public static final String ERR_CODE_MISSING_ACCOUNT_ID = "ERR5";
		
	public static final String ERR_MESSAGE_MISSING_ACCOUNT_ID= "Missing required value for  account id";
		
	public static final String ERR_CODE_MISSING_NAME= "ERR6";
		
	public static final String ERR_MESSAGE_MISSING_NAME= "Missing reuired value for account name";
	
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
	
	public void validateAccount(Account account, List<CustomError> errorMessages) {
		if (account == null) {
			errorMessages.add(new CustomError(ERR_MESSAGE_INVALID_ADD_REQUEST, ERR_CODE_INVALID_ADD_REQUEST));
		} else {
			if (account.getId() == 0L) {
				errorMessages.add(new CustomError(ERR_MESSAGE_MISSING_ID, ERR_CODE_MISSING_ID));
		
			if (account.getName() == null) {
				errorMessages.add(new CustomError(ERR_MESSAGE_MISSING_NAME, ERR_CODE_MISSING_NAME));
			}
		}
	}

}
}
