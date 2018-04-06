package com.att.demo.integration;

import org.junit.Before;
import org.junit.Test;

import com.att.demo.model.Account;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class AccountServiceIT {
	
	private String uri ="/accounts";
		
	@Before
	public void setUp() throws Exception {
		String baseURI = System.getProperty("BASE_URL");
		RestAssured.baseURI = baseURI + "/api";
	}
	
	private RequestSpecification givenBaseSpec() {
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
	public void testgetAccount_success() {
		
		givenBaseSpec()
				.when()
				.get(uri+"/1")
				.then()
					.statusCode(200);
	}
	
	@Test
	public void testgetAccount_fail() {
		
		givenBaseSpec()
				.when()
				.get(uri+"/12345")
				.then()
					.statusCode(404);
	}
	
	private RequestSpecification getSpec() {
		String input = "{\"id\":\"\",\"name\":\"Account9\"}";
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(input);
		builder.setContentType("application/json; charset=UTF-8");
		return builder.build();
	}

	@Test
	public void testcreateAccount_success() {
		
		givenBaseSpec()
				.when().spec(getSpec())
				.post(uri)
				.then()
					.statusCode(201);
	}
	
	@Test
	public void testcreateAccount_fail() {
		
		givenBaseSpec()
				.when().spec(getSpec())
				.post(uri)
				.then()
					.statusCode(409);
	}

}