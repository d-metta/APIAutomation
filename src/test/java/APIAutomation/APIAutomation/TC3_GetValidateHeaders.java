package APIAutomation.APIAutomation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC3_GetValidateHeaders {
	
	
	@Test
	public static void getWeatherAndValidate()
	{
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
	//Object for Request Specification - Send a request to server
		RequestSpecification httpRequest=RestAssured.given();		
	
		//Response Object to store the response
		Response response=httpRequest.request(Method.GET,"/Hyderabad");	
  
		//print response in window
		String responseBody=response.getBody().asString();	
		System.out.println("Response body is " +responseBody);
	 
		//Validate Header
       String  contentType=response.header("Content-Type");
   	   System.out.println("Content Type is " +contentType);
   	   Assert.assertEquals(contentType, "application/json");
   	   

	}

}
