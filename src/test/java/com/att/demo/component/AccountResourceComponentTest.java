package com.att.demo.component;

import java.net.InetAddress;

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
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountResourceComponentTest {
	@LocalServerPort
	protected int randomServerPort;
	private String uri = "/accounts";

	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI = "http://" + InetAddress.getLocalHost().getHostName() + ":" + randomServerPort + "/api";
	}

	private RequestSpecification givenBaseSpec() {
		return RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON);
	}

	private RequestSpecification createAccountSpecFailure() {
		Account account = new Account(1L, "Account3");
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(account);
		builder.setContentType("application/json; charset=UTF-8");
		return builder.build();
	}

	private RequestSpecification createAccountSpecSuccess() {
		Account account = new Account(1L, "Account4");
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(account);
		builder.setContentType("application/json; charset=UTF-8");
		return builder.build();
	}

	@Test
	public void testfindAllAccount_success() {

		givenBaseSpec().when().get(uri).then().statusCode(200);
	}

	// Test for retreiving account using the id
	@Test
	public void testfindAccountById_Success() {
		givenBaseSpec().when().get(uri + "/" + 1).then().statusCode(200);
	}

	// Test for retrieving non existent account id
	@Test
	public void testfindAccountById_Failure() {
		givenBaseSpec().when().get(uri + "/" + 123).then().statusCode(404);
	}

	// Test for successful account creation
	@Test
	public void testCreateAccount_Success() {
		RestAssured.given().spec(createAccountSpecSuccess()).post(uri + "/createAccount").then().statusCode(201);
	}

	// Test for account name conflict
	@Test
	public void testCreateAccount_Failure() {
		RestAssured.given().spec(createAccountSpecFailure()).when().post(uri + "/createAccount").then().statusCode(409);

	}
}
