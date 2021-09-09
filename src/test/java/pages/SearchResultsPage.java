package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage extends BasePage {

	@FindBy(xpath = "//*[@id='search']//div[contains(@class,'s-result-item')]//a[@class='a-link-normal a-text-normal']")
	List<WebElement> resultItems;

	public SearchResultsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public List<String> getAllResults() {

		wait.until(ExpectedConditions.visibilityOfAllElements(resultItems));

		List<String> results = new ArrayList<String>();
		for (WebElement item : resultItems) {
			results.add(item.getText());
		}
		return results;
	}

	public void clickOnOneResultRandomly() {

		wait.until(ExpectedConditions.visibilityOfAllElements(resultItems));
		resultItems.get(new Random().nextInt(resultItems.size())).click();
	}
}
