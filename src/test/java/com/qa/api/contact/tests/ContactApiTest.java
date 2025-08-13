package com.qa.api.contact.tests;

import java.util.List;

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

public class ContactApiTest extends BaseTest {
String token;
String mail;
int  count =0;
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
public void getAllContacts() {
	
	Response response=restClient.get(CONTACTS_BASE_URL, CONTACTS_ENDPOINT, null, null, AuthType.Bearer_Token, ContentType.JSON);
List<String> contactids=response.jsonPath().getList("_id");

for(String id :contactids) {
	System.out.println(id);
	 count = count+1;
	
	
}
System.out.println("total users: " +count);
}


}	

