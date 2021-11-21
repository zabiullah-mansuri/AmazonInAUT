package testcases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class TC_07_VerifyAfterSignOutAccess extends BaseTestClass {

	@Test(enabled = true, description = "Verify : After sign out access")
	public void verifyAfterSignOutAccess() {

		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jLogger.info("Clicked : Account & Lists");

		signInPage.enterUserId(userId);
		log4jLogger.info("Entered user id : " + userId);

		signInPage.clickContinue();
		log4jLogger.info("Clicked : Continue button");

		signInPage.enterPassword(password);
		log4jLogger.info("Entered password : " + password);

		signInPage.clickSignIn();
		log4jLogger.info("Clicked : Sign in button");

		try {
			homePage.getSignedInUserName().contains(name);
			log4jLogger.info("Login successfull.");
		} catch (NoSuchElementException e) {
			log4jLogger.error("Login failed.");
		}

		log4jLogger.info("Clicking : Sign out");
		while (!homePage.clickSignOut()) {
			log4jLogger.info("Clicking again : Sign out");
		}

		driver.navigate().back();
		log4jLogger.info("Navigated: Back");

		driver.navigate().refresh();
		log4jLogger.info("Refreshed page");

		if (homePage.getSignedInUserName().contains(name)) {
			log4jLogger.error("Login still retained.");
			Assert.assertTrue(false);
		} else {
			log4jLogger.info("No access after sign out");
			Assert.assertTrue(true);
		}
	}
}