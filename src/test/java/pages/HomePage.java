package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	@FindBy(id = "nav-link-accountList")
	WebElement accountAndLists;

	@FindBy(xpath = "//*[@id=\'nav-flyout-ya-signin\']/a")
	WebElement btnSignIn;

	@FindBy(id = "nav-link-accountList-nav-line-1")
	WebElement helloName;

	@FindBy(id = "nav-item-signout")
	WebElement signOut;

	@FindBy(id = "twotabsearchtextbox")
	WebElement txtSearch;

	@FindBy(id = "suggestions")
	WebElement suggestionsBox;

	@FindBy(xpath = "//div[@id='suggestions']/div[@class='s-suggestion']")
	List<WebElement> suggestionItems;

	@FindBy(id = "nav-cart")
	WebElement shoppingCart;

	@FindBy(id = "nav-cart-count")
	WebElement cartCount;

	@FindBy(id = "WLHUC_viewlist")
	WebElement btnViewWL;

	@FindBy(xpath = "//div[@id='anonCarousel1']//img")
	List<WebElement> carouselItemImages;

	@FindBy(xpath = "//a[@class='a-carousel-goto-prevpage']")
	WebElement btnPrevCarousel;

	@FindBy(xpath = "//a[@class='a-carousel-goto-nextpage']")
	WebElement btnNextCarousel;

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickOnAccountAndListsThenSignIn() {
		accountAndLists.click();
	}

	public String getSignedInUserName() {
		return helloName.getText();
	}

	public void clickSignOut() {
		Actions action = new Actions(driver);
		action.moveToElement(accountAndLists).moveToElement(signOut).click().build().perform();
	}

	public String getTextOfSearchBox() {
		return txtSearch.getAttribute("value");
	}

	public String getPlaceholderOfSearchBox() {
		return txtSearch.getAttribute("placeholder");
	}

	public List<String> searchForThisKeyword(char input) {

		txtSearch.sendKeys(Character.toString(input));

		wait.until(ExpectedConditions.visibilityOf(suggestionsBox));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<String> suggestions = new ArrayList<String>();
		for (WebElement suggestion : suggestionItems) {
			suggestions.add(suggestion.getText());
		}
		return suggestions;
	}

	public void searchForThisProduct(String input) {
		txtSearch.sendKeys(input);
		txtSearch.submit();
	}

	public void goToShoppingCart() {
		shoppingCart.click();
	}

	public int getItemsInCartCount() {
		return Integer.parseInt(cartCount.getText());
	}

	public void goToWishList() {
		btnViewWL.click();
	}

	public Map<String, String> getCarouselItemNamesAndVisibility() {

		Map<String, String> carouselItemNamesAndVisibility = new HashMap<String, String>();

		carouselItemImages.forEach(item -> {
			carouselItemNamesAndVisibility.put(item.getAttribute("alt"), item.getCssValue("visibility"));
		});
		return carouselItemNamesAndVisibility;
	}

	public List<String> getCarouselItemNames() {
		return carouselItemImages.stream().map(image -> image.getAttribute("alt")).collect(Collectors.toList());
	}

	public String getVisibleCarouselItemName() {

		return carouselItemImages.stream().filter(image -> image.getCssValue("visibility").equals("visible"))
				.collect(Collectors.toList()).get(0).getAttribute("alt");
	}

	public boolean isCarouselPrevButtonDisplayed() {
		return btnPrevCarousel.isDisplayed();
	}

	public boolean isCarouselNextButtonDisplayed() {
		return btnNextCarousel.isDisplayed();
	}

	public void clickPreviousCarousel() {
		btnPrevCarousel.click();
	}

	public void clickNextCarousel() {
		btnNextCarousel.click();
	}
}