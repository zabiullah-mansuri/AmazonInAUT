package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;

public class TC_12_VerifyAddToCart extends BaseTestClass {
	@Test(enabled = true, description = "Verify : Add to cart functionality")
	public void verifyAddToCart() {

		HomePage homePage = new HomePage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		ProductPage productPage = new ProductPage(driver);
		String searchKey = "samsung";

		homePage.searchForThisProduct(searchKey);
		log4jLogger.info("Searched : " + searchKey);

		searchResultsPage.clickOnOneResultRandomly();
		log4jLogger.info("Clicked one result randomly");

		String[] tabs = driver.getWindowHandles().toArray(new String[2]);
		driver.switchTo().window(tabs[1]);
		log4jLogger.info("Switched to Product's tab");

		int beforeAdding = homePage.getItemsInCartCount();
		log4jLogger.info("Count of Cart items : Before Adding : " + beforeAdding);

		productPage.clickAddToCart();
		log4jLogger.info("Clicked : Add to cart");

		if (productPage.isAddedToCartMsgDisplayed()) {
			log4jLogger.info("Msg present : Added to Cart");
			Assert.assertTrue(true);
		} else {
			log4jLogger.error("Msg absent : Added to Cart");
			Assert.assertTrue(false);
		}

		driver.navigate().refresh();

		int afterAdding = homePage.getItemsInCartCount();
		log4jLogger.info("Count of Cart items : After Adding : " + afterAdding);
	}
}