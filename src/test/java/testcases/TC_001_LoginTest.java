package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;

public class TC_001_LoginTest extends BaseTestClass {
	@Test
	public void loginTest() {
		LoginPage loginPage = new LoginPage(driver);
		log4jlogger.info("URL is opened.");
		loginPage.setUserID(userId);
		log4jlogger.info("Entered UserId : " + userId);
		loginPage.setPassword(password);
		log4jlogger.info("Entered Password : " + password);
		loginPage.clickLogin();
		log4jlogger.info("Clicked Login button.");
		if (driver.getTitle().equals("GTPL Bank Manager HomePage")) {
			log4jlogger.info("Login successful.");
			Assert.assertTrue(true);
		} else {
			log4jlogger.info("Login failed.");
			Assert.assertTrue(false);
		}
	}
}
