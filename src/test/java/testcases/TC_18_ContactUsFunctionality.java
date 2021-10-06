package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HelpPage;
import pages.HomePage;
import pages.SignInPage;

public class TC_18_ContactUsFunctionality extends BaseTestClass {
	@Test(enabled = false, description = "Verify : All contact us options working")
	public void contactUsFunctionality() {
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);
		HelpPage helpPage = new HelpPage(driver);

		log4jlogger.info("Signing in...");
		homePage.clickOnAccountAndListsThenSignIn();
		signInPage.enterUserId(userId);
		signInPage.clickContinue();
		signInPage.enterPassword(password);
		signInPage.clickSignIn();

		homePage.clickHMenuCustomerService();
		log4jlogger.info("Navigated to : Help page");

		helpPage.cliclContactUs();
		log4jlogger.info("Clicked button : Contact Us");

		String[] tabs = driver.getWindowHandles().toArray(new String[2]);
		driver.switchTo().window(tabs[1]);
		log4jlogger.info("Switched to Contact Us tab");

		Assert.assertTrue(helpPage.areCharAndCallDisplayed(), "Chat and Call Me options are missing");
		log4jlogger.info("Chat and Call Me options are displayed");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
