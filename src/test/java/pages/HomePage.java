package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;

	List<String> suggestions;

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

	public HomePage(WebDriver driver) {
		this.driver = driver;
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
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(suggestionsBox));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		suggestions = new ArrayList<String>();
		for (WebElement suggestion : suggestionItems) {
			suggestions.add(suggestion.getText());
		}
		return suggestions;
	}

	public void searchForThisProduct(String input) {

		txtSearch.sendKeys(input);
		txtSearch.submit();

	}
}
