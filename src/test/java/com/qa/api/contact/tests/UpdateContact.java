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

public class UpdateContact extends BaseTest {
	String userid;
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
	public void updateContact() {
		
		Contact contact = Contact.builder()
				.email(StringUtil.getRandomEmail())
				.firstName("jack")
				.lastName("k")
				.birthdate("1222-09-12")
				.build();
		
		Response responsePost= restClient.post(CONTACTS_BASE_URL, CONTACTS_ENDPOINT, contact, null, null, AuthType.Bearer_Token, ContentType.JSON);
		Assert.assertEquals(responsePost.jsonPath().getString("firstName"),"jack");
		Assert.assertNotNull(responsePost.jsonPath().getString("_id"));
		userid = responsePost.jsonPath().getString("_id");
	
		
	Response responseGet=	restClient.get(CONTACTS_BASE_URL, CONTACTS_ENDPOINT + "/" + userid , null, null, AuthType.Bearer_Token, ContentType.JSON);
	Assert.assertEquals(responseGet.jsonPath().getString("firstName"),"jack");
	Assert.assertNotNull(responseGet.jsonPath().getString("_id"));
		
	
	
	contact.setFirstName("Mack");
	contact.setBirthdate("1972-09-11");
	
	 Response responseput=restClient.put(CONTACTS_BASE_URL, CONTACTS_ENDPOINT+"/"+userid,contact, null, null, AuthType.Bearer_Token, ContentType.JSON);
	
	 Assert.assertEquals(responseput.jsonPath().getString("firstName"),"Mack");
	Assert.assertEquals(responseput.jsonPath().getString("birthdate"), "1972-09-11");
	
	
	
	
	 responseGet=restClient.get(CONTACTS_BASE_URL, CONTACTS_ENDPOINT+"/"+userid,null, null, AuthType.Bearer_Token, ContentType.JSON);
	 
	Assert.assertEquals(responseGet.jsonPath().getString("firstName"), "Mack");
	Assert.assertEquals(responseput.jsonPath().getString("birthdate"), "1972-09-11");
		
	}
	
}
