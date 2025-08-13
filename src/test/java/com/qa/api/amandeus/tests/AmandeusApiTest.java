package com.qa.api.amandeus.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AmandeusApiTest extends BaseTest {
private String token;
	@BeforeMethod
	public void genrateToken() {
		
Response response=restClient.post(BASE_URL_OAUTH2_AMANDEUS, AMANDEUS_OAUTH2_ENDPOINT, ContentType.URLENC,"client_credentials", "prAkheA7z2WvQ7TrVDFprgaCT0WO7ANm", "B82sVuVMZTuzlTAw");
	token= response.jsonPath().get("access_token");
	
	ConfigManager.set("bearertoken", token);
	}
	
	
	@Test
public void getFlightDetails() {
	
		Map<String,String> queryParams= new HashMap<String, String>();
		queryParams.put("origin", "PAR");
		queryParams.put("maxPrice", "200");
	Response response =	restClient.get(BASE_URL_OAUTH2_AMANDEUS, AMANDEUS_FLIGHT_DESTINATION, queryParams, null, AuthType.Bearer_Token, ContentType.ANY);
			Assert.assertEquals(response.getStatusCode(), 200);	
	}
	
}
