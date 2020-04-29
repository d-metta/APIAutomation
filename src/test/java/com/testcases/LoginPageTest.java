package com.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;
import com.pageobjects.WelcomePage;

public class LoginPageTest extends TestBase {

	HomePage homepage;
	LoginPage loginpage;
	WelcomePage welcomepage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();

		homepage = new HomePage();
		// homepage.clickClosePopButton();
		loginpage = homepage.clickLoginButton();

	}

	@Test(priority = 1)
	public void validateLoginPageTitleTest() throws Exception {
		String actualLoginPageTitle = loginpage.getLoginPagePageTitle();

		if (!actualLoginPageTitle.equals("My Equinox Login")) {
			System.out.println("Login Page Title is " + actualLoginPageTitle);
			throw new Exception("Not an appropriate LoginPageTitle");
		}

	}

	@Test(priority = 2)
	public void clickOnLogInButtonTest() {
		// welcomepage = loginpage.clickOnLogInButton(prop.getProperty("us_emailid"),
		// prop.getProperty("us_password"));
		welcomepage = loginpage.clickOnLogInButton(Emailid, "equinox1");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
