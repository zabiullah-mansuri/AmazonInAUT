package testcases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class TC_06_VerifyKeepMeSignedIn extends BaseTestClass {

	@Test(enabled = true, description = "Verify : 'Keep me signed in' functionality")
	public void verifyKeepMeSignedIn() {

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

		signInPage.clickKeepMeSignedIn();
		log4jLogger.info("Ticked : Keep me signed in");

		signInPage.clickSignIn();
		log4jLogger.info("Clicked : Sign in button");

		driver.get("https://www.google.com/");
		driver.get(appUrl);

		try {
			homePage.getSignedInUserName().contains(name);
			log4jLogger.info("Login retained.");
			Assert.assertTrue(true);
		} catch (NoSuchElementException e) {
			log4jLogger.error("Login lost.");
			Assert.assertTrue(false);
		}
	}
}