package com.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;

public class HomePageTest extends TestBase {

	HomePage homepage;
	LoginPage loginpage;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		initialization();
		homepage = new HomePage();
	}

	@Test(priority = 1)

	public void validateHomePageTitleTest() {

		String actualHomePageTitle = homepage.getHomePagePageTitle();
		Assert.assertEquals(actualHomePageTitle, "Fitness Clubs, Luxury Gym, Workout Clubs - Equinox");

	}

	@Test(priority = 2)
	public void validateClosePopButtonTest() {

		boolean closeButtonFlag = homepage.validateClosePopButton();
		Assert.assertTrue(closeButtonFlag);

	}

	@Test(priority = 3)
	public void validateLoginLinkTest() {

		boolean loginLinkFlag = homepage.validateLoginLink();
		Assert.assertTrue(loginLinkFlag);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}
