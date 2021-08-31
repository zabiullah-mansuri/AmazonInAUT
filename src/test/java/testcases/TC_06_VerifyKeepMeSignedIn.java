package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class TC_06_VerifyKeepMeSignedIn extends BaseTestClass {

	@Test(enabled = false)
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
			AssertJUnit.assertTrue(true);
		} catch (NoSuchElementException e) {
			captureScreenshot(driver, "verifyKeepMeSignedIn");
			log4jlogger.error("Login lost.");
			AssertJUnit.assertTrue(false);
		}

	}

}
