package com.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//a[@class='icon-close']")
	WebElement closeButton;

	@FindBy(xpath = "//nav[@id='main-navbar']//a[@class='highlight menu-log in']")
	WebElement loginButton;
	
	
	@FindBy(xpath = "//nav[@id='main-navbar']//a[@class='highlight menu-join']")
	WebElement joinButton;
	
	@FindBy(xpath = "//a[@class='nav-link menu-clubs']")
	WebElement clubsButton;

	/* Initialize Page Objects using PageFactory in this constructor */
	public HomePage() {
		PageFactory.initElements(driver, this);

	}

	public String getHomePagePageTitle() {

		return driver.getTitle();
	}

	public boolean validateClosePopButton() {

		return closeButton.isDisplayed();
	}

	public boolean validateLoginLink() {

		return loginButton.isDisplayed();
	}

	public void clickClosePopButton() {

		if (closeButton.isDisplayed()) {
			closeButton.click();
		}
	}

	public LoginPage clickLoginButton() {

		loginButton.click();
		return new LoginPage();
	}
	
	public JoinPage clickJoinButton() {
		joinButton.click();
		return new JoinPage();
	}
	
	public ClubPage clickClubsButton() {
		clubsButton.click();
		return new ClubPage();
	}

}
