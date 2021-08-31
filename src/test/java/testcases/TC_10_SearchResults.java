package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;
import pages.HomePage;
import pages.SearchResultsPage;

public class TC_10_SearchResults extends BaseTestClass {
	@Test(enabled = false)
	public void verifySearchResults() {
		HomePage homePage = new HomePage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		String searchKey = "samsung";
		homePage.searchForThisProduct(searchKey);
		List<String> results = searchResultsPage.getAllResults();
		log4jlogger.info("Search Results count for " + searchKey + " : " + results.size());
		int i = 1;
		for (String result : results) {
			log4jlogger.info("Result #" + i + " : " + result);
			i++;
		}
		if (areMostOfOutputsMatchingInput(searchKey, results)) {
			log4jlogger.info("Search results are relavant.");
			AssertJUnit.assertTrue(true);
		} else {
			log4jlogger.info("Search results are irrelavant.");
			AssertJUnit.assertTrue(false);
		}

	}
}
