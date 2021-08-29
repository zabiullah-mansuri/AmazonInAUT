package testcases;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class BaseTestClass {

	WebDriver driver;
	String appUrl = "http://demo.guru99.com/V1/index.php";
	String userId = "mngr350124";
	String password = "udejugy";

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(appUrl);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
