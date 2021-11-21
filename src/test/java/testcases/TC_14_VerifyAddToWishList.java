package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import pages.SignInPage;
import pages.WishListPage;

public class TC_14_VerifyAddToWishList extends BaseTestClass {
	@Test(enabled = true, description = "Verify : Add to Wish List")
	public void verifyAddToWishList() {

		SignInPage signInPage = new SignInPage(driver);
		HomePage homePage = new HomePage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		ProductPage productPage = new ProductPage(driver);
		WishListPage wishListPage = new WishListPage(driver);

		String searchKey = "samsung";

		log4jLogger.info("Signing in...");
		homePage.clickOnAccountAndListsThenSignIn();
		signInPage.enterUserId(userId);
		signInPage.clickContinue();
		signInPage.enterPassword(password);
		signInPage.clickSignIn();

		homePage.searchForThisProduct(searchKey);
		log4jLogger.info("Searched : " + searchKey);

		searchResultsPage.clickOnOneResultRandomly();
		log4jLogger.info("Clicked one result randomly");

		String[] tabs = driver.getWindowHandles().toArray(new String[2]);
		driver.switchTo().window(tabs[1]);
		log4jLogger.info("Switched to Product's tab");

		productPage.clickAddToWishList();
		log4jLogger.info("Click : Add to Wish List");

		if (productPage.isAddedToWishListMsgDisplayed()) {
			log4jLogger.info("Msg present : Added to Wish List");
			Assert.assertTrue(true);
		} else {
			log4jLogger.error("Msg absent : Added to Wish List");
			Assert.assertTrue(false);
		}

		homePage.goToWishList();
		log4jLogger.info("Opened : Wish List Page");
		wishListPage.getWishListItemNames(log4jLogger).forEach(item -> log4jLogger.info(item));

	}
}
