package com.qa.api.product.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductApiTest extends BaseTest {
@Test
	public void getProducts() {
		Response response= restClient.get(PRODUCT_BASE_URL, PRODUCT_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.JSON);
		
		Assert.assertEquals(response.statusCode(), 200);
		
	Product[]product=JsonUtil.deserialise(response, Product[].class);
	
	
	for (Product p : product) {
		System.out.println(p.getId());
		System.out.println(p.getImage());
		System.out.println(p.getRating().getRate());
	
	}
	}
	
}
