package APIAutomation.APIAutomation;


import io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

import org.testng.annotations.Test;



public class RestTestOne{
	
	@Test
	public static void restAPIGet() throws Exception {
		
		given()
		.when()
		 .get("http://restapi.demoqa.com/utilities/weather/city/hyderabad")
		 .then()
		 .statusCode(200)
		 .statusLine("HTTP/1.1 200 OK")	
		 .assertThat().body("City",equalTo("Hyderabad"))
		 .log().all()		 ;
		     
		

	}
	
}

