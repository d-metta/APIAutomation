package APIAutomation.APIAutomation;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC7_DataProviderTestData {
	
	@Test (dataProvider ="empdataprovider" )
	public static void postResitrationSucessful(String ename,String eage,String esal) 
	{
		RestAssured.baseURI="http://dummy.restapiexample.com/api.v1";
		RequestSpecification httpRequest=RestAssured.given();
		
		//Request Payload with POST Request
		
		JSONObject requestparams=new JSONObject();
		requestparams.put("name", ename);
		requestparams.put("age", eage);
		requestparams.put("salary", esal);

		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestparams.toJSONString());
		
		Response response=httpRequest.request(Method.POST,"/create");
	
		//print response in window - convert jsonresponse as string
				String responseBody=response.getBody().asString();	

		//
		   Assert.assertEquals(responseBody.contains(ename), true);
		   Assert.assertEquals(responseBody.contains(eage), true);
		   Assert.assertEquals(responseBody.contains(ename), true);
		//Statuscode Verification		
		int statuscode=response.statusCode();
		Assert.assertEquals(statuscode, 201);
				
				
		         
	}
	
	@DataProvider(name="empdataprovider")
	String [][] getEmpData()
	{
		String empdata[][]= {{"abc123","30","30000"},{"xyz123","40","40000"}};
		return empdata;
		
	}
}



