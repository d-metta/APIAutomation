package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageobjects.BookAClassPage;
import com.pageobjects.ClassesPage;
import com.pageobjects.PtPage;
import com.util.WebEventListener;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class AllTestData {

	public static WebDriver driver;
	public static Properties prop;
	public static String Emailid;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	protected static WebDriverWait wait;
	public static String Password;
	

	public void TestBase() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					"C:/Users/divya/eclipse-workspace/DigitalPlatformTest/src/main/java/com/config/properties");
			prop.load(ip);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void clickAndWait(WebDriverWait wait, WebDriver driver, WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click(); 
		}

	public static void initialization() {

		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		
		String Country = prop.getProperty("country");
		Password="equinox1";

		if (Country.equals("us")) {
			Emailid = "myeqtestuser14@equinox.com";
		} else if (Country.equals("uk")) {
			Emailid = "myeqtestuser20@equinox.com";
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		long time = Long.parseLong(prop.getProperty("explicitwait"));
		wait = new WebDriverWait(driver,time);
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		public static long PAGE_LOAD_TIMEOUT = 20;
		public static long IMPLICIT_WAIT = 10;

		public static String TESTDATA_SHEET_PATH = "C:/Users/divya/eclipse-workspace/DigitalPlatformTest"
				+ "/src/test/java/com/testdata/Club_Join_Urls_Price_Calc_Excel.xlsx";

		static Workbook book;
		static Sheet sheet;

		public void switchTOFrame() {
			driver.switchTo().frame(prop.getProperty("locator"));
		}

		public static Object[][] getTestData(String sheetname) {
			FileInputStream file = null;
			try {
				file = new FileInputStream(TESTDATA_SHEET_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				book = WorkbookFactory.create(file);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetname);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			// System.out.println(sheet.getLastRowNum() + "--------" +
			// sheet.getRow(0).getLastCellNum());
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {

					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					// System.out.println(data[i][k]);
				}
			}
			return data;
		}

		public static void takeScreenshotAtEndOfTest() throws IOException {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");

			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));

		}
		
		public static void captureFullPage(String ScreenshotName) throws IOException{

			Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
			String destination = System.getProperty("user.dir") + "/screenshots/" + ScreenshotName + ".png";
	        ImageIO.write(screenshot.getImage(), "PNG", new File(destination));
		}
		
		
		
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		@FindBy(xpath = "//a[@class='nav-link menu-clubs']")
		WebElement clubsLink;

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
