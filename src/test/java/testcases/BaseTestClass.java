package testcases;

import org.testng.annotations.BeforeClass;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class BaseTestClass {

	WebDriver driver;
	String appUrl = "http://demo.guru99.com/V1/index.php";
	String userId = "mngr350124";
	String password = "udejugy";
	Logger log4jlogger;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
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
