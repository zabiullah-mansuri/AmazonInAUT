package testcases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class TC_01_Login extends BaseTestClass {

	@Test(priority = 1, enabled = false)
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
		}
	}

	@Test(priority = 2, enabled = false)
	public void loginInvalidId() {
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jlogger.info("Clicked : Account & Lists");
		signInPage.enterUserId(wrongUserId);
		log4jlogger.info("Entered user id : " + wrongUserId);
		signInPage.clickContinue();

		log4jlogger.info("Clicked : Continue button");
		if (signInPage.getAuthErrorMsg().contains("We cannot find an account with that email address")) {
			log4jlogger.info("Error msg present on invalid login.");
			Assert.assertTrue(true);
		} else {
			captureScreenshot(driver, "loginInvalidId");
			log4jlogger.info("Error msg not present on invalid login.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 3, enabled = false)
	public void loginInvalidPassword() {
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
		if (signInPage.getAuthErrorMsg().contains("Your password is incorrect")) {
			log4jlogger.info("Error msg present on invalid login.");
			Assert.assertTrue(true);
		} else {
			captureScreenshot(driver, "loginInvalidPassword");
			log4jlogger.info("Error msg not present on invalid login.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 4, enabled = false)
	public void loginBlankId() {
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jlogger.info("Clicked : Account & Lists");
		signInPage.enterUserId("");
		log4jlogger.info("Entered user id : " + "<blank>");
		signInPage.clickContinue();
		log4jlogger.info("Clicked : Continue button");
		if (signInPage.getEmailMissingMsg().contains("Enter your email or mobile phone number")) {
			log4jlogger.info("Email missing msg present on blank login.");
			Assert.assertTrue(true);
		} else {
			captureScreenshot(driver, "loginBlankId");
			log4jlogger.info("Email missing msg absent on blank login.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 5, enabled = false)
	public void verifyForgotPassword() {
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jlogger.info("Clicked : Account & Lists");
		signInPage.enterUserId(userId);
		log4jlogger.info("Entered user id : " + userId);
		signInPage.clickContinue();
		log4jlogger.info("Clicked : Continue button");
		signInPage.clickForgotPasswordLink();
		log4jlogger.info("Clicked : Forgot Password link");
		signInPage.clickContinue();
		log4jlogger.info("Clicked : Continue button");
		if (signInPage.getOtpSentMsg().contains("We've sent an OTP to")) {
			log4jlogger.info("Otp has been sent.");
			Assert.assertTrue(true);
		} else {
			captureScreenshot(driver, "verifyForgotPassword");
			log4jlogger.info("Otp sent msg absent.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 6, enabled = false)
	public void verifyKeepMeSignedIn() {
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

		signInPage.clickKeepMeSignedIn();
		log4jlogger.info("Ticked : Keep me signed in");

		signInPage.clickSignIn();
		log4jlogger.info("Clicked : Sign in button");

		driver.get("https://www.google.com/");
		driver.get(appUrl);

		try {
			homePage.getSignedInUserName().contains(name);
			log4jlogger.info("Login retained.");
			Assert.assertTrue(true);
		} catch (NoSuchElementException e) {
			captureScreenshot(driver, "verifyKeepMeSignedIn");
			log4jlogger.error("Login lost.");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 7, enabled = false)
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
			captureScreenshot(driver, "loginValid");
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
			captureScreenshot(driver, "verifyAfterSignOutAccess");
		} else {
			log4jlogger.info("No access after sign out");
		}

	}
}
