package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(name = "uid")
	WebElement txtUserID;
	@FindBy(name = "password")
	WebElement txtPassword;
	@FindBy(name = "btnLogin")
	WebElement btnLogin;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setUserID(String strUserID) {
		txtUserID.sendKeys(strUserID);
	}

	public void setPassword(String strPassword) {
		txtPassword.sendKeys(strPassword);
	}

	public void clickLogin() {
		btnLogin.click();
	}
}
