package com.qa.api.gorest.tests;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AppConstants;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.ExcelUtil;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {
String mail;
String tokenid;
@BeforeClass
public void setToken() {
	tokenid= "d0c755452408063306c4fea6d190531c9b4a280be9c20ee684bb6d2678839b6b";
	ConfigManager.set("bearertoken", tokenid);
}

@DataProvider
public Object[][] getUserData() {
	return new Object[][] {
		{"neon" ,"male", "active"},
		{"Leo" ,"male", "active"},
		{"Nancy" ,"female", "inactive"}
	};
}


@DataProvider
public Object[][] getExcelUserData() throws Exception {
	return ExcelUtil.readData(AppConstants.CREATE_USER_SHEET);
}
	@Test(dataProvider= "getExcelUserData")
	public void createAUserTest(String name, String gender, String status) {
		User user = new User(null ,name ,gender, StringUtil.getRandomEmail(), status );
	Response response=	restClient.post(BASE_URL_GOREST, GOREST_Users_ENDPONT, user, null, null, AuthType.Bearer_Token, ContentType.JSON);
	Assert.assertEquals(response.jsonPath().getString("name"), name);
	Assert.assertEquals(response.jsonPath().getString("gender"), gender);
	Assert.assertEquals(response.jsonPath().getString("status"), status);
	Assert.assertNotNull(response.jsonPath().getString("id"));
		ChainTestListener.log("user id" + response.jsonPath().getString("id"));
	}
	
	@Test
	public void createAUserWithJsonStringTest() {
		
		mail = StringUtil.getRandomEmail();
		String userjson= "{\r\n"
				+ "    \"name\": \"neon\",\r\n"
				+ "    \"gender\": \"male\",\r\n"
				+ "    \"email\": \""+mail+"\",\r\n"
				+ "    \"status\": \"active\"\r\n"
				+ "}";
	Response response=	restClient.post(BASE_URL_GOREST, GOREST_Users_ENDPONT, userjson, null, null, AuthType.Bearer_Token, ContentType.JSON);
	Assert.assertEquals(response.jsonPath().getString("name"), "neon");
	Assert.assertNotNull(response.jsonPath().getString("id"));
		
	}
	
	@Test
	public void createUserWithJsonFile() throws IOException {
		mail = StringUtil.getRandomEmail();
		File userFile = new File(".\\src\\test\\resources\\jsons\\users.json");
		String rawBody= new String(Files.readAllBytes(Paths.get(".\\src\\test\\resources\\jsons\\users.json")));
		String updateBody=rawBody.replace("{{email}}", mail);
		Response response=	restClient.post(BASE_URL_GOREST, GOREST_Users_ENDPONT, updateBody, null, null, AuthType.Bearer_Token, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), "neon");
		Assert.assertNotNull(response.jsonPath().getString("id"));
	}
	
}
