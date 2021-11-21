package testcases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class TC_03_LoginInvalidPassword extends BaseTestClass {

	@Test(enabled = true, description = "Verify : Login with invalid password")
	public void loginInvalidPassword() {

		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jLogger.info("Clicked : Account & Lists");

		signInPage.enterUserId(userId);
		log4jLogger.info("Entered user id : " + userId);

		signInPage.clickContinue();
		signInPage.enterPassword(wrongPassword);
		log4jLogger.info("Entered password : " + wrongPassword);

		signInPage.clickSignIn();
		log4jLogger.info("Clicked : Sign in button");

		try {
			if (signInPage.getAuthErrorMsg().contains("Your password is incorrect")) {
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
