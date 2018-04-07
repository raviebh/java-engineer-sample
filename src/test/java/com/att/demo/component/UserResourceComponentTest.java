/**
 * 
 */
package com.att.demo.component;

import java.net.InetAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.demo.model.User;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Test class for user service api
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceComponentTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI = "http://" + InetAddress.getLocalHost().getHostName() + ":" + randomServerPort + "/api";
	}

	@LocalServerPort
	protected int randomServerPort;
	private String uri = "/users";

	private RequestSpecification givenBaseSpec() {
		return RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON);
	}

	private RequestSpecification createUserSpecSuccess() {
		User user = new User(1L, "TestUser", 20, 123);
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBody(user);
		builder.setContentType("application/json; charset=UTF-8");
		return builder.build();
	}

	@Test
	public void testfindAllUsers_success() {

		givenBaseSpec().when().get(uri).then().statusCode(200);
	}

	// Test for retrieving non-existent user id
	@Test
	public void testfindUserById_Failure() {
		givenBaseSpec().when().get(uri + "/" + 123).then().statusCode(404);
	}

	// Test for retrieving existent user id
	@Test
	public void testfindUserById_Success() {
		givenBaseSpec().when().get(uri + "/" + 1).then().statusCode(200);
	}

	// Test successful User creation
	@Test
	public void testUserCreationSuccess() {
		RestAssured.given().spec(createUserSpecSuccess()).when().post(uri + "/createUser").then().statusCode(201);
	}
}
