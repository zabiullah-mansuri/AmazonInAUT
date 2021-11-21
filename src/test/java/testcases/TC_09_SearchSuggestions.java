package testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;

public class TC_09_SearchSuggestions extends BaseTestClass {

	@Test(enabled = true, description = "Verify : Seach suggestions characters by characters")
	public void verifySearchSuggestionsCharByChar() throws InterruptedException {

		String searchkey = "samsung";
		boolean tryAgain = false;
		do {
			try {
				tryAgain = false;
				searchSuggestionsCharByChar(searchkey);
			} catch (Exception e) {
				log4jLogger.error(e.getMessage());
				afterEachTest(null);
				beforeEachTest(context);
				tryAgain = true;
			}
		} while (tryAgain);
	}

	public void searchSuggestionsCharByChar(String searchkey) throws Exception {
		HomePage homePage = new HomePage(driver);
		for (int i = 0; i < searchkey.length(); i++) {
			List<String> suggestions = homePage.searchForThisKeyword(log4jLogger, searchkey.charAt(i));
			if (suggestions.size() > 0) {

				log4jLogger.info(
						"Search results for '" + homePage.getTextOfSearchBox() + "' are : " + suggestions.toString());
				if (areMostOfOutputsMatchingInput(homePage.getTextOfSearchBox(), suggestions)) {
					Assert.assertTrue(true);
				} else {
					log4jLogger.error("Search suggestions are irrelavant.");
					Assert.assertTrue(false);
				}
			} else {
				throw new Exception("Suggestions list is empty.");
			}
		}
	}
}