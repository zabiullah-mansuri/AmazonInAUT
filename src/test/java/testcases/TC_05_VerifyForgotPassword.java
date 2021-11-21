package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class TC_05_VerifyForgotPassword extends BaseTestClass {

	@Test(enabled = true, description = "Verify : Forgot password functionality")
	public void verifyForgotPassword() {

		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jLogger.info("Clicked : Account & Lists");

		signInPage.enterUserId(userId);
		log4jLogger.info("Entered user id : " + userId);

		signInPage.clickContinue();
		log4jLogger.info("Clicked : Continue button");

		signInPage.clickForgotPasswordLink();
		log4jLogger.info("Clicked : Forgot Password link");

		signInPage.clickContinue();
		log4jLogger.info("Clicked : Continue button");

		if (signInPage.getOtpSentMsg().contains("We've sent an OTP to")
				|| signInPage.getOtpSentMsg().equals("Waiting for Captcha.")) {
			log4jLogger.info("Otp has been sent.");
			Assert.assertTrue(true);
		} else {
			log4jLogger.error("Otp sent msg absent.");
			Assert.assertTrue(false);
		}
	}
}