package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage {

	@FindBy(id = "ap_email")
	WebElement txtUserId;

	@FindBy(id = "ap_password")
	WebElement txtPassword;

	@FindBy(id = "continue")
	WebElement btnContinue;

	@FindBy(id = "signInSubmit")
	WebElement btnSignIn;

	@FindBy(xpath = "//*[@id='auth-error-message-box']/div/div/ul/li/span")
	WebElement msgAuthError;

	@FindBy(xpath = "//*[@id='auth-email-missing-alert']/div/div")
	WebElement msgEmailMissing;

	@FindBy(id = "auth-fpp-link-bottom")
	WebElement lnkForgotPassword;

	@FindBy(xpath = "//*[@id='verification-code-form']/div[4]/div[2]/span[2]")
	WebElement msgOtpSent;

	@FindBy(xpath = "//*[@id='authportal-main-section']//input[@name='rememberMe']")
	WebElement chkRememberMe;

	public SignInPage(WebDriver driver) {
		super(driver);
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

	public String getAuthErrorMsg() {
		try {
			return msgAuthError.getText();
		} catch (NoSuchElementException e) {
			return "No Auth Error Message";
		}
	}

	public String getEmailMissingMsg() {
		try {
			return msgEmailMissing.getText();
		} catch (NoSuchElementException e) {
			return "No Email Missing Error Message";
		}
	}

	public void clickForgotPasswordLink() {
		lnkForgotPassword.click();
	}

	public String getOtpSentMsg() {
		try {
			return msgOtpSent.getText();
		} catch (NoSuchElementException e) {
			return "Waiting for Captcha.";
		}
	}

	public void clickKeepMeSignedIn() {
		chkRememberMe.click();
	}
}
