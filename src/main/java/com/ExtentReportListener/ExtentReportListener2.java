package com.ExtentReportListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.base.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportListener2 extends TestBase {
	public static ExtentReports extent;
	public static ExtentTest logger;

	public void startReport() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/STMExtentReport3.html", true);
		extent.addSystemInfo("Environment", "Environment Name");
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
		logger = extent.startTest(TCMID + TCM_Description);
		Assert.assertTrue(true);
	}

	public void failLog(String TCMID, String TCM_Description) {
		logger = extent.startTest(TCMID + TCM_Description);
		Assert.assertTrue(false);
	}

	public void skipLog(String TCMID, String TCM_Description) {
		logger = extent.startTest(TCMID + TCM_Description);
		throw new SkipException(TCMID + TCM_Description);
	}

	public void getResult(ITestResult result) throws Exception {
		System.out.println(result);
		System.out.println(result.getName());
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath = ExtentReportListener2.getScreenhot(result.getName());
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

//@aftertest
	public void endReport() {

		extent.flush();
		extent.close();
	}
}