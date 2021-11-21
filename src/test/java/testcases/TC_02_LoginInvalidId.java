package testcases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class TC_02_LoginInvalidId extends BaseTestClass {
	@Test(enabled = true, description = "Verify : Login with invalid ID")
	public void loginInvalidId() {

		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jLogger.info("Clicked : Account & Lists");

		signInPage.enterUserId(wrongUserId);
		log4jLogger.info("Entered user id : " + wrongUserId);

		signInPage.clickContinue();
		log4jLogger.info("Clicked : Continue button");

		try {
			if (signInPage.getAuthErrorMsg().contains("We cannot find an account with that email address")) {
				log4jLogger.info("Error msg present on invalid login.");
				Assert.assertTrue(true);
			} else {
				log4jLogger.error("Error msg not present on invalid login.");
				Assert.assertTrue(false);
			}
		} catch (NoSuchElementException e) {
			log4jLogger.error(e.getMessage());
			Assert.assertTrue(false);

		}
	}
}
