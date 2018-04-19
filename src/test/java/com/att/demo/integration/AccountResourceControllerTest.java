package com.att.demo.integration;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.att.demo.model.Account;
import com.att.demo.resource.AccountResourceController;
import com.att.demo.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountResourceController.class)
public class AccountResourceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AccountService accountService;

	private final String BaseURL = "/account/";
	
	@Test
	public void testAddAccount() throws Exception {

		// prepare data and mock's behaviour
		Account mockAccount = new Account(1,"account1");
		when(accountService.saveAccount(any(Account.class))).thenReturn(mockAccount);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BaseURL+ "createAccount").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.asJsonString(mockAccount))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Response Status", HttpStatus.CREATED.value(), status);

		// verify that service method was called once
		verify(accountService).saveAccount(any(Account.class));

		Account resultAccount = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Account.class);
		assertNotNull(resultAccount);
		assertEquals(1l, resultAccount.getId());

	}
	
	
	@Test
	public void testGetAccount() throws Exception {

		// prepare data and mock's behaviour
		Account mockAccount = new Account(1, "account1");
		when(accountService.findById(any(Long.class))).thenReturn(mockAccount);

		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(BaseURL + "findById/{accountId}", new Long(1)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(accountService).findById(any(Long.class));

		Account resuttGetAccount = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Account.class);
		assertNotNull(resuttGetAccount);
		assertEquals(1l, resuttGetAccount.getId());
	}

	
	@Test
	public void testGetAccountNotExist() throws Exception {

		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(BaseURL + "findById/{accountId}", new Long(1)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND.value(), status);

		// verify that service method was called once
		verify(accountService).findById(any(Long.class));

		Account resuttGetAccount = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Account.class);
		assertNull(resuttGetAccount);
	}

	
	@Test
	public void testGetAllAccounts() throws Exception {

		// prepare data and mock's behaviour
		List<Account> acctList = buildMockAccounts();
		when(accountService.findAllAccounts()).thenReturn(acctList);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BaseURL +"findAllAccounts").accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(accountService).findAllAccounts();

		// get the List<Employee> from the Json response
		ObjectMapper objMapper = new ObjectMapper();
		List<Account> accountResluts = Arrays.asList(objMapper.readValue(result.getResponse().getContentAsString(), Account[].class));
		assertNotNull("Employees not found", accountResluts);
		assertEquals("Employee List", acctList.size(), accountResluts.size());

	}

	

	private List<Account> buildMockAccounts() {
		Account account1 = new Account(1, "account1");
		Account account2 = new Account(2, "account2");
		List<Account> accountListt = Arrays.asList(account1, account2);
		return accountListt;
	}

}
