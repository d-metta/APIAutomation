
package restframeworkbases;



import io.restassured.*;
import io.restassured.RestAssured.*;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.annotations.BeforeClass;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//TestBase: Common Variables and Logger

public class Testbase {


public static RequestSpecification httpRequest;
public static Response resposne;
private static String authenticationToken;
public String empid="";


public Logger logger;


public void logger()
{
	logger=Logger.getLogger("APIAutomation");//added logger
	PropertyConfigurator.configure("Log4j.properties");
	logger.setLevel(Level.DEBUG);

}


public static ExtentReports extent;
public static ExtentTest logger1;

public void startReport() {
	extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/STMExtentReport3.html", true);
	extent.addSystemInfo("Environment", "Environment Name");
}

	
	//CUSTOM CODE TO set the time to execute the api until we get the api response instead of Thread.sleep
		public String waitUntilStringFoundInResponse(String basepath, String findMe, int TIMEOUT) throws Exception {
		    String responseBody = null;
		 		
		    int i = 0;
		    
			while (i < TIMEOUT) {
				resposne = httpRequest.request(Method.GET,basepath);
		        //result = postHttpsRequest(apiPath, parameters);
				 responseBody=resposne.getBody().asString();
				
		        if (responseBody.contains(findMe)) {
		            break;
		        } else {
		            TimeUnit.SECONDS.sleep(1);
		            ++i;
		            if (i == TIMEOUT) {
		                throw new TimeoutException("Timed out after waiting for " + i + " seconds");
		            }
		        }
		    }
		    return responseBody;
		}
		
		
		public String tokenGenerator() {
			   authenticationToken = resposne.jsonPath().get("accessToken");
			   return authenticationToken;
					   
		}
		
		
		public Response getResponse() {
			RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
			httpRequest=RestAssured.given();		
			resposne=httpRequest.request(Method.GET,"/Hyderabad");	
			return resposne;
		}


	
}
