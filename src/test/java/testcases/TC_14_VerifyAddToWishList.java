package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import pages.SignInPage;
import pages.WishListPage;

public class TC_14_VerifyAddToWishList extends BaseTestClass {
	@Test(enabled = false, description = "Verify : Add to Wish List")
	public void verifyAddToWishList() {

		SignInPage signInPage = new SignInPage(driver);
		HomePage homePage = new HomePage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		ProductPage productPage = new ProductPage(driver);
		WishListPage wishListPage = new WishListPage(driver);

		String searchKey = "motorola";

		log4jlogger.info("Signing in...");
		homePage.clickOnAccountAndListsThenSignIn();
		signInPage.enterUserId(userId);
		signInPage.clickContinue();
		signInPage.enterPassword(password);
		signInPage.clickSignIn();

		homePage.searchForThisProduct(searchKey);
		log4jlogger.info("Searched : " + searchKey);

		searchResultsPage.clickOnOneResultRandomly();
		log4jlogger.info("Clicked one result randomly");

		String[] tabs = driver.getWindowHandles().toArray(new String[2]);
		driver.switchTo().window(tabs[1]);
		log4jlogger.info("Switched to Product's tab");

		productPage.clickAddToWishList();
		log4jlogger.info("Click : Add to Wish List");

		if (productPage.isAddedToWishListMsgDisplayed()) {
			log4jlogger.info("Msg present : Added to Wish List");
			Assert.assertTrue(true);
		} else {
			log4jlogger.warn("Msg absent : Added to Wish List");
			Assert.assertTrue(false);
		}

		homePage.goToWishList();
		log4jlogger.info("Opened : Wish List Page");
		wishListPage.getWishListItemNames().forEach(item -> System.out.println(item));

	}
}
