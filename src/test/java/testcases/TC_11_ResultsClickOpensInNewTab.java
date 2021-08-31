package testcases;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchResultsPage;

public class TC_11_ResultsClickOpensInNewTab extends BaseTestClass {
	@Test(enabled = true)
	public void resultsClickOpensInNewTab() {
		HomePage homePage = new HomePage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		String searchKey = "samsung";
		homePage.searchForThisProduct(searchKey);
		log4jlogger.info("Searched : " + searchKey);
		Set<String> handlesBefore = driver.getWindowHandles();
		searchResultsPage.clickOnOneResultRandomly();
		log4jlogger.info("Clicked one result randomly");
		Set<String> handlesAfter = driver.getWindowHandles();
		if (handlesAfter.size() > handlesBefore.size()) {
			log4jlogger.info("Opened in new tab");
			Assert.assertTrue(true);
		} else {
			log4jlogger.warn("Didn't open in new tab");
			Assert.assertTrue(false);
		}

	}
}
