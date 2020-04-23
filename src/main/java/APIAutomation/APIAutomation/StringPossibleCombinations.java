package APIAutomation.APIAutomation;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StringPossibleCombinations {


	// Java program to print all the permutations 
	// of the given string 
	
		@Test
		public static void postResitrationSucessful() 
		{
			RestAssured.baseURI="http://localhost:3000/posts";
			RequestSpecification httpRequest=RestAssured.given();
			
			//Request Payload with POST Request
			
			JSONObject requestparams=new JSONObject();
			requestparams.put("id", "4");
			requestparams.put("title", "json-server4");
			requestparams.put("author", "typicode4");
		
			
			httpRequest.header("Content-Type","application/json");
			
			httpRequest.body(requestparams.toJSONString());
			
			Response response=httpRequest.request(Method.POST,"");
			
			  
					//print response in window
					String responseBody=response.getBody().asString();	
					System.out.println("Response body is " +response);
				 
					
		}
    }
	 

