package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
	WebDriver driver;

	@FindBy(id = "ap_email")
	WebElement txtUserId;

	@FindBy(id = "ap_password")
	WebElement txtPassword;

	@FindBy(id = "continue")
	WebElement btnContinue;

	@FindBy(id = "signInSubmit")
	WebElement btnSignIn;

	@FindBy(xpath = "//*[@id=\'auth-error-message-box\']/div/div/ul/li/span")
	WebElement msgPasswordWrong;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUserId(String userId) {
		txtUserId.sendKeys(userId);
	}

	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void clickContinue() {
		btnContinue.click();
	}

	public void clickSignIn() {
		btnSignIn.click();
	}

	public boolean isWrongPasswordMsgPresent() {
		try {
			System.out.println(msgPasswordWrong.getText());
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}
}
