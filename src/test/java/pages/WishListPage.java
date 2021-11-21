package pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishListPage extends BasePage {

	@FindBy(xpath = "//a[contains(@id,'itemName_')]")
	List<WebElement> itemNames;

	@FindBy(xpath = "//span[contains(@id,'delete-button-']//input")
	List<WebElement> deleteLinks;

	public WishListPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public List<String> getWishListItemNames(Logger logger) {
		logger.info("No. of items in Wish List : " + itemNames.size());
		List<String> names = new ArrayList<String>();
		itemNames.forEach(item -> names.add(item.getText()));
		return names;
	}

	public void deleteItemAtIndex(int index) {
		try {
			deleteLinks.get(index).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
}
