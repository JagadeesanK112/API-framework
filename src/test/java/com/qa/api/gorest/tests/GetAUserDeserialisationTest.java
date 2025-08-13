package com.qa.api.gorest.tests;

import static org.testng.Assert.assertNotNull;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.JsonUtil;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAUserDeserialisationTest extends BaseTest {
	String tokenid;
	@BeforeClass
	public void setToken() {
		tokenid= "d0c755452408063306c4fea6d190531c9b4a280be9c20ee684bb6d2678839b6b";
		ConfigManager.set("bearertoken", tokenid);
	}

	@Test
	public void getAuser() {
		User user = User.builder()
				.id(null)
				.name("neon")
				.email(StringUtil.getRandomEmail())
				.gender("male")
				.status("active")
				.build();
		//Create user	
	Response responsepost=	restClient.post(BASE_URL_GOREST, GOREST_Users_ENDPONT, user, null, null, AuthType.Bearer_Token, ContentType.JSON);
	Assert.assertEquals(responsepost.jsonPath().getString("name"), "neon");
	Assert.assertNotNull(responsepost.jsonPath().getString("id"));
		String userid= responsepost.jsonPath().getString("id");
		
		
		
		//get user
		
		Response responseget= restClient.get(BASE_URL_GOREST, GOREST_Users_ENDPONT +"/"+userid, null, null, AuthType.Bearer_Token, ContentType.JSON);
		
	User userResponse=	JsonUtil.deserialise(responseget, User.class);
		
	Assert.assertEquals(userResponse.getName(), user.getName());
	Assert.assertNotNull(userResponse.getId());
	
	}
}
