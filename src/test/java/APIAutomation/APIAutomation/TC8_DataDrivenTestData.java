package APIAutomation.APIAutomation;

import java.io.IOException;

import org.apache.xmlbeans.SystemProperties;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import datadriventesting.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC8_DataDrivenTestData {
	
	
	@Test(dataProvider = "empdataprovider")
	public static void postRegistrationProcess(String ename,String eage,String esal)
	{
	RestAssured.baseURI="http://dummy.restapiexample.com/api.v1";
	RequestSpecification requestSpecification=RestAssured.given();

	JSONObject jsonObject=new JSONObject();
	jsonObject.put("name",ename);
	jsonObject.put("age",eage);
	jsonObject.put("salary",esal);
	requestSpecification.header("Content-Type","Application/json");
	requestSpecification.body(jsonObject.toJSONString());
	
	Response resBody=requestSpecification.request(Method.POST,"/create");
	
	String responseBody=resBody.getBody().asString();	

	System.out.println("Response is "+ responseBody);
	

	   Assert.assertEquals(responseBody.contains(ename), true);
	   Assert.assertEquals(responseBody.contains(esal), true);
	   Assert.assertEquals(responseBody.contains(eage), true);
	//Statuscode Verification		
	int statuscode=resBody.statusCode();
	Assert.assertEquals(statuscode, 201);
	}
	
    @DataProvider(name="empdataprovider")
	String [][] getEmpdata() throws IOException
{
	String xpath=System.getProperty("user.dir")+"/src/test/java/datadriventestdata.xlsx";
	
	int rownumber=TestUtilities.getRowCount(xpath, "Sheet1");
	int colcount=TestUtilities.getCellCount(xpath, "Sheet1", 1);
	
	String empdata[][]=new String[rownumber][colcount];
	
	for (int i = 1; i <= rownumber; i++) {
		for (int j= 0 ; j < colcount; j++ )	{
			empdata[i-1][j]=TestUtilities.getCellData(xpath, "Sheet1", i, j);
		}
	}
	
	return (empdata);
	
}
}
