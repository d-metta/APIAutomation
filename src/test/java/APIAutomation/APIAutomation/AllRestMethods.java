package APIAutomation.APIAutomation;

import java.lang.ProcessHandle.Info;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import bsh.Variable;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AllRestMethods {
	
	@Test
	public static void getWeatherDetailsAndVerifyData()
	{
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
//Object for Request Specification TO Send a request to server
		RequestSpecification httpRequest=RestAssured.given();		
	
//Response Object to store the response
		Response response=httpRequest.request(Method.GET,"/Hyderabad");	
  
//To get the Individual Key values of Response, we have JSOSNpath class
		JsonPath jsonPath=response.jsonPath();
		
		 String city=jsonPath.get("City");
		 System.out.println("City Name is " +city);
	     Assert.assertEquals(city,"Hyderabad");
	     
/////print entire response in window, We need to get the response body as string and put in a object and print
		String responseBody=response.getBody().asString();	
		System.out.println("Response body is " +responseBody);
		
/////To validate if given response is correct or not then First we need get responsebody as string and use contains to check
	       Assert.assertEquals(responseBody.contains("Hyderabad"), true);
	 
//Statuscode Verification		
		int statuscode=response.statusCode();
		Assert.assertEquals(statuscode, 200);
	
//StatusLine Verification response in window		
		String statusline=response.getStatusLine();
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
		
//headers verification
	     String  contentType=response.header("Content-Type");
	   	 System.out.println("Content Type is " +contentType);
	   	 Assert.assertEquals(contentType, "application/json");
	   	 
//Get All Headers using Headers Class and print
	   	   Headers allHeaders = response.headers();
//Using HTTP Header
	       for(Header header:allHeaders)
	       {
	    	   System.out.println("Resposne Header "+header.getName()+"    "+header.getValue());
	       }
	}
	
	@Test
	public static void getafterAuthentication()
	{
		RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
		
	
//Basic Authentication
		PreemptiveBasicAuthScheme authScheme=new PreemptiveBasicAuthScheme();
		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");
 //Need to mention what type of authentication should rest assure need to perform
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
	
	//@Test
	public static void postResitrationSucessful() 
	{
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		RequestSpecification httpRequest=RestAssured.given();
		
//Request Payload with POST Request
		
		JSONObject requestparams=new JSONObject();
		
		requestparams.put("FirstName", "DDDM1XYZ1");
		requestparams.put("LastName", "DDDM1XYZ12");
		requestparams.put("UserName", "JDDDM1XYZ123");
		requestparams.put("Password", "JDDDM1XYZ1234");
		requestparams.put("Email", "JDDDM1XYZ@gmail.com");
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestparams.toJSONString());
		
		Response response=httpRequest.request(Method.POST,"/register");
		
		  
//print response in window - convert jsonresponse as string
		String responseBody=response.getBody().asString();	
		System.out.println("Response body is " +responseBody);
			 
//Statuscode Verification		
		int statuscode=response.statusCode();
		Assert.assertEquals(statuscode, 201);
				
//Statusline Verification	resposne.jsonpath.get("keyname")
		 String sucessCode= response.jsonPath().get("SuccessCode");
		 Assert.assertEquals(sucessCode, "OPERATION_SUCESS");
			
//To extract the reponse of all the nodes	 
		 JsonPath jsonPath=response.jsonPath();
		 String city=jsonPath.get("City");
		 System.out.println("City Name is " +city);
	     Assert.assertEquals(city,"Hyderabad");
	}
	
	//JSON VIEWER
	ObjectMapper objmap=new ObjectMapper();
	String myobjmapString=objmap.writerWithDefaultPrettyPrinter().writeValueAsString(fieldsobject);

}
