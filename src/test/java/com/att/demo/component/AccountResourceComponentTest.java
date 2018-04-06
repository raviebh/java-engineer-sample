package com.att.demo.component;

import java.net.InetAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.demo.model.Account;
import com.att.demo.service.AccountService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountResourceComponentTest {
	@LocalServerPort
	protected int randomServerPort;
	@Autowired
	private AccountService accountService;
	private String uri ="/accounts";
	
	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI = "http://" + InetAddress.getLocalHost().getHostName() + ":" + randomServerPort + "/api";
	}
	
	private  RequestSpecification givenBaseSpec() {
		return 
				RestAssured.given()
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON);
	}
	
	@Test
	public void testfindAllAccount_success() {
		
		givenBaseSpec()
				.when()
				.get(uri)
				.then()
					.statusCode(200);
	}
	@Test
	public void testfindAccountById_Success(){
		givenBaseSpec().when().get(uri+"/"+1).then().statusCode(200);
	}
	@Test
	public void testfindAccountById_Failure(){
		givenBaseSpec().when().get(uri+"/"+123).then().statusCode(404);
	}
	@Test
	public void testCreateAccount_Success(){
		Account account = new Account();
		account.setName("Account4");
		accountService.saveAccount(account);
		givenBaseSpec()
		.when()
		.get(uri)
		.then()
			.statusCode(200);
		
	}
	@Test
	public void testCreateAccount_Failure(){
		Account account = accountService.findByName("Account3");
		
		
		if(accountService.isAccountExist(account)){
			givenBaseSpec()
			.when()
			.get(uri+"/createAccount")
			.then()
				.statusCode(405);
		}
		
		//accountService.saveAccount(account);
		
		
	}
}
