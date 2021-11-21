package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends BasePage {

	@FindBy(xpath = "//*[contains(@id,'sc-item-')]//span[@class='a-truncate-cut']")
	List<WebElement> itemNames;

	@FindBy(xpath = "//*[contains(@id,'sc-item-')]//input[@value='Delete']")
	List<WebElement> deleteLinks;

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public List<String> getCartItemNames(Logger logger) {
		logger.info("No. of items in cart : " + itemNames.size());
		return itemNames.stream().map(item -> item.getText()).collect(Collectors.toList());
	}

	public void deleteItemAtIndex(int index) {
		try {
			deleteLinks.get(index).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
}
