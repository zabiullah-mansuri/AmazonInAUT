package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	@FindBy(id = "nav-link-accountList")
	WebElement accountAndLists;
	@FindBy(xpath = "//*[@id=\'nav-flyout-ya-signin\']/a")
	WebElement btnSignIn;

	@FindBy(id = "nav-link-accountList-nav-line-1")
	WebElement helloName;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnAccountAndListsThenSignIn() {
		accountAndLists.click();
		// btnSignIn.click();
	}

	public String getSignedInUserName() {
		return helloName.getText();
	}

}
