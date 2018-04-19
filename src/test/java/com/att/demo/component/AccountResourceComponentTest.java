package com.att.demo.component;

import java.net.InetAddress;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.demo.model.Account;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

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
	public void testfindAccount_success() {
		
		givenBaseSpec()
				.when()
				.get(uri + "/1")
				.then()
					.statusCode(200);
	}
	
	@Test
	public void testfindAccount_NotFound() {
		
		givenBaseSpec()
				.when()
				.get(uri + "/4")
				.then()
					.statusCode(404);
	}
	
	@Test
	public void testcreateAccount_Success() {
		givenBaseSpec()
				.body("{\"name\":\"Test User1\"}")
				.when()
				.post(uri)
				.then()
					.statusCode(201);
	}
	
	@Test
	public void testcreateAccount_failure() {
		givenBaseSpec()
				.body("{\"name\":\"Account1\"}")
				.when()
				.post(uri)
				.then()
					.statusCode(409);
	}
	
}
