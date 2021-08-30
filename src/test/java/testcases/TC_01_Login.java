package testcases;

import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class TC_01_Login extends BaseTestClass {
	@Test(priority = 1, enabled = true)
	public void loginValid() {
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jlogger.info("Clicked : Account & Lists");
		signInPage.enterUserId(userId);
		log4jlogger.info("Entered user id : " + userId);
		signInPage.clickContinue();
		signInPage.enterPassword(password);
		log4jlogger.info("Entered password : " + password);
		signInPage.clickSignIn();
		log4jlogger.info("Clicked : Sign in button");
		try {
			homePage.getSignedInUserName().contains(name);
			log4jlogger.info("Login successfull.");
			Assert.assertTrue(true);
		} catch (NoSuchElementException e) {
			captureScreenshot(driver, "loginValid");
			log4jlogger.error("Login failed.");
			Assert.assertTrue(false);
		} finally {
			driver.quit();
		}
	}

	@Test(priority = 2, enabled = true)
	public void loginInvalid() {
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jlogger.info("Clicked : Account & Lists");
		signInPage.enterUserId(userId);
		log4jlogger.info("Entered user id : " + userId);
		signInPage.clickContinue();
		signInPage.enterPassword(wrongPassword);
		log4jlogger.info("Entered password : " + wrongPassword);
		signInPage.clickSignIn();
		log4jlogger.info("Clicked : Sign in button");
		if (signInPage.isWrongPasswordMsgPresent()) {
			log4jlogger.info("Error msg present on invalid login.");
			Assert.assertTrue(true);
		} else {
			captureScreenshot(driver, "loginValid");
			log4jlogger.info("Error msg not present on invalid login.");
			Assert.assertTrue(false);
		}
		driver.quit();

	}

}
