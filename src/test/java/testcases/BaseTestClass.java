package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.ReadConfig;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;

public class BaseTestClass {

	WebDriver driver;
	String appUrl;
	String userId;
	String password;
	ReadConfig readConfig;
	Logger log4jlogger;

	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) {
		readConfig = new ReadConfig();
		appUrl = readConfig.getAppUrl();
		userId = readConfig.getUserId();
		password = readConfig.getPassword();

		log4jlogger = Logger.getLogger("Amazon.in - AUT");
		PropertyConfigurator.configure("log4j.properties");

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConfig.getChromeDriverPath());
			driver = new ChromeDriver();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", readConfig.getIEDriverPath());
			driver = new InternetExplorerDriver();
		} else if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", readConfig.getEdgeeDriverPath());
			driver = new EdgeDriver();
		}

		driver.get(appUrl);

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
