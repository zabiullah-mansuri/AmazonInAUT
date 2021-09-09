package pages;

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
	WebElement msgAddedToCart;

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
		
		wait.until(ExpectedConditions.visibilityOf(msgAddedToCart));
		return msgAddedToCart.isDisplayed();
	}

	public void clickAddToWishList() {
		btnAddToWishList.click();
	}

	public boolean isAddedToWishListMsgDisplayed() {
		
		wait.until(ExpectedConditions.visibilityOf(msgAddedToWishList));
		return msgAddedToWishList.isDisplayed();
	}
}