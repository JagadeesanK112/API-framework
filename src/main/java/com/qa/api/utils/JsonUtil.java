package com.qa.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class JsonUtil {
	
	private static ObjectMapper objectMapper =new ObjectMapper();
	
	public static  <T> T deserialise(Response response , Class <T> targetClass) {
		
		try {
			return objectMapper.readValue(response.getBody().asString(), targetClass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("deserialisation failed " + targetClass.getName());
		}
		
	}

}
