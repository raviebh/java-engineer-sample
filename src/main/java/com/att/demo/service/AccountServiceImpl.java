package com.att.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.att.demo.exception.AccountNotFoundException;
import com.att.demo.model.Account;

@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Account> accounts;
	
	@Value("${accountresource.retry.count}")
	private int retryCount;
	
	static{
		accounts= populateDummyAccounts();
	}

	public List<Account> findAllAccounts() {
		return accounts;
	}
	
	public Account findById(long id) throws Exception {
		int count=0;
		for(Account account : accounts){
			System.out.println("accounts:" + accounts);
			if(account.getId() == id){
				return account;
			}
		}
		//Retry Mechanism
		count++;
		if(count<=retryCount) {
			findById(id);
		}
		throw new AccountNotFoundException("404", "Account with id" + id + "not found");
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

}
