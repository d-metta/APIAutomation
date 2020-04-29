package com.dummy.testcases;

import java.io.IOException;
import org.testng.annotations.Test;
import com.util.TestUtilities;

@Test
public class ExcelResultStatusUpdate{

	String xlfile=System.getProperty("user.dir")+"/src/test/java/com/testdata/DummyTestCases.xlsx";
	String xlsheet = "TestReport";

	public void excelUpdate() throws IOException {
	
	 //TestUtilities.setCellData(xlfile, xlsheet, 1, 2, "FAIL");
	 TestUtilities.updateResult(xlfile, xlsheet,  "Login Test", "PASS");
	 	
}

}
