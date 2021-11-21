package testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchResultsPage;

public class TC_10_SearchResults extends BaseTestClass {
	@Test(enabled = true, description = "Verify : search results are matching or not")
	public void verifySearchResults() {

		HomePage homePage = new HomePage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		String searchKey = "samsung";

		homePage.searchForThisProduct(searchKey);
		List<String> results = searchResultsPage.getAllResults();
		log4jLogger.info("Search Results count for " + searchKey + " : " + results.size());

		int i = 1;
		for (String result : results) {
			log4jLogger.info("Result #" + i + " : " + result);
			i++;
		}

		if (areMostOfOutputsMatchingInput(searchKey, results)) {
			log4jLogger.info("Search results are relavant.");
			Assert.assertTrue(true);
		} else {
			log4jLogger.error("Search results are irrelavant.");
			Assert.assertTrue(false);
		}
	}
}