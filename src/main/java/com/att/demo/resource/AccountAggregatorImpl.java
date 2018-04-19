package com.att.demo.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.att.demo.model.Account;

@Component
public class AccountAggregatorImpl implements AccountAggregator{

	public static List<Account> accountList=new ArrayList<Account>();
	
	public String createAccount(Account account){
		
		if(!accountList.isEmpty()){
			boolean isAccountPresent=false;
			for(Account acc: accountList){
				if(account.getName().equalsIgnoreCase(acc.getName())){
					isAccountPresent=true;
					break;
				}
			}
			
			if(!isAccountPresent){
				accountList.add(account);
				return "SUCCESS";
			}else{
				return "ERROR";
			}
		}else{
			accountList.add(account);
			return "SUCCESS";
		}
		
	}
	
	public Account getAccountById(String accountId){
		
		if(!accountList.isEmpty()){
			int id=Integer.parseInt(accountId);
			for(Account account: accountList){
				if(id==account.getId())
					return account;
			}
			
		}
		return null;
		
	}
}
