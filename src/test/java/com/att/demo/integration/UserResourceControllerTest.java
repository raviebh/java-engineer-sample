package com.att.demo.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.att.demo.model.User;
import com.att.demo.resource.UserResourceController;
import com.att.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserResourceController.class)
public class UserResourceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	private final String BaseURL = "/user/";
	
	@Test
	public void testAddUser() throws Exception {

		// prepare data and mock's behavior
		User mockUser = new User(1, "user1", 35, 1);
		when(userService.saveUser(any(User.class))).thenReturn(mockUser);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BaseURL+ "createUser").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.asJsonString(mockUser))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Response Status", HttpStatus.CREATED.value(), status);

		// verify that service method was called once
		verify(userService).saveUser(any(User.class));

		User resultUser = TestUtils.jsonToObject(result.getResponse().getContentAsString(), User.class);
		assertNotNull(resultUser);
		assertEquals(1l, resultUser.getId());

	}
	
	@Test
	public void testGetAllUsers() throws Exception {

		// prepare data and mock's behaviour
		List<User> userList = buildMockUsers();
		when(userService.findAllUsers()).thenReturn(userList);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BaseURL +"findAllUsers").accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(userService).findAllUsers();

		// get the List<Employee> from the Json response
		ObjectMapper objMapper = new ObjectMapper();
		List<User> userResluts = Arrays.asList(objMapper.readValue(result.getResponse().getContentAsString(), User[].class));
		assertNotNull("Employees not found", userResluts);
		assertEquals("Employee List", userList.size(), userResluts.size());

	}
	
	private List<User> buildMockUsers() {
		User user1 = new User();
		User user2 = new User();
		List<User> userList = Arrays.asList(user1, user2);
		return userList;
	}

	

}
