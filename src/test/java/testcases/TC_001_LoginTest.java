package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;

public class TC_001_LoginTest extends BaseTestClass {
	@Test
	public void f() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserID(userId);
		loginPage.setPassword(password);
		loginPage.clickLogin();
		if (driver.getTitle().equals("GTPL Bank Manager HomePage")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}
}
