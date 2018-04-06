package com.att.demo.component;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicLong;

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
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
	public void whenCorrectInputThenReturnSuccess() {
		givenBaseSpec()
		.when()
		.get(uri+"/1")
		.then().statusCode(200);
	}
	
	//Testing getAccount for Account not found in the list
	@Test
	public void whenAccountNotFoundThenReturnNOT_FOUND() {
		givenBaseSpec()
		.when()
		.get(uri+"/7")
		.then().statusCode(404);
	}
	
	//Testing creatAccount for NULL input
	@Test
	public void whenAccountIsNullThenReturnBAD_REQUEST() {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		String requestBody = null;
		builder.setBody(requestBody);
		builder.setContentType("application/json");
			
		RequestSpecification requestSpec = builder.build();
		
		 	givenBaseSpec()
				.spec(requestSpec)
				.when()
				.post(uri).then().statusCode(400);
		
	}
	
	//Testing createAccount for Conflicting input
	@Test
	public void whenAccountConflictThenReturnCONFLICT_CODE() {
		
		RequestSpecBuilder builder = new RequestSpecBuilder();
		Account requestBody = new Account(1L,"Account1");
		builder.setBody(requestBody);
		builder.setContentType("application/json");
			
		RequestSpecification requestSpec = builder.build();
	
		givenBaseSpec()
		.spec(requestSpec)
		.when()
		.post(uri).then().statusCode(409);
		
	}
	
}
