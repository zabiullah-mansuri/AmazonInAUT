package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.NoSuchElementException;

import pages.HomePage;
import pages.SignInPage;

public class TC_07_VerifyAfterSignOutAccess extends BaseTestClass {

	@Test(enabled = false, description = "Verify : After sign out access")
	public void verifyAfterSignOutAccess() {

		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jlogger.info("Clicked : Account & Lists");

		signInPage.enterUserId(userId);
		log4jlogger.info("Entered user id : " + userId);

		signInPage.clickContinue();
		log4jlogger.info("Clicked : Continue button");

		signInPage.enterPassword(password);
		log4jlogger.info("Entered password : " + password);

		signInPage.clickSignIn();
		log4jlogger.info("Clicked : Sign in button");

		try {
			homePage.getSignedInUserName().contains(name);
			log4jlogger.info("Login successfull.");
		} catch (NoSuchElementException e) {
			log4jlogger.error("Login failed.");
		}
		homePage.clickSignOut();
		log4jlogger.info("Clicked : Sign out");

		driver.navigate().back();
		log4jlogger.info("Navigated: Back");

		driver.navigate().refresh();
		log4jlogger.info("Refreshed page");

		if (homePage.getSignedInUserName().contains(name)) {
			log4jlogger.error("Login still retained.");
			Assert.assertTrue(false);
		} else {
			log4jlogger.info("No access after sign out");
			Assert.assertTrue(true);
		}
	}
}