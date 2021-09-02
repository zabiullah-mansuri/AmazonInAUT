package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;

public class TC_12_VerifyAddToCart extends BaseTestClass {
	@Test(enabled = true)
	public void verifyAddToCart() {

		HomePage homePage = new HomePage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		ProductPage productPage = new ProductPage(driver);
		String searchKey = "samsung";

		homePage.searchForThisProduct(searchKey);
		log4jlogger.info("Searched : " + searchKey);

		searchResultsPage.clickOnOneResultRandomly();
		log4jlogger.info("Clicked one result randomly");

		String[] tabs = driver.getWindowHandles().toArray(new String[2]);
		driver.switchTo().window(tabs[1]);
		log4jlogger.info("Switched to Product's tab");

		productPage.clickAddToCart();
		log4jlogger.info("Clicked : Add to cart");

		if (productPage.isAddedToCartMsgDisplayed()) {
			log4jlogger.info("Msg present : Added to Cart");
			Assert.assertTrue(true);
		} else {
			log4jlogger.warn("Msg absent : Added to Cart");
			Assert.assertTrue(false);
		}
	}
}
