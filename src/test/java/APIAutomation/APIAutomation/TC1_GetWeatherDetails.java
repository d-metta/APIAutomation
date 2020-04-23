package APIAutomation.APIAutomation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC1_GetWeatherDetails {
	
	@Test
	public static void getWeatherDetails()
	{
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
	//Object for Request Specification - Send a request to server
		RequestSpecification httpRequest=RestAssured.given();		
	
		//Response Object to store the response
		Response response=httpRequest.request(Method.GET,"/Hyderabad");	
  
		//print response in window
		String responseBody=response.getBody().asString();	
		System.out.println("Response body is " +responseBody);
	 
		//Statuscode Verification		
		int statuscode=response.statusCode();
		Assert.assertEquals(statuscode, 200);
	
		//StatusLine Verification response in window		
		String statusline=response.getStatusLine();
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
}
