package restframeworktests;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restframeworkbases.Testbase;
import restframeworkmethods.GetRequestCalls;

public class TC1RS_GetWeatherDetails extends Testbase{
	

	GetRequestCalls getRequestCalls = new GetRequestCalls();
	
	@Test
	public void validateResponseStatusCode() {
		//String responseBody=resposne.getBody().asString();
		String respBody=getRequestCalls.getResponse().getBody().asString();
		int statuscode=resposne.statusCode();
		Assert.assertEquals(statuscode, 200);
		System.out.println("Response body is " +respBody);
		 
	}

}
