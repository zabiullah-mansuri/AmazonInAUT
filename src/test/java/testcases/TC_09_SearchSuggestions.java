package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class TC_09_SearchSuggestions extends BaseTestClass {

	@Test(enabled = false)
	public void verifySearchSuggestionsCharByChar() {

		HomePage homePage = new HomePage(driver);
		String searchkey = "samsung";

		for (int i = 0; i < searchkey.length(); i++) {

			List<String> suggestions = homePage.searchForThisKeyword(searchkey.charAt(i));
			log4jlogger.info("Found : '" + homePage.getTextOfSearchBox() + "' in : " + suggestions.toString());

			if (areMostOfOutputsMatchingInput(homePage.getTextOfSearchBox(), suggestions)) {
				Assert.assertTrue(true);
			} else {
				log4jlogger.info("Search suggestions are irrelavant.");
				Assert.assertTrue(false);
			}
		}
	}
}