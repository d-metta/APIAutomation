package APIAutomation.APIAutomation;

import org.apache.http.auth.AuthScheme;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RSGetBasicAuthentication {
	
	@Test
	public static void getafterAuthentication()
	{
		RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
		
	
	//Basic Authentication
		PreemptiveBasicAuthScheme authScheme=new PreemptiveBasicAuthScheme();
		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");
	 
		RestAssured.authentication=authScheme;
	 
	//Authorization key as Bearer and Bearer token 
        //String bearerToken = null;
		//httpRequest.headers("Authorization","Bearer "+bearerToken);
		
	//Object for Request Specification - Send a request to server
		RequestSpecification httpRequest=RestAssured.given();		
	
	//Response Object to store the response
		Response response=httpRequest.request(Method.GET,"/");	

	
		//Statuscode Verification		
		int statuscode=response.statusCode();
		Assert.assertEquals(statuscode, 200);
   	   
		//print response in window
		String responseBody=response.getBody().asString();	
		System.out.println("Response body is " +responseBody);
	}
}
