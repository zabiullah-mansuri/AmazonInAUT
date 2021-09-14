package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;

import pages.HomePage;
import pages.SignInPage;

public class TC_05_VerifyForgotPassword extends BaseTestClass {

	@Test(enabled = false, description = "Verify : Forgot password functionality")
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
			log4jlogger.info("Otp sent msg absent.");
			Assert.assertTrue(false);
		}
	}
}