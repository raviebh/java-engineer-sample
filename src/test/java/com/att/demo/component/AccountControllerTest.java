package com.att.demo.component;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.demo.controller.AccountController;
import com.att.demo.model.Account;
import com.att.demo.model.ServiceResponse;
import com.att.demo.resource.AccountAggregator;

@RunWith(SpringRunner.class)
@ComponentScan(basePackageClasses=AccountController.class)
public class AccountControllerTest {

	@Mock
	AccountAggregator accountAggregator;
	
	@Mock
	HttpServletResponse servletResponse;
	
	@InjectMocks
	AccountController controller;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(accountAggregator);
	}
	
	@Test
	public void createAccountTestSuccessFlow(){
		
		Mockito.when(accountAggregator.createAccount((Account)Mockito.anyObject())).thenReturn("SUCCESS");
		servletResponse.setStatus(Mockito.anyInt());
		ServiceResponse response=controller.createAccount(new Account(), servletResponse);
		assertEquals("SUCCESS",response.getType());

		
	}
	
	@Test
	public void createAccountTestErrorFlow(){
		
		Mockito.when(accountAggregator.createAccount((Account)Mockito.anyObject())).thenReturn("ERROR");
		servletResponse.setStatus(Mockito.anyInt());
		ServiceResponse response=controller.createAccount(new Account(), servletResponse);
		assertEquals("ERROR",response.getType());
		assertEquals("Unable to create. A Account with name already exist",response.getMessage());
		
	}
	
	@Test
	public void createAccountTestInvalidRequestFlow(){
		
		Mockito.when(accountAggregator.createAccount((Account)Mockito.anyObject())).thenReturn("ERROR");
		servletResponse.setStatus(Mockito.anyInt());
		ServiceResponse response=controller.createAccount(null, servletResponse);
		assertEquals("ERROR",response.getType());
		assertEquals("Please pass proper request",response.getMessage());
		
	}
	
	@Test
	public void getAccountByIdTestSuccessFlow(){
		
		Account account=new Account();
		account.setId(2);
		account.setName("Account2");
		
		Mockito.when(accountAggregator.getAccountById((String)Mockito.anyObject())).thenReturn(account);
		ServiceResponse response=controller.getAccountById("2",servletResponse);
		
		assertEquals(2,response.getAccount().getId());
		assertEquals("Account2",response.getAccount().getName());

	}
	
	@Test
	public void getAccountByIdTestErrorFlow(){
		
		
		Mockito.when(accountAggregator.getAccountById((String)Mockito.anyObject())).thenReturn(null);
		ServiceResponse response=controller.getAccountById("2",servletResponse);
		
		assertEquals("NOT_FOUND",response.getCode());
		assertEquals("Account with id 2 not found",response.getMessage());
		
		
	}
	
	@Test
	public void getAccountByIdTestInvalidRquestFlow(){
			
		ServiceResponse response=controller.getAccountById("",servletResponse);
		
		assertEquals("ERROR",response.getType());
		assertEquals("Please pass proper account Id",response.getMessage());
	}
	
}
