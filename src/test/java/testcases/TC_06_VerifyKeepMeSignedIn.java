package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;

public class TC_06_VerifyKeepMeSignedIn extends BaseTestClass {

	@Override
	void initializeDriver() {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConfig.getChromeDriverPath());
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--user-data-dir=C:/Users/mansozx/AppData/Local/Google/Chrome/User Data");
			driver = new ChromeDriver(options);
			log4jLogger.info("Browser : Google Chrome");
		} else if (browser.equals("chrome-headless")) {
			System.setProperty("webdriver.chrome.driver", readConfig.getChromeDriverPath());
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--user-data-dir=C:/Users/mansozx/AppData/Local/Google/Chrome/User Data");
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
			log4jLogger.info("Browser : Google Chrome in headless mode");
		} else if (browser.equals("firefox")) {
			throw new SkipException("This test case not yet implemented for Mozilla Firefox.");
		} else if (browser.equals("ie")) {
			throw new SkipException("This test case not yet implemented for Internet Explorer.");
		} else if (browser.equals("edge")) {
			throw new SkipException("This test case not yet implemented for Microsoft Edge.");
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appUrl);
		context.setAttribute("driver", driver);
	}

	@Test(enabled = true, description = "Verify : 'Keep me signed in' functionality")
	public void verifyKeepMeSignedIn() {

		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		if (homePage.getSignedInUserName().contains(name)) {
			log4jLogger.info("User is already signed in. Clicking : Sign out");
			while (!homePage.clickSignOut()) {
				log4jLogger.info("Clicking again : Sign out");
			}
			driver.navigate().to(appUrl);
		}

		homePage.clickOnAccountAndListsThenSignIn();
		log4jLogger.info("Clicked : Account & Lists");

		signInPage.enterUserId(userId);
		log4jLogger.info("Entered user id : " + userId);

		signInPage.clickContinue();
		log4jLogger.info("Clicked : Continue button");

		signInPage.enterPassword(password);
		log4jLogger.info("Entered password : " + password);

		signInPage.clickKeepMeSignedIn();
		log4jLogger.info("Ticked : Keep me signed in");

		signInPage.clickSignIn();
		log4jLogger.info("Clicked : Sign in button");

		if (homePage.getSignedInUserName().contains(name)) {
			log4jLogger.info("Login successfull.");
		}

		driver.quit();
		log4jLogger.info("Quited browser.");

		initializeDriver();
		log4jLogger.info("Re-launched browser.");

		homePage = new HomePage(driver);

		try {
			if (homePage.getSignedInUserName().contains(name)) {
				log4jLogger.info("Login retained.");
				Assert.assertTrue(true);
			} else {
				log4jLogger.error("Login lost.");
				Assert.assertTrue(false);
			}
		} catch (NoSuchElementException e) {
			log4jLogger.error(e.getMessage());
			Assert.assertTrue(false);
		}
	}
}