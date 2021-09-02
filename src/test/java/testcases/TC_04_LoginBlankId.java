package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class TC_04_LoginBlankId extends BaseTestClass {

	@Test(enabled = false)
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
			AssertJUnit.assertTrue(true);
		} else {
			captureScreenshot(driver, "loginBlankId");
			log4jlogger.info("Email missing msg absent on blank login.");
			AssertJUnit.assertTrue(false);
		}
	}
}