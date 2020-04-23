package APIAutomation.APIAutomation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC4_CaptureAllHeaders {
	
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
	 
	//headers has key and value and stores in hasmap
       Headers allHeaders = response.headers();
       
       for(Header header:allHeaders)
       {
    	   System.out.println("Resposne Header "+header.getName()+"    "+header.getValue());
       }
       
       }
	}


