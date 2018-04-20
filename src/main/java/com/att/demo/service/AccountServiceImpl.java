package com.att.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.att.demo.model.Account;



@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Account> accounts;
	
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
	private static Map<String,Account> empMap = new LinkedHashMap<String,Account>();
	private Random random = new Random(System.currentTimeMillis());

	
	@Override
	public Account getAccount(String id) {

		if(empMap.get(id) != null) {
			return empMap.get(id);
			
		}
		return null;
	}


	@Override
	public Account createAccount(Account account) {

		String newId = getId();
		account.setId(Long.parseLong(newId));
		account.setName(account.getName());
		
		empMap.put(newId, account);

		return account;
	}



	private String getId() {
		return String.valueOf(Math.abs(random.nextInt() % 1000));
	}
}
