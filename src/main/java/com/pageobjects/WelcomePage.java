package com.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.TestBase;

public class WelcomePage extends TestBase {
	@FindBy(xpath = "//a[@class='nav-link menu-clubs']")
	WebElement clubsLink;

	@FindBy(xpath = "//a[@class='nav-link menu-classes']")
	WebElement classesLink;

	@FindBy(xpath = "//a[@class='nav-link menu-personal training']")
	WebElement ptLink;

	@FindBy(xpath = "//a[@class='nav-link menu-pilates']")
	WebElement pilatesLink;

	@FindBy(xpath = "//a[@class='nav-link menu-spa']")
	WebElement spaLink;

	@FindBy(xpath = "//span[text()='BROWSE CLASSES']")
	WebElement browseClassesLink;

	@FindBy(xpath = "//span[text()='BOOK A CLASS']")
	WebElement bookAClassLink;

	@FindBy(xpath = "//span[text()='VIEW CALENDAR']")
	WebElement viewCalendarLink;

	@FindBy(xpath = "//span[@class='user-name']")
	WebElement userName;

	//// li[@class='dropdown']//span[@class='user-name']

	@FindBy(xpath = "//div[@class='collapse navbar-collapse navbar-menu']//a[@href='/account']")
	WebElement accountLink;

	@FindBy(xpath = "//ul[@class='navbar-nav access-links ml-auto']//a[@class='logout-link']")
	WebElement logOutLink;

	public WelcomePage() {
		PageFactory.initElements(driver, this);
	}

	public String getPTPageTitle() {
		return driver.getTitle();
	}

	public Boolean verifyUserName() {
		return userName.isDisplayed();
	}

	public String getUserName() {
		String actualUserName = userName.getText();
		return actualUserName;
	}

	public PtPage clickOnPersonalTrainingLink() {
		ptLink.click();
		return new PtPage();
	}

	public ClassesPage clickOnClassesLink() {
		classesLink.click();
		return new ClassesPage();
	}

	public BookAClassPage clickOnBookAClassLink() {
		bookAClassLink.click();
		return new BookAClassPage();
	}

	public void clickOnWelcomePageLogOutLink() {
		logOutLink.click();
	}

}
