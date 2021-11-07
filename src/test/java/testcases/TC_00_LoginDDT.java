package testcases;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;
import utilities.ExcelUtility;

public class TC_00_LoginDDT extends BaseTestClass {

	@Test(enabled = true, dataProvider = "LoginData", description = "Data Driven Testing for Login")
	public void loginDDT(String username, String password) {
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);

		homePage.clickOnAccountAndListsThenSignIn();
		log4jlogger.info("Clicked : Account & Lists");

		signInPage.enterUserId(username);
		log4jlogger.info("Entered user id : " + userId);
		signInPage.clickContinue();

		try {
			signInPage.enterPassword(password);
			log4jlogger.info("Entered password : " + password);
			signInPage.clickSignIn();
			log4jlogger.info("Clicked : Sign in button");
		} catch (NoSuchElementException e) {
			if (signInPage.getAuthErrorMsg().contains("We cannot find an account with that email address")) {
				log4jlogger.info("Error msg present on invalid username.");
				return;
			}
		}

		try {
			if (homePage.getSignedInUserName().contains(name)) {
				log4jlogger.info("Login successfull.");
				Assert.assertTrue(true);
				return;
			}
		} catch (NoSuchElementException e) {
			if (signInPage.getAuthErrorMsg().contains("Your password is incorrect")) {
				log4jlogger.info("Error msg present on invalid login.");
				Assert.assertTrue(true);
				return;
			}
		}
	}

	@DataProvider(name = "LoginData")
	public String[][] getLoginData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/testdata/LoginData.xlsx";
		int rowCount = ExcelUtility.getRowCount(path, "Sheet1");
		int colCount = ExcelUtility.getCellCount(path, "Sheet1", 1);
		log4jlogger.info("Number of records in LoginData.xlsx file. : " + rowCount);
		String data[][] = new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				data[i - 1][j] = ExcelUtility.getCellData(path, "Sheet1", i, j);
			}
		}
		return data;
	}

	@DataProvider(name = "TempLoginData")
	public String[][] getTempLoginData() {
		String data[][] = { { "8733806144", "QA@automation" } };
		return data;
	}
}