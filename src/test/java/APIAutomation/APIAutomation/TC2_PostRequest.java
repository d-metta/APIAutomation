package APIAutomation.APIAutomation;



import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC2_PostRequest {
	
	@Test
	public static void postResitrationSucessful() 
	{
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		RequestSpecification httpRequest=RestAssured.given();
		
		//Request Payload with POST Request
		
		JSONObject requestparams=new JSONObject();
		requestparams.put("FirstName", "DDM1XYZ1");
		requestparams.put("LastName", "DDM1XYZ12");
		requestparams.put("UserName", "JDDM1XYZ123");
		requestparams.put("Password", "JDDM1XYZ1234");
		requestparams.put("Email", "JDDM1XYZ@gmail.com");
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestparams.toJSONString());
		
		Response response=httpRequest.request(Method.POST,"/register");
		
		  
				//print response in window
				String responseBody=response.getBody().asString();	
				System.out.println("Response body is " +responseBody);
			 
				//Statuscode Verification		
				int statuscode=response.statusCode();
				Assert.assertEquals(statuscode, 201);
				
				
		         String sucessCode= response.jsonPath().get("SuccessCode");
		         Assert.assertEquals(sucessCode, "OPERATION_SUCESS");
	}
}
