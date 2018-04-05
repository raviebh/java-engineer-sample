package com.att.demo.integration;

import org.junit.Before;
import org.junit.Test;

import com.att.demo.model.Account;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class AccountServiceIT {
	
	private String uri ="/accounts/findAllAccounts";
	
	private String uri2 ="/accounts/createAccount";
	
	private String uri3 ="/accounts/getAccount";
		
	@Before
	public void setUp() throws Exception {
		String baseURI = System.getProperty("BASE_URL");
		RestAssured.baseURI = baseURI + "/api";
	}
	
	private RequestSpecification givenBaseSpec() {
		return 
				RestAssured.given()
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON)
					;
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
	public void testCreateAccount_success() {
		
			
		Account account = new Account();
		account.setId(44);
		account.setName("Account4");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
					.statusCode(201);
	}
	
	@Test
	public void testCreateUser_failure() {
		
	Account account = new Account();
	account.setId(44);
	account.setName("Account4");
	
	givenBaseSpec()
		.body(account)
		.when()
			.post(uri2)
			.then()
				.statusCode(201);
	}
	
	@Test
	public void testGetAccount_success() {
		
		givenBaseSpec()
			.body("Account4")
			.when()
				.post(uri3)
				.then()
				.statusCode(201);

		givenBaseSpec()
		.when()
			.get(uri + "/12345")
			.then()
				.statusCode(200);
	}

}
