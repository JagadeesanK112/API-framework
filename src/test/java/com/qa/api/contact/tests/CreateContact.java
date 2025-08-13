package com.qa.api.contact.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.Contact;
import com.qa.api.pojo.ContactCredentials;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateContact extends BaseTest {
	String token;
@BeforeMethod
	public void getToken() {
		ContactCredentials contact =  ContactCredentials.builder()
				.email("jack@mail.com")
				.password("1234567")
				.build();
		
		
		Response response= restClient.post(CONTACTS_BASE_URL, CONTACTS_LOGIN_ENDPOINT, contact, null, null, AuthType.NO_AUTH, ContentType.JSON);
	token=	response.jsonPath().getString("token");
	System.out.println("Token " +token);
	ConfigManager.set("bearertoken" , token);

	}
	@Test
	public void createContact() {
		
		
		Contact contact = Contact.builder()
				.email(StringUtil.getRandomEmail())
				.firstName("jack")
				.lastName("k")
				.birthdate("1222-09-12")
				.build();
		
		Response response= restClient.post(CONTACTS_BASE_URL, CONTACTS_ENDPOINT, contact, null, null, AuthType.Bearer_Token, ContentType.JSON);
		
	
		
		Assert.assertEquals(response.jsonPath().getString("firstName"),"jack");
		Assert.assertNotNull(response.jsonPath().getString("_id"));
	}

}
