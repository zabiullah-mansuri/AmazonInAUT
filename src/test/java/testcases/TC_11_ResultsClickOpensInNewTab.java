package testcases;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchResultsPage;

public class TC_11_ResultsClickOpensInNewTab extends BaseTestClass {
	@Test(enabled = true, description = "Verify : clicking on result opens in new tab")
	public void resultsClickOpensInNewTab() {

		HomePage homePage = new HomePage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		String searchKey = "samsung";

		homePage.searchForThisProduct(searchKey);
		log4jLogger.info("Searched : " + searchKey);

		Set<String> handlesBefore = driver.getWindowHandles();

		searchResultsPage.clickOnOneResultRandomly();
		log4jLogger.info("Clicked one result randomly");

		Set<String> handlesAfter = driver.getWindowHandles();

		if (handlesAfter.size() > handlesBefore.size()) {
			log4jLogger.info("Opened in new tab");
			Assert.assertTrue(true);
		} else {
			log4jLogger.error("Didn't open in new tab");
			Assert.assertTrue(false);
		}
	}
}