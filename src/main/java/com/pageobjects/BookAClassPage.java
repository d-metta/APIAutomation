package com.pageobjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.base.TestBase;

public class BookAClassPage extends TestBase {

	@FindBy(xpath = "//li[@class='clear-filters']")
	WebElement clearLink;

	@FindBy(xpath = "//*[@id=\"filter-edit\"]/div/ul/li[3]/div/div/input")
	WebElement ClassesPlaceHolder;

	@FindBy(xpath = ".//li[@data-autocomplete='classes']//ul[@class='resultFoundAtBeginning']/li[1]")
	WebElement ClassesDropdownFirstElement;

	@FindBy(xpath = ".//*[normalize-space(text())='Save to Calendar']")
	WebElement SaveToCalenderButton;

	@FindBy(xpath = ".//nav[@class='buttons']//*[normalize-space(text())='Remove From Calendar']")
	WebElement RemoveFromCalenderButton;

	@FindBy(xpath = ".//*[normalize-space(text())='Book Class']")
	WebElement BookClassButton;

	public static final String ListOfBookButtons = ".//div[@class='option-button']//span[text()='Book']/ancestor::a";
	public static final String ListOfSaveToCalenderButtons = ".//div[@class='option-button']//span[text()='Save to Calendar']/ancestor::a";

	public BookAClassPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnClearLink() {
		wait.until(ExpectedConditions.elementToBeClickable(clearLink));
		clearLink.click();
	}

	public void setClassType(String type) {
		ClassesPlaceHolder.sendKeys(type);
	}

	public void clickOnFirstClassesSearchItem() {
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
				By.xpath(".//li[@data-autocomplete='classes']//ul[@class='resultFoundAtBeginning']/li"), 1));
		ClassesDropdownFirstElement.click();
	}

	public Boolean clickonFirstSaveToCalendarButton() {
		List<WebElement> bookButtons = driver.findElements(By.xpath(ListOfSaveToCalenderButtons));
		System.out.println(bookButtons.size());
		for (WebElement bookButton : bookButtons) {
			System.out.println(bookButton.getAttribute("href"));
		}
		WebElement book = bookButtons.get(0);
		String href = book.getAttribute("href");
		System.out.println(href);
		driver.navigate().to(href);
		SaveToCalenderButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(By.xpath(".//nav[@class='buttons']//*[normalize-space(text())='Remove From Calendar']"))));
		List<WebElement> buttons = driver.findElements(By.xpath(".//nav[@class='buttons']"));
		String expecteButton = "Remove From Calendar";
		Boolean flag = false;
		for (WebElement button : buttons) {
			String actualButton = button.findElement(By.tagName("a")).getText().trim();
			System.out.println("*******************************************" + actualButton);
			if (actualButton.equalsIgnoreCase(expecteButton)) {
				flag = true;
				break;
			}
		}

		return flag;
	}

	public Boolean clickonFirstNonCyclingBookButton() {
		List<WebElement> bookButtons = driver.findElements(By.xpath(ListOfBookButtons));
		System.out.println(bookButtons.size());
		for (WebElement bookButton : bookButtons) {
			System.out.println(bookButton.getAttribute("href"));
		}
		WebElement book = bookButtons.get(0);
		String href = book.getAttribute("href");
		System.out.println(href);
		driver.navigate().to(href);
		BookClassButton.click();
		Boolean isBooked = wait.until(ExpectedConditions.textToBePresentInElement(
				driver.findElement(By.xpath(".//span[@class='medium-font bike-number']")), "BOOKED"));
		return isBooked;
	}

	public Boolean clickonSecondNonCyclingBookButtonSameDay() {
		List<WebElement> bookButtons = driver.findElements(By.xpath(ListOfBookButtons));
		System.out.println(bookButtons.size());
		for (WebElement bookButton : bookButtons) {
			System.out.println(bookButton.getAttribute("href"));
		}
		WebElement book = bookButtons.get(0);
		String href = book.getAttribute("href");
		System.out.println(href);
		driver.navigate().to(href);
		BookClassButton.click();
		Boolean isBookedAlready = wait.until(ExpectedConditions.textToBePresentInElement(
				driver.findElement(By.xpath(".//div[@class='overlay-box active']/span")),
				"You may only book 1 class per class category per day. You may still sign up for the class in-club 30 minutes before class begins."
						.toUpperCase()));
		return isBookedAlready;
	}

	public void getNextDayClassesUrlAndNavigate(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		String nextDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		// GET THE URL
		String currentDayUrl = driver.getCurrentUrl();
		String url = currentDayUrl + "&date=" + nextDate;
		System.out.println(url);
		// navigate
		driver.navigate().to(url);
	}
	
	public Boolean clickonNextDayFirstNonCyclingBookButton() {
		List<WebElement> bookButtons = driver.findElements(By.xpath(ListOfBookButtons));
		System.out.println(bookButtons.size());
		for (WebElement bookButton : bookButtons) {
			System.out.println(bookButton.getAttribute("href"));
		}
		WebElement book = bookButtons.get(0);
		String href = book.getAttribute("href");
		System.out.println(href);
		driver.navigate().to(href);
		BookClassButton.click();
		Boolean isBooked = wait.until(ExpectedConditions.textToBePresentInElement(
				driver.findElement(By.xpath(".//span[@class='medium-font bike-number']")), "BOOKED"));
		return isBooked;

	}
}
