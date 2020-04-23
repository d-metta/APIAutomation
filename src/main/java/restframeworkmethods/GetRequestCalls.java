package restframeworkmethods;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import restframeworkbases.Testbase;

public class GetRequestCalls extends Testbase{
	
	
	public Response getResponse() {
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		httpRequest=RestAssured.given();		
		resposne=httpRequest.request(Method.GET,"/Hyderabad");	
		return resposne;
	}

}
