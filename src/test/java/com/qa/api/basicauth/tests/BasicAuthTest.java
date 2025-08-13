package com.qa.api.basicauth.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;

public class BasicAuthTest  extends BaseTest{

	
	@Test
	public void basicAuthTest() {
		restClient.get(BASIC_AUTH_BASE_URL, BASIC_AUTH_ENDPOINT, null, null, AuthType.BASIC_AUTH, ContentType.JSON);
	}
}
