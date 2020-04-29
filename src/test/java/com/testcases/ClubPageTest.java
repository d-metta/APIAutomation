package com.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.util.TestUtil;

public class ClubPageTest extends TestBase {

	String sheetName = "Clubs";
	
	public ClubPageTest() {
		super();
	}
	

	@BeforeMethod
	public void setup() {
		initialization();
	}
	
	@DataProvider
	public Object[][] getTestData1() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority = 1, dataProvider = "getTestData1")
	public void clubPageNavigation(String Region,String Subregion,String Country,String ClubPage,String JoinLink,
			String ScreenshotName,String Screenshot_Path) throws Exception {

		System.out.println(ClubPage);
		driver.get(ClubPage);
		
		
		  //// If header is fixed only by css styles i add this code to your test after opening the required page:
		JavascriptExecutor js = (JavascriptExecutor) driver; 

		WebElement header = driver.findElement(By.xpath("//nav[@class='navbar navbar-fixed main-navbar navbar-toggleable-md navbar-dark bg-inverse logged-out']")); 
		js.executeScript("arguments[0].style.visibility='hidden'", header);
	//	js.executeScript("arguments[0].setAttribute('style', 'position: static !important;')",header);
         TestUtil.captureFullPage(ScreenshotName);
		/*
      
       	 ////  If your header is fixed by some javascript, then use this code in your test just after opening the required page:
        	 JavascriptExecutor js = (JavascriptExecutor) driver; 
        	 js.executeScript("document.head.appendChild(document.createElement(\"style\")).innerHTML = \"css-locator-of-your-element {position: static !important; }\"");
		
	*/
		}
	

	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
