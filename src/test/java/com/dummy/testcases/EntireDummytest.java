package com.dummy.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.util.WebEventListener;


public class EntireDummytest {
	
	public static WebDriver driver;
	public static Properties prop;
	public static String Emailid;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	protected static WebDriverWait wait;
	public static String Password;
	
	public EntireDummytest() {
		
	
	prop = new Properties();
	try {
		FileInputStream ip = new FileInputStream(
				"C:/Users/divya/eclipse-workspace/DigitalPlatformTest/src/main/java/com/config/properties1");
		prop.load(ip);
	
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}
	
	public void testAgain() {

		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
}
