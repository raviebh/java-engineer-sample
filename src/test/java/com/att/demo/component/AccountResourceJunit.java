package com.att.demo.component;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.demo.model.Account;
import com.att.demo.service.AccountService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


public class AccountResourceJunit {

	@Autowired
	AccountService accountService;

	@Test
	public void whencreate() {
		Account account = new Account();
		account.setId(44);
		account.setName("Account4");
		accountService.saveAccount(account);
		assertThat(accountService.isAccountExist(account)).isEqualTo(true);
	}

	@Test
	public void whenget() {
		assertThat(accountService.findByName("Account4")).isEqualTo("Account4");
	
	}
	
}
