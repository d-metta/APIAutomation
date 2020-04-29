package com.testcases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;
import com.pageobjects.WelcomePage;

public class LoginPageTest2 extends TestBase {

	
	//ExtentReportListener2 Ext = new ExtentReportListener2();

	HomePage homepage;
	LoginPage loginpage;
	WelcomePage welcomepage;

	public LoginPageTest2() {
		super();
	}

	@BeforeTest
	public void startExtentReport() {

		//Ext.startReport();
		//startReport();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		homepage = new HomePage();
		loginpage = homepage.clickLoginButton();

	}

	@Test(priority = 1)
	public void validateLoginPageTitleTest() throws Exception {
		String actualLoginPageTitle = loginpage.getLoginPagePageTitle();
		System.out.println("Login Page Title is " + actualLoginPageTitle);

		if (actualLoginPageTitle.equals("My Equinox Login")) {
			//Ext.passLog("TCM-1010:","LoginPage Title verified sucessfully");
			passLog("TCM-1010:","LoginPage Title verified sucessfully");
		} else {
			//Ext.failLog("TCM-1010:","LoginPage Title verification failed");
			failLog("TCM-1010:","LoginPage Title verification failed");
		}

	}

	@Test(priority = 2,enabled=false)
	public void validateLoginPageTitleTest2() throws Exception {
		String actualLoginPageTitle = loginpage.getLoginPagePageTitle();
		System.out.println("Login Page Title is " + actualLoginPageTitle);

		if (actualLoginPageTitle.equals("My Equinox Login.")) {
			//Ext.passLog("TCM-1010:","LoginPage Title verified sucessfully");
			//passLog("TCM-1010:","LoginPage Title verified sucessfully");
		} else {
			//Ext.failLog("TCM-1010:","LoginPage Title verification failed");
			//failLog("TCM-1010:","LoginPage Title verification failed");
		}

	}
	
	@Test(priority = 3,enabled=false)
	public void callSkip() throws Exception {
		//Ext.skipLog("TCM-1010:","LoginPage Title verification skipped");
		//skipLog("TCM-1010:","LoginPage Title verification skipped");
	}

	@Test(priority = 2, enabled = false)
	public void clickOnLogInButtonTest() {
		welcomepage = loginpage.clickOnLogInButton(Emailid, Password);

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		System.out.println(result);
		//Ext.getResult(result);
		getResult(result);
		driver.quit();
	}

	@AfterTest
	public void endExtentReport() {
		//Ext.endReport();
         endReport();
	}
}
