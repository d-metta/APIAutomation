package APIAutomation.APIAutomation;

import java.io.IOException;

import org.apache.commons.collections4.functors.AndPredicate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import datadriventesting.TestUtilities;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restframeworkbases.Testbase;


public class ExcelDataExtract extends Testbase{
	

private static String method;
public static RequestSpecification httpRequest;
public static Response resposne;
	
    @DataProvider(name="empdataprovider")
	String [][] getEmpdata() throws IOException
{
	String xpath=System.getProperty("user.dir")+"/src/test/java/TestCases.xlsx";
	
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

    @Test(dataProvider = "empdataprovider")
	public static void postRegistrationProcess(String TCID,String ENDPOINT,String REQUESTMethod,String URI,String BODY)
	{
    	if (method == "GET") {
    		    resposne=httpRequest.request(Method.GET,URI);
    		    System.out.println(resposne);
    		} else if (method == "POST") {
    			resposne=httpRequest.request(Method.POST,URI);
    			 System.out.println(resposne);
    		} else if (method == "PUT"){
    			resposne=httpRequest.request(Method.PUT,URI);
    			 System.out.println(resposne);
    		} else if (method == "DELETE") {
    			resposne=httpRequest.request(Method.DELETE,URI);
    			 System.out.println(resposne);
    		}
	}
    
    

}
