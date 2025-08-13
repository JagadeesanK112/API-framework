package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import io.restassured.response.Response;

public class JsonPathValidatorUtil {
	
	
	public static String getReadContext(Response response) {
		String responsebody=response.getBody().asString();
		return responsebody;
	}

	public static <T> T read(Response response,String jsonPath) {
		
	
	ReadContext ctx= JsonPath.parse(getReadContext(response));
	return ctx.read(jsonPath);
	}

	public static <T> List <T> readList(Response response,String jsonPath) {
		
		
		
		ReadContext ctx= JsonPath.parse(getReadContext(response));
		return ctx.read(jsonPath);
		}
	
	
	public static <T> List<Map<String,T>> readListofMaps(Response response,String jsonPath) {
		

		
		ReadContext ctx= JsonPath.parse(getReadContext(response));
		return ctx.read(jsonPath);
		}


	
}
