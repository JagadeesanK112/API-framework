package com.qa.api.gorest.tests;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest {

	
	@Test
	public void deleteUsertest() {
		User user = User.builder()
				.name("NEEN")
				.email(StringUtil.getRandomEmail())
				.gender("male")
				.status("active")
				.build();
		
	Response responsePost=	restClient.post(BASE_URL_GOREST, GOREST_Users_ENDPONT, user, null, null, AuthType.Bearer_Token, ContentType.JSON);
	
	Assert.assertEquals(responsePost.jsonPath().getString("name"), "NEEN");
	Assert.assertNotNull(responsePost.jsonPath().getString("id"));
	String userid=responsePost.jsonPath().getString("id");
	
	Response responseGet= restClient.get(BASE_URL_GOREST, GOREST_Users_ENDPONT+"/"+ userid , null, null, AuthType.Bearer_Token, ContentType.JSON);
	Assert.assertEquals(responseGet.jsonPath().getString("name"), "NEEN");
	Assert.assertNotNull(responseGet.jsonPath().getString("id"));
	
	
Response responseDelete=restClient.delete(BASE_URL_GOREST, GOREST_Users_ENDPONT +"/"+userid, null, null, AuthType.Bearer_Token, ContentType.JSON);
Assert.assertNotNull(responseDelete.statusLine().contains("No Content"));
	
	}
}
