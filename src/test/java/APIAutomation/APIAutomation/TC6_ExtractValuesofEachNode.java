package APIAutomation.APIAutomation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC6_ExtractValuesofEachNode {
	
	@Test
	public static void getWeatherAndValidate()
	{
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
	//Object for Request Specification - Send a request to server
		RequestSpecification httpRequest=RestAssured.given();		
	
		//Response Object to store the response
		Response response=httpRequest.request(Method.GET,"/Hyderabad");	
  
	
	 JsonPath jsonPath=response.jsonPath();
	 String city=jsonPath.get("City");
	 System.out.println("City Name is " +city);
     Assert.assertEquals(city,"Hyderabad");
   	   

	}
}
