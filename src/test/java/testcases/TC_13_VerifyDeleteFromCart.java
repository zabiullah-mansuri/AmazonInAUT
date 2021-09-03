package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import pages.ShoppingCartPage;

public class TC_13_VerifyDeleteFromCart extends BaseTestClass {
	
	@Test(enabled = true)
	public void verifyDeleteFromCart() {

		HomePage homePage = new HomePage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		ProductPage productPage = new ProductPage(driver);
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
		String searchKey = "iPhone";

		homePage.searchForThisProduct(searchKey);
		log4jlogger.info("Searched : " + searchKey);

		searchResultsPage.clickOnOneResultRandomly();
		log4jlogger.info("Clicked one result randomly");

		String[] tabs = driver.getWindowHandles().toArray(new String[2]);
		driver.switchTo().window(tabs[1]);
		log4jlogger.info("Switched to Product's tab");

		int beforeAdd = homePage.getItemsInCartCount();
		log4jlogger.info("Count of Cart items : Before Add : " + beforeAdd);

		productPage.clickAddToCart();
		log4jlogger.info("Clicked : Add to cart");
		
		productPage.isAddedToCartMsgDisplayed();
		driver.navigate().refresh();

		int afterAdd = homePage.getItemsInCartCount();
		log4jlogger.info("Count of Cart items : After Add : " + afterAdd);

		homePage.goToShoppingCart();
		log4jlogger.info("Opened : Shopping Cart");
		shoppingCartPage.getCartItemNames().forEach(item -> System.out.println(item));

		shoppingCartPage.deleteItemAtIndex(0);
		log4jlogger.info("Item has been deleted from cart");

		int afterDelete = homePage.getItemsInCartCount();
		log4jlogger.info("Count of Cart items : After Delete : " + afterDelete);

		Assert.assertEquals(beforeAdd, afterDelete);
	}
}
