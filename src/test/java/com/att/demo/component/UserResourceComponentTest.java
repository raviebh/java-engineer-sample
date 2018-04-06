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
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceComponentTest {
	@LocalServerPort
	protected int randomServerPort;
	
	private String uri ="/users";
	
	private  RequestSpecification givenBaseSpec() {
		return 
				RestAssured.given()
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON);
	}
	
	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI = "http://" + InetAddress.getLocalHost().getHostName() + ":" + randomServerPort + "/api";
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void whenCorrectIdThenReturnCorrectResult() {
		
		User expected = new User(1, "Biniam Shibru" , 56, 1);
	   Response response=	givenBaseSpec().when()
		              .get(uri+"/{accountId}");
		              
			     Assert.assertEquals(expected, response.getBody());
		
	}
	
	@Test
	public void whenIncorrectIdThenReturnNoContent() {
		
		givenBaseSpec()
				.when()
				.get(uri+"/99")
				.then()
					.statusCode(404);
	}
	
	//test save user 
	public void testSaveUser() {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		User user = new User(1, "Biniam Shibru" , 56, 2);
		builder.setBody(user);
		builder.setContentType("application/json");
			
		RequestSpecification requestSpec = builder.build();
	
		givenBaseSpec()
		.spec(requestSpec)
		.when()
		.post(uri).then().statusCode(201);
	}
	
	
	
}
