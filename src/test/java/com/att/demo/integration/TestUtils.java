package com.att.demo.integration;

import java.io.IOException;
import java.util.List;

import com.att.demo.model.Account;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T jsonToObject(String json, Class<T> classOf) throws JsonParseException, JsonMappingException, IOException {		
		return new ObjectMapper().readValue(json, classOf);
	}
	
	
	public static List<Class<Account>> jsonToList(String json, Class<Account> classOf) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Class<Account>> myObjects = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, classOf));
		return myObjects;
	}

}

