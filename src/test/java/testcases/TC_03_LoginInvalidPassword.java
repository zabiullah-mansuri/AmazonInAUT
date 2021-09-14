package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;

import pages.HomePage;
import pages.SignInPage;

public class TC_03_LoginInvalidPassword extends BaseTestClass {

	@Test(enabled = false, description = "Verify : Login with invalid password")
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
			log4jlogger.info("Error msg not present on invalid login.");
			Assert.assertTrue(false);
		}
	}
}
