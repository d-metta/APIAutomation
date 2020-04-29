package com.testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pageobjects.ClassesPage;
import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;
import com.pageobjects.PtPage;
import com.pageobjects.WelcomePage;

public class WelcomePageTest extends TestBase {

	HomePage homepage;
	LoginPage loginpage;
	WelcomePage welcomepage;
	PtPage ptpage;
	ClassesPage classespage;

	public WelcomePageTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws Exception {
		initialization();
		homepage = new HomePage();
		//homepage.clickClosePopButton();
		loginpage = homepage.clickLoginButton();
		welcomepage = loginpage.clickOnLogInButton(Emailid, "equinox1");
		
		Thread.sleep(2500);
			}

	@Test(priority = 1, description = "TCM_100")
	public void validateWelcomePageTitleTest() throws Exception {
		String actualWelcomePageTitle = welcomepage.getPTPageTitle();
		
		//WebDriverWait wait = new WebDriverWait(driver, 25000);
		//wait.until(ExpectedConditions.titleContains("Equinox Fitness Clubs - It's Not Fitness. It's Life."));
		
		
		for (int i = 0; i < 2; i++) {
			switch (i) {
			case 1:
				Assert.assertEquals(actualWelcomePageTitle, "Equinox Fitness Clubs - It's Not Fitness. It's Life.",
						"WelcomePageTitle Not matched");
			case 2:
				Assert.assertFalse(welcomepage.verifyUserName(), welcomepage.getUserName());
			}
		}

	}

	/*
	 * @Test(priority = 2) public void verifyUserNameTest() {
	 * Assert.assertTrue(welcomepage.verifyUserName(), welcomepage.getUserName()); }
	 */

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
