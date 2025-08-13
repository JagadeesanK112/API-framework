package com.qa.api.product.test;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidatorUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductApiTestWithJsonPath extends BaseTest{

	@Test
	public void getProducttest() {
	Response response= restClient.get(PRODUCT_BASE_URL, PRODUCT_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.JSON);
	
	List<Double>rate=JsonPathValidatorUtil.readList(response, "$[*].rating.rate");
	System.out.println(rate);
	
	
	List<Map<String, Object>>idList=JsonPathValidatorUtil.readListofMaps(response, "$.[*].['id','title','category','image']");
System.out.println(idList);

double price=JsonPathValidatorUtil.read(response, "min($.[*].price)");
System.out.println(price);

	}
	
	
	
}