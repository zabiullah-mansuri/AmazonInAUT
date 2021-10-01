package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HelpPage extends BasePage {

	@FindBy(xpath = "//div[@class='a-box self-service-rich-card']")
	List<WebElement> selfServicesCards;

	@FindBy(xpath = "//div[@class='a-box self-service-rich-card']//h3")
	List<WebElement> selfServicesCardsHeaders;

	@FindBy(xpath = "//div[@class='a-box self-service-rich-card']//ul")
	List<WebElement> selfServicesCardsLines;

	@FindBy(xpath = "//ul[@id='category-section']//a[contains(text(),'Customer Service')]")
	WebElement topicCustomerService;

	@FindBy(xpath = "//button[@id='a-autoid-0-announce' and contains(text(),'Contact Us')]/ancestor::span")
	WebElement btnContactUs;

	@FindBy(id = "a-autoid-0")
	WebElement btnChat;

	@FindBy(id = "a-autoid-1")
	WebElement btnCallMe;

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

	public void cliclContactUs() {
		action = new Actions(driver);
		
		action.moveToElement(topicCustomerService);
		action.moveToElement(btnContactUs);
		action.click().build().perform();
	}

	public boolean areCharAndCallDisplayed() {
		return btnChat.isDisplayed() && btnCallMe.isDisplayed();
	}
}
