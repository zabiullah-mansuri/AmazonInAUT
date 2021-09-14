package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;

import pages.HomePage;
import pages.SignInPage;

public class TC_02_LoginInvalidId extends BaseTestClass {
	@Test(enabled = false, description  = "Verify : Login with invalid ID")
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
			log4jlogger.info("Error msg not present on invalid login.");
			Assert.assertTrue(false);
		}
	}
}
