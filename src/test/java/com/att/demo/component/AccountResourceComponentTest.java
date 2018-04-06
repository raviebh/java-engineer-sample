package com.att.demo.component;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.net.InetAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountResourceComponentTest {
	@LocalServerPort
	protected int randomServerPort;
	
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
				.get(uri+"/1122")
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
