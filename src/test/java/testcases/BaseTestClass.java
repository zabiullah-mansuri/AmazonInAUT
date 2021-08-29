package testcases;

import org.testng.annotations.BeforeClass;

import utilities.ReadConfig;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class BaseTestClass {

	WebDriver driver;
	String appUrl;
	String userId;
	String password;
	ReadConfig readConfig;
	Logger log4jlogger;

	@BeforeClass
	public void setup() {
		readConfig = new ReadConfig();
		appUrl = readConfig.getAppUrl();
		userId = readConfig.getUserId();
		password = readConfig.getPassword();

		System.setProperty("webdriver.chrome.driver", readConfig.getChromeDriverPath());
		driver = new ChromeDriver();
		driver.get(appUrl);

		log4jlogger = Logger.getLogger("Amazon.in - AUT");
		PropertyConfigurator.configure("log4j.properties");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
