package com.qa.api.reqres;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest {

	
	@Test
	public void getUserTest() {
		Map<String,String> queryParam = new HashMap<String,String>();
		queryParam.put("page","2");
		 Response response=restClient.get(REQRES_BASE_URL, REQRES_ENDPOINT,queryParam, null, AuthType.NO_AUTH, ContentType.JSON);
		 
	}
}
