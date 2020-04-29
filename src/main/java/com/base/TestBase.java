package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.util.TestUtil;
import com.util.TestUtilities;
import com.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static String Emailid;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	protected static WebDriverWait wait;
	public static String Password;

	String xlfile=System.getProperty("user.dir")+"/src/test/java/com/testdata/DummyTestCases.xlsx";
	String xlsheet = "TestReport";
	public static Logger log4jlogger = Logger.getLogger("RestAssuredFramework");//added logger
	//url=https://Devpreview:Equinox1!@stag.equinox.com

	public TestBase() {
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

	@BeforeClass
	public void logger()
	{
		log4jlogger=Logger.getLogger("DigitalPlatformTest");//added logger
		//PropertyConfigurator.configure("C:/Users/divya/eclipse-workspace/DigitalPlatformTest/src/main/resource/log4j.properties");
		BasicConfigurator.configure();
		log4jlogger.setLevel(Level.DEBUG);
	}

	public static void clickAndWait(WebDriverWait wait, WebDriver driver, WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click(); 
	}

	
	public static void initialization()  {
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
	}

	public static ExtentReports extent;
	public static ExtentTest logger;

	public void startReport() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/STMExtentReport4.html", true);
		extent.addSystemInfo("Environment", "Environment Name");
		//logger = extent.startTest("Description");
	}

	public static String getScreenhot(String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		System.out.println(screenshotName);
		TakesScreenshot ts = (TakesScreenshot) driver;
		System.out.println(ts.getScreenshotAs(OutputType.FILE));
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public void passLog(String TCMID, String TCM_Description) throws Exception {
		logger = extent.startTest(TCMID + ":" + TCM_Description);
		TestUtilities.updateResult(xlfile, xlsheet, TCMID, "PASS");
		Assert.assertTrue(true);
	}

	public void failLog(String TCMID, String TCM_Description) throws IOException {
		logger = extent.startTest(TCMID + ":" + TCM_Description);
		TestUtilities.updateResult(xlfile, xlsheet, TCMID, "FAIL");
		Assert.assertTrue(false);
	}

	public void skipLog(String TCMID, String TCM_Description) throws IOException {
		logger = extent.startTest(TCMID + ":" + TCM_Description);
		TestUtilities.updateResult(xlfile, xlsheet, TCMID, "SKIPPED");
		throw new SkipException(TCMID + TCM_Description);
	}

	public void getResult(ITestResult result) throws Exception {
		System.out.println(result);
		System.out.println(result.getName());

		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath = TestBase.getScreenhot(result.getName());
			System.out.println(screenshotPath);
			// To add it in the extent report
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test Case PASSED " + result.getName());

		}

		extent.endTest(logger);
	}

	public void endReport() {
		extent.flush();
		extent.close();
	}
}
