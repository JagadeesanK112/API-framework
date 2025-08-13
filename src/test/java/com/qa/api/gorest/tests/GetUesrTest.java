package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Epic("EPIC 100 GO Rest API Get Call")
@Story("US100 API for Getting users")
public class GetUesrTest  extends BaseTest{
	@Description("Getting all users")
	@Owner("me")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void getAllUsersTest() {
		ChainTestListener.log("Get all users");
	Response response=	restClient.get(BASE_URL_GOREST, GOREST_Users_ENDPONT, null, null, AuthType.Bearer_Token, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
	@Description("Getting all users Query Param")
	@Owner("me")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void getallUsersWithQueryParam() {
		
		Map<String,String > queryParams = new HashMap<String, String>();
		
		queryParams.put("name", "ram");
		queryParams.put("status", "inactive");
	Response response=	restClient.get(BASE_URL_GOREST, GOREST_Users_ENDPONT, queryParams, null, AuthType.Bearer_Token, ContentType.JSON);
	Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
	
	@Test
	public void singleUserTest() {
		
		String userid ="7911453";
		Response response= restClient.get(BASE_URL_GOREST, GOREST_Users_ENDPONT +"/"+userid, null, null, AuthType.Bearer_Token, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("id"), "7911453");
	}
	
}
