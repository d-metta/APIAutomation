package com.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pageobjects.BookAClassPage;
import com.pageobjects.ClassesPage;
import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;
import com.pageobjects.PtPage;
import com.pageobjects.WelcomePage;

public class BookaNonCyclingClassTest extends TestBase {

	HomePage homepage;
	LoginPage loginpage;
	WelcomePage welcomepage;
	PtPage ptpage;
	ClassesPage classespage;
	BookAClassPage bookaclasspage;
	
	public static final String NonCyclingClassType = "Boxing";
	
	

	public BookaNonCyclingClassTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws Exception {
		initialization();
		homepage = new HomePage();
		//homepage.clickClosePopButton();
		loginpage = homepage.clickLoginButton();
		welcomepage = loginpage.clickOnLogInButton(Emailid, "equinox1");
		
		Thread.sleep(3000);
		classespage=welcomepage.clickOnClassesLink();
		bookaclasspage = welcomepage.clickOnBookAClassLink();
		Thread.sleep(3000);
			}

	@Test(priority = 1, description = "TCM-21660",enabled=false)
	public void clickonSaveToCalendarButtonTest() throws InterruptedException  {
		bookaclasspage.clickOnClearLink();
		bookaclasspage.setClassType(NonCyclingClassType);
		bookaclasspage.clickOnFirstClassesSearchItem();
		Boolean flag = bookaclasspage.clickonFirstSaveToCalendarButton();
		Assert.assertTrue( flag,"Failed:The save to calendar button is not displayed");
	}
	

	@Test(priority = 2, description = "TCM-21660",enabled=false)
	public void verifyBookANonCyclingClass() throws InterruptedException  {
		bookaclasspage.clickOnClearLink();
		bookaclasspage.setClassType(NonCyclingClassType);
		bookaclasspage.clickOnFirstClassesSearchItem();
		Boolean flag = bookaclasspage.clickonFirstNonCyclingBookButton();
		Assert.assertTrue( flag,"Failed:The Book a Class button is not displayed");
	}
	
	@Test(priority = 3, description = "TCM-21661",enabled=false)
	public void verifySecondBookingNotAllowedIn24Hours() throws InterruptedException  {
		bookaclasspage.clickOnClearLink();
		bookaclasspage.setClassType(NonCyclingClassType);
		bookaclasspage.clickOnFirstClassesSearchItem();
		Boolean flag = bookaclasspage.clickonSecondNonCyclingBookButtonSameDay();
		Assert.assertTrue( flag,"Failed:The Second Book a Class  button is not displayed");
	}
	
	@Test(priority = 4, description = "TCM-21662")
	public void verifyBookANonCyclingClassNextDay() throws InterruptedException  {
		bookaclasspage.clickOnClearLink();
		bookaclasspage.setClassType(NonCyclingClassType);
		bookaclasspage.clickOnFirstClassesSearchItem();
		bookaclasspage.getNextDayClassesUrlAndNavigate();
		Boolean flag = bookaclasspage.clickonNextDayFirstNonCyclingBookButton();
		Assert.assertTrue( flag,"Failed:The Book a Class button is not displayed");
	}



	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
