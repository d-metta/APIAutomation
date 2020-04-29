package com.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.TestBase;

public class LoginPage extends TestBase {
	@FindBy(name = "email")
	WebElement emailId;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginButton;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public String getLoginPagePageTitle() {

		return driver.getTitle();
	}

	public WelcomePage clickOnLogInButton(String email, String pwd) {
		emailId.sendKeys(email);
		password.sendKeys(pwd);
		loginButton.click();	
		//clickAndWait(new WebDriverWait(driver,10), driver, loginButton);
		return new WelcomePage();

	}

}
