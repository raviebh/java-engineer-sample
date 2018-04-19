package com.att.demo.component;


import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.att.demo.model.User;
import com.att.demo.resource.UserResource;
import com.att.demo.service.UserService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UserResource.class, secure = false)
public class UserResourceComponentTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userServiceImpl;

	User sampleUser = new User(1,"Josh",26,12345);

	
	@Test
	public void findOneTest() throws Exception {

		Mockito.when(userServiceImpl.findbyUserId(Mockito.anyInt())).thenReturn(sampleUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/findUser/1").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id: 1,name: josh,age: 26,accountId: 12345}";

		System.out.println(result.getResponse().getContentAsString());
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void findAllTest() throws Exception {
		
		List<User> userLst = Arrays.asList(new User(1,"Josh",26,12345), new User(2,"Jearmy",27,34512),new User(3,"John",28,45111));

		Mockito.when(userServiceImpl.getUsers()).thenReturn(userLst);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/findAllUsers").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.SC_OK, response.getStatus());

	}
	


	@Test
	public void addUserTest() throws Exception {

		String postsample = "{\r\n" + "    \"id\": 1,\r\n" + "    \"name\": \"bharat\",\r\n" + "    \"age\": 28,\r\n"
				+ "    \"accountId\": 1234\r\n" + "}";
		Mockito.when(userServiceImpl.createUser(Mockito.any(),Mockito.anyInt())).thenReturn(sampleUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addUser").accept(MediaType.APPLICATION_JSON)
				.content(postsample).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.SC_OK, response.getStatus());

	}

	

}

