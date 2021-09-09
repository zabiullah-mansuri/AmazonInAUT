package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	@FindBy(id = "ap_customer_name")
	WebElement txtName;

	@FindBy(xpath = "//div[@id='auth-customerName-missing-alert']//div[@class='a-alert-content']")
	WebElement alertNameMissing;

	@FindBy(id = "ap_phone_number")
	WebElement txtPhone;

	@FindBy(xpath = "//div[@id='auth-phoneNumber-missing-alert']//div[@class='a-alert-content']")
	WebElement alertPhoneMissing;
	
	@FindBy(xpath="//div[@id='auth-phoneNumber-invalid-phone-alert']//div[@class='a-alert-content']")
	WebElement alertPhoneInvalid;

	@FindBy(id = "ap_email")
	WebElement txtEmail;

	@FindBy(id = "ap_password")
	WebElement txtPassword;
	
	@FindBy(xpath = "//div[@id='auth-password-missing-alert']//div[@class='a-alert-content']")
	WebElement alertPasswordMissing;

	@FindBy(xpath = "//div[@id='auth-password-invalid-password-alert']//div[@class='a-alert-content']")
	WebElement alertInvalidPassword;

	@FindBy(id = "continue")
	WebElement btnContinue;

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

}
