package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.pojo.User.UserBuilder;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest  extends BaseTest{

	@Test
	public void updateUserTest() {
	User user = User.builder()
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
	
// getting user id
Response responseget= restClient.get(BASE_URL_GOREST, GOREST_Users_ENDPONT +"/"+userid, null, null, AuthType.Bearer_Token, ContentType.JSON);
Assert.assertEquals(responseget.jsonPath().getString("name"), "neon");
Assert.assertNotNull(responseget.jsonPath().getString("id"));

//update username and status
user.setName("NEEM");
user.setStatus("inactive");

Response responseput =restClient.put(BASE_URL_GOREST, GOREST_Users_ENDPONT +"/"+userid, user, null, null, AuthType.Bearer_Token, ContentType.JSON);

Assert.assertEquals(responseput.jsonPath().getString("name"), "NEEM");
Assert.assertNotNull(responseput.jsonPath().getString("status"), "inactive");


//get the updated user
 responseget= restClient.get(BASE_URL_GOREST, GOREST_Users_ENDPONT +"/"+userid, null, null, AuthType.Bearer_Token, ContentType.JSON);
Assert.assertEquals(responseget.jsonPath().getString("name"), "NEEM");
Assert.assertNotNull(responseget.jsonPath().getString("id"));
	}
}
