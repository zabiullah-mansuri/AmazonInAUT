package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

	@FindBy(id = "add-to-cart-button")
	WebElement btnAddToCart;

	@FindBy(id = "attach-added-to-cart-alert-and-image-area")
	WebElement msgAddedToCart1;

	@FindBy(id = "huc-v2-order-row-confirm-text")
	WebElement msgAddedToCart2;

	@FindBy(id = "wishListMainButton")
	WebElement btnAddToWishList;

	@FindBy(id = "WLHUC_result_success")
	WebElement msgAddedToWishList;

	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickAddToCart() {
		btnAddToCart.click();
	}

	public boolean isAddedToCartMsgDisplayed() {
		wait = new WebDriverWait(driver, 5);
		try {
			wait.until(ExpectedConditions.visibilityOf(msgAddedToCart1));
			return msgAddedToCart1.isDisplayed();
		} catch (TimeoutException e) {
			wait.until(ExpectedConditions.visibilityOf(msgAddedToCart2));
			return msgAddedToCart2.isDisplayed();
		}
	}

	public void clickAddToWishList() {
		btnAddToWishList.click();
	}

	public boolean isAddedToWishListMsgDisplayed() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(msgAddedToWishList));
		return msgAddedToWishList.isDisplayed();
	}
}