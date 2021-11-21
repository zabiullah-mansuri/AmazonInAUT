package testcases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class TC_01_LoginValid extends BaseTestClass {

	@Test(enabled = true, description = "Verify : Login with valid credentials")
	public void loginValid() {

		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jLogger.info("Clicked : Account & Lists");

		signInPage.enterUserId(userId);
		log4jLogger.info("Entered user id : " + userId);

		signInPage.clickContinue();
		signInPage.enterPassword(password);
		log4jLogger.info("Entered password : " + password);

		signInPage.clickSignIn();
		log4jLogger.info("Clicked : Sign in button");

		try {
			if (homePage.getSignedInUserName().contains(name)) {
				log4jLogger.info("Login successfull.");
				Assert.assertTrue(true);
			} else {
				log4jLogger.error("Login failed.");
				Assert.assertTrue(false);
			}
		} catch (NoSuchElementException e) {
			log4jLogger.error("Login failed.");
			Assert.assertTrue(false);
		}
	}
}
