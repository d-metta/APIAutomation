package com.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.base.TestBase;

public class JoinPage extends TestBase {
	
	@FindBy(xpath = "//div[@class='club-listing']/div[1]//select[@class='form-control']")
	WebElement selectRegion;
	
	
	@FindBy(xpath = "//div[@class='club-listing-region']/div[1]//select[@class='form-control']")
	WebElement selectSubRegion;
	

	public JoinPage() {
		PageFactory.initElements(driver, this);
	}

	public JoinPage selectRegion(String region) {
		
		Select select = new Select(selectRegion);
		select.selectByVisibleText(region);
		return this;
	}
	
	public JoinPage selectSubRegion(String subRegion) {
		
		Select select = new Select(selectSubRegion);
		select.selectByVisibleText(subRegion);
		return this;
	}
	
	public JoinPage findClub(String region, String subRegion) {
		selectRegion(region).selectSubRegion(subRegion);
		return this;
	}
	
	public JoinPage findClub(String region) {
		selectRegion(region);
		return this;
	}

}
