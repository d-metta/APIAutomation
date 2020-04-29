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
import com.pageobjects.JoinPage;
import com.pageobjects.LoginPage;
import com.pageobjects.PtPage;
import com.pageobjects.WelcomePage;

public class JoinFlowTest extends TestBase {

	HomePage homepage;
	JoinPage joinpage;


	public JoinFlowTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws Exception {
		initialization();
		homepage = new HomePage();
		joinpage=homepage.clickJoinButton();
			}
	
	@Test
	public void JoinPageStep1Test() {
		joinpage.findClub("Boston");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
