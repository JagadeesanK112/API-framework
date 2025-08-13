package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import io.qameta.allure.restassured.AllureRestAssured;

import com.qa.api.client.RestClient;

import io.restassured.RestAssured;

public class BaseTest {
 protected RestClient restClient;
 
	//=================BaseUrl======================//
	protected final static String BASE_URL_GOREST="https://gorest.co.in/";
	protected final static String CONTACTS_BASE_URL="https://thinking-tester-contact-list.herokuapp.com";
	protected final static String REQRES_BASE_URL="https://reqres.in";
	protected final static String BASIC_AUTH_BASE_URL="https://the-internet.herokuapp.com";
	protected final static String PRODUCT_BASE_URL="https://fakestoreapi.com";
	protected final static String BASE_URL_OAUTH2_AMANDEUS="https://test.api.amadeus.com";
	
	//=====================Endpoints===============//
	protected final static String GOREST_Users_ENDPONT="public/v2/users";
	protected final static String CONTACTS_LOGIN_ENDPOINT="/users/login";
	protected final static String CONTACTS_ENDPOINT="/contacts";
	protected final static String REQRES_ENDPOINT="/api/users";
	protected final static String BASIC_AUTH_ENDPOINT= "/basic_auth";
	protected final static String PRODUCT_ENDPOINT="/products";
	protected final static String AMANDEUS_OAUTH2_ENDPOINT="/v1/security/oauth2/token";
	protected final static String AMANDEUS_FLIGHT_DESTINATION="/v1/shopping/flight-destinations";
	
	
	@BeforeSuite
	public void setupAllureReport() {
		RestAssured.filters(new AllureRestAssured());
	}
	
	@BeforeTest
	public void setup() {
		restClient =new RestClient();
	}
}
