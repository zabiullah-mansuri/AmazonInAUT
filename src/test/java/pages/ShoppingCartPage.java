package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {
	WebDriver driver;

	@FindBy(xpath = "//*[contains(@id,'sc-item-')]//span[@class='a-truncate-cut']")
	List<WebElement> itemNames;

	@FindBy(xpath = "//*[contains(@id,'sc-item-')]//input[@value='Delete']")
	List<WebElement> deleteLinks;

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<String> getCartItemNames() {
		System.out.println("No. of items in cart : " + itemNames.size());
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
