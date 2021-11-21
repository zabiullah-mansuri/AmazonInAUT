package testcases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class TC_04_LoginBlankId extends BaseTestClass {

	@Test(enabled = true, description = "Verify : Login with fields blank")
	public void loginBlankId() {

		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jLogger.info("Clicked : Account & Lists");

		signInPage.enterUserId("");
		log4jLogger.info("Entered user id : " + "<blank>");

		signInPage.clickContinue();
		log4jLogger.info("Clicked : Continue button");

		try {
			if (signInPage.getEmailMissingMsg().contains("Enter your email or mobile phone number")) {
				log4jLogger.info("Email missing msg present on blank login.");
				Assert.assertTrue(true);
			} else {
				log4jLogger.error("Email missing msg absent on blank login.");
				Assert.assertTrue(false);
			}
		} catch (NoSuchElementException e) {
			log4jLogger.error(e.getMessage());
			Assert.assertTrue(false);
		}
	}
}