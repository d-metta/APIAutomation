package com.dummy.testcases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.base.TestBase;

public class DummyTest extends TestBase {

	public DummyTest() {
		super();
	}

	@BeforeTest
	public void startExtentReport() {
		startReport();
	}

	@BeforeMethod
	public void setup() {
		initialization();
	}

	@Test(priority = 1)
	public void validateLoginPageTitleTest() throws Exception {

		String actualLoginPageTitle = "My Equinox Login";

		log4jlogger.info("*****STARTED TC-TCM-1010_LoginPage_Title_Verification****");

		if (actualLoginPageTitle.equals("My Equinox Login")) {
			passLog("TCM-1010","LoginPage Title verified sucessfully");
		} else {
			failLog("TCM-1010","LoginPage Title verification failed");
		}
	}


	@Test(priority = 2)
	public void clickOnLogInButtonTest() throws Exception {

		log4jlogger.info("*****STARTED TC-TCM-1011_Click_On_Login_Button_Verification****");
		String actualLoginPageTitle = "My Equinox Logi";

		if (actualLoginPageTitle.equals("My Equinox Login")) {
			passLog("TCM-1011","LoginButton Present and clicked");
		} else {
			failLog("TCM-1011","LoginButton is not Present");
		}
	}

	@Test(priority = 3)
	public void callSkip() throws Exception  {
		log4jlogger.info("*****STARTED TC-TCM-1012_Skipping_Method****");

		skipLog("TCM-1012","Skipping this Method");
	}


	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {

		System.out.println("Test RESULT IS"+ result);
		System.out.println("TestName IS"+ result.getName());
		getResult(result);
		driver.quit();
	}

	@AfterTest
	public void endExtentReport() {
		endReport();
	}
}
