package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HelpPage extends BasePage {

	@FindBy(xpath = "//div[@class='a-box self-service-rich-card']")
	List<WebElement> selfServicesCards;

	@FindBy(xpath = "//div[@class='a-box self-service-rich-card']//h3")
	List<WebElement> selfServicesCardsHeaders;

	@FindBy(xpath = "//div[@class='a-box self-service-rich-card']//ul")
	List<WebElement> selfServicesCardsLines;

	public HelpPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean areSelfServicesCardsDisplayed() {
		return selfServicesCards.stream().allMatch(card -> card.isDisplayed());
	}

	public List<List<String>> getSelfServiceOptions() {
		List<List<String>> cardsAll = new ArrayList<List<String>>();
		List<String> cardsOne;
		for (int i = 0; i < selfServicesCards.size(); i++) {
			cardsOne = new ArrayList<String>();
			cardsOne.add(selfServicesCardsHeaders.get(i).getText());
			cardsOne.addAll(Arrays.asList(selfServicesCardsLines.get(i).getText().split("\n")));
			cardsAll.add(cardsOne);
		}
		return cardsAll;
	}
}
