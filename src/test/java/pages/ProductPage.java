package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(id = "add-to-cart-button")
	WebElement btnAddToCart;

	@FindBy(id = "attach-added-to-cart-alert-and-image-area")
	WebElement msgAddedToCart;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickAddToCart() {
		btnAddToCart.click();
	}

	public boolean isAddedToCartMsgDisplayed() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(msgAddedToCart));
		return msgAddedToCart.isDisplayed();
	}
}