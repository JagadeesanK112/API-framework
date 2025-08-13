package com.qa.api.client;

import java.io.File;

import java.util.Base64;
import java.util.Map;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.APIExceptions;
import com.qa.api.manager.ConfigManager;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;

public class RestClient {
	
	
	private ResponseSpecification responseSpec200 = expect().statusCode(200);
	private ResponseSpecification responseSpec201or200  = expect().statusCode(anyOf(equalTo(201),equalTo(200)));
	private ResponseSpecification responseSpec204 = expect().statusCode(204);
	private ResponseSpecification responseSpec404 = expect().statusCode(404);
	private ResponseSpecification responseSpec400 = expect().statusCode(400);
	private ResponseSpecification responseSpec200or404 = expect().statusCode(anyOf(equalTo(200),equalTo(404)));

	public RequestSpecification setup(String baseUrl,AuthType authtype,ContentType contentType) {
	RequestSpecification request=	RestAssured.given().log().all()
		           .baseUri(baseUrl)
		           .contentType(contentType)
		           .accept(contentType);
	
	switch (authtype) {
	case Bearer_Token:
		request.header("Authorization" ,"Bearer "  + ConfigManager.get("bearertoken"));
		break;
		case OAUTH2:
			request.header("Authorization" , "Bearer " +"token");
			break;
		case BASIC_AUTH:
			request.header("Authorization" , "Basic " + generateBasicAuthToken());
		case API_KEY:
			request.header("x-api-key", "api key");
			break;
		case NO_AUTH:
			System.out.println("Auth is not required...");
			break;
			
		default:
			System.out.println("Authtype not supported");
			throw new APIExceptions("=====Invalid Auth=====");
	
	}
	return request;
	}
	public void applyparams(RequestSpecification request, Map<String, String> queryParams, Map<String, String> pathParams) {
		
		ChainTestListener.log("Queryparam " + queryParams);
		ChainTestListener.log("PathParam "  + pathParams);
		if(queryParams != null) {
			request.queryParams(queryParams);	
		}
		
		if (pathParams != null) {
			request.pathParams(pathParams);
		}
	}
	
	public String generateBasicAuthToken() {
		String Credentials = ConfigManager.get("basic_username")+":"+ConfigManager.get("basic_password");
		return Base64.getEncoder().encodeToString(Credentials.getBytes());
	}
/**
 * Method for calling all get apis
 * @param baseUrl
 * @param endpoint
 * @param queryParams
 * @param pathParams
 * @param authtype
 * @param Contenttype
 * @return
 */
	public Response get(String baseUrl, String endpoint,  Map<String, String> queryParams, Map<String, String> pathParams , AuthType authtype , ContentType Contenttype) {
		
		RequestSpecification request= setup(baseUrl, authtype, Contenttype);
		applyparams(request, queryParams, pathParams);
		Response response= request.get(endpoint).then().spec(responseSpec200or404).extract().response();
		
		response.prettyPrint();
		return response;
	}
	
	
	/**
	 * @param <T>
	 * @param baseUrl
	 * @param endpoint
	 * @param body
	 * @param queryParams
	 * @param pathParams
	 * @param authtype
	 * @param Contenttype
	 * @return
	 */
	public <T> Response post(String baseUrl, String endpoint,T body,
			Map<String, String> queryParams,
			Map<String, String> pathParams ,
			AuthType authtype , 
			ContentType Contenttype  ) {
		
RequestSpecification request =setup(baseUrl, authtype, Contenttype);
 applyparams(request, queryParams, pathParams);
Response response = request.body(body).post(endpoint).then().spec(responseSpec201or200).extract().response();
response.prettyPrint();

return response;
		
	}
	
	
	public Response post(String baseUrl, String endpoint,File file,
			Map<String, String> queryParams,
			Map<String, String> pathParams ,
			AuthType authtype , 
			ContentType Contenttype  ) {
		
RequestSpecification request =setup(baseUrl, authtype, Contenttype);
 applyparams(request, queryParams, pathParams);
Response response = request.body(file).post(endpoint).then().spec(responseSpec201or200).extract().response();
response.prettyPrint();

return response;
		
	}
	
	
public Response post(String baseUrl, String endpoint, ContentType Contenttype, String granttype, String clientId , String clientSecretId  ) {
		
Response response=RestAssured.given()
.contentType(Contenttype)
.formParam("grant_type", granttype)
.formParam("client_id", clientId)
.formParam("client_secret", clientSecretId)
.when()
.post(baseUrl+endpoint);

response.prettyPrint();

return response;
		
	}
	public <T> Response put(String baseUrl, String endpoint,T body,
			Map<String, String> queryParams,
			Map<String, String> pathParams ,
			AuthType authtype , 
			ContentType Contenttype  ) {
		
RequestSpecification request =setup(baseUrl, authtype, Contenttype);
 applyparams(request, queryParams, pathParams);
Response response = request.body(body).put(endpoint).then().spec(responseSpec200).extract().response();
response.prettyPrint();

return response;
		
	}
	
	
	public <T> Response patch(String baseUrl, String endpoint,T body,
			Map<String, String> queryParams,
			Map<String, String> pathParams ,
			AuthType authtype , 
			ContentType Contenttype  ) {
		
RequestSpecification request =setup(baseUrl, authtype, Contenttype);
 applyparams(request, queryParams, pathParams);
Response response = request.body(body).patch(endpoint).then().spec(responseSpec200).extract().response();
response.prettyPrint();

return response;
		
	}
	
	
	public <T> Response delete(String baseUrl, String endpoint,
			Map<String, String> queryParams,
			Map<String, String> pathParams ,
			AuthType authtype , 
			ContentType Contenttype  ) {
		
RequestSpecification request =setup(baseUrl, authtype, Contenttype);
 applyparams(request, queryParams, pathParams);
Response response = request.delete(endpoint).then().spec(responseSpec204).extract().response();
response.prettyPrint();

return response;
		
	}
	}

