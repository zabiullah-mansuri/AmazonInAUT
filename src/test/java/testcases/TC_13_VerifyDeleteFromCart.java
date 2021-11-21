package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import pages.ShoppingCartPage;

public class TC_13_VerifyDeleteFromCart extends BaseTestClass {

	@Test(enabled = true, description = "Verify : Delete from cart functionality")
	public void verifyDeleteFromCart() {

		HomePage homePage = new HomePage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		ProductPage productPage = new ProductPage(driver);
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
		String searchKey = "samsung";

		homePage.searchForThisProduct(searchKey);
		log4jLogger.info("Searched : " + searchKey);

		searchResultsPage.clickOnOneResultRandomly();
		log4jLogger.info("Clicked one result randomly");

		String[] tabs = driver.getWindowHandles().toArray(new String[2]);
		driver.switchTo().window(tabs[1]);
		log4jLogger.info("Switched to Product's tab");

		int beforeAdd = homePage.getItemsInCartCount();
		log4jLogger.info("Count of Cart items : Before Add : " + beforeAdd);

		productPage.clickAddToCart();
		log4jLogger.info("Clicked : Add to cart");

		productPage.isAddedToCartMsgDisplayed();
		driver.navigate().refresh();

		int afterAdd = homePage.getItemsInCartCount();
		log4jLogger.info("Count of Cart items : After Add : " + afterAdd);

		homePage.goToShoppingCart();
		log4jLogger.info("Opened : Shopping Cart");
		shoppingCartPage.getCartItemNames(log4jLogger).forEach(item -> System.out.println(item));

		shoppingCartPage.deleteItemAtIndex(0);
		log4jLogger.info("Item has been deleted from cart");

		int afterDelete = homePage.getItemsInCartCount();
		log4jLogger.info("Count of Cart items : After Delete : " + afterDelete);

		Assert.assertEquals(beforeAdd, afterDelete);
	}
}
