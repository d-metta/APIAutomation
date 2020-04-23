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

public class TC9_DataDrivenTestData2 {
	
	static String xpath=System.getProperty("user.dir")+"/src/test/java/datadriventestdata.xlsx";

	
	@Test(dataProvider = "empdataprovider")
	public static void postRegistrationProcess(String ename,String eage,String esal) throws IOException
	{
	RestAssured.baseURI="http://localhost:3000/posts";
	RequestSpecification requestSpecification=RestAssured.given();
    
	
	JSONObject jsonObject=new JSONObject();
	jsonObject.put("id",ename);
	jsonObject.put("title",eage);
	jsonObject.put("author",esal);
	requestSpecification.header("Content-Type","Application/json");
	requestSpecification.body(jsonObject.toJSONString());
	
	  int emprow =TestUtilities.getRowCount(xpath, "Sheet2");
	  int empcol=TestUtilities.getCellCount(xpath, "Sheet2", emprow);

	  
	Response resBody=requestSpecification.request(Method.POST,"");
	
	
	String responseBody=resBody.getBody().asString();	

	System.out.println("Response is "+ responseBody);
	

	   Assert.assertEquals(responseBody.contains(ename), true);
	   Assert.assertEquals(responseBody.contains(esal), true);
	   Assert.assertEquals(responseBody.contains(eage), true);
	   
		 //TestUtilities.setCellData(xpath, "Sheet2", emprow+1, emprow, "test");
	//Statuscode Verification		
	 
	 System.out.println(emprow);
	 System.out.println(empcol);
	 
	int statuscode=resBody.statusCode();
	Assert.assertEquals(statuscode, 201);
	
	
	}
	
    @DataProvider(name="empdataprovider")
	String [][] getEmpdata() throws IOException
{
	
    	    	
	int rownumber=TestUtilities.getRowCount(xpath, "Sheet2");
	int colcount=TestUtilities.getCellCount(xpath, "Sheet2", 1);
	
	String empdata[][]=new String[rownumber][colcount];
	
	for (int i = 1; i <= rownumber; i++) {
		for (int j= 0 ; j < colcount; j++ )	{
			empdata[i-1][j]=TestUtilities.getCellData(xpath, "Sheet2", i, j);
			System.out.println(empdata);
		}
	}
	
	return (empdata);
	
}
}
