package com.att.demo.component;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.demo.model.Account;
import com.att.demo.resource.AccountAggregator;
import com.att.demo.resource.AccountAggregatorImpl;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
public class AccountAggregatorImplTest {
	
	
	@Test
	public void createAccountTestwithListOfValue(){
		
		AccountAggregator accountAggregator=new AccountAggregatorImpl();
		AccountAggregatorImpl.accountList.addAll(mockList());
		
		Account account3=new Account();
		account3.setId(3);
		account3.setName("Account3");
		
		String response=accountAggregator.createAccount(account3);
		assertEquals("SUCCESS",response);
		
		
	}
	
	@Test
	public void createAccountTestwithEmptyList(){
		AccountAggregator accountAggregator=new AccountAggregatorImpl();
		AccountAggregatorImpl.accountList.removeAll(mockList());
		Account account4=new Account();
		account4.setId(4);
		account4.setName("Account4");
		
		String response=accountAggregator.createAccount(account4);
		assertEquals("SUCCESS",response);
	}
	
	@Test
	public void createAccountTestForExistingAccountt(){
		
		AccountAggregator accountAggregator=new AccountAggregatorImpl();
		AccountAggregatorImpl.accountList.addAll(mockList());
		
		Account acount1=new Account();
		acount1.setId(1);
		acount1.setName("Account1");
		
		String response=accountAggregator.createAccount(acount1);
		assertEquals("ERROR",response);
		
	}
	
	@Test
	public void getAccountByIdTestSuccessFlow(){
		
		AccountAggregator accountAggregator=new AccountAggregatorImpl();
		AccountAggregatorImpl.accountList.addAll(mockList());
		
		Account account=accountAggregator.getAccountById("1");
		assertEquals(1,account.getId());
		assertEquals("Account1", account.getName());
		
	}
	
	@Test
	public void getAccountByIdTestErrorFlow(){
		
		AccountAggregator accountAggregator=new AccountAggregatorImpl();
		AccountAggregatorImpl.accountList.addAll(mockList());
		
		Account account=accountAggregator.getAccountById("809");
		assertEquals(null,account);
	}
	

	List<Account> mockList(){
		List<Account> accountList=new ArrayList<Account>();
		Account acount1=new Account();
		Account acount2=new Account();
		
		acount1.setId(1);
		acount1.setName("Account1");
		accountList.add(acount1);
		
		acount2.setId(2);
		acount2.setName("Account2");
		accountList.add(acount2);
		
		return accountList;
		
	}
}
