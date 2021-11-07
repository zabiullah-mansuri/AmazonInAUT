package pages;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage extends BasePage {

	@FindBy(xpath = "//div[@id='search']//div[contains(@data-component-type ,'s-search-result')]//span[@class='a-size-medium a-color-base a-text-normal']/parent::a")
	List<WebElement> resultItems;

	public SearchResultsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public List<String> getAllResults() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(resultItems));
		return resultItems.stream().map(item -> item.getText()).collect(Collectors.toList());
	}

	public void clickOnOneResultRandomly() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(resultItems));
		resultItems.get(new Random().nextInt(resultItems.size())).click();
	}
}
