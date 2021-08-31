package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
	WebDriver driver;
	WebDriverWait wait;
	List<String> results;

	@FindBy(xpath = "//*[@id='search']//div[contains(@class,'s-result-item')]//a[@class='a-link-normal a-text-normal']")
	List<WebElement> resultItems;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<String> getAllResults() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(resultItems));
		results = new ArrayList<String>();
		for (WebElement item : resultItems) {
			results.add(item.getText());
		}
		return results;
	}

}
