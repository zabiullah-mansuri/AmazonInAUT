package testcases;

import java.util.ArrayList;

import org.testng.annotations.Test;

import pages.HomePage;

public class TC_02_SearchSuggestions extends BaseTestClass {
	@Test(enabled = false)
	public void verifySearchSuggestionsCharByChar() {
		HomePage homePage = new HomePage(driver);
		String searchkey = "samsung";
		for (int i = 0; i < searchkey.length(); i++) {
			ArrayList<String> suggestions = homePage.searchForThisKeyword(searchkey.charAt(i));
			if (areMostOfSuggestionsMatching(homePage.getTextOfSearchBox(), suggestions)) {
				log4jlogger.info(homePage.getTextOfSearchBox() + " : ");
				log4jlogger.info(suggestions.toString());
			}
		}
	}

	boolean areMostOfSuggestionsMatching(String input, ArrayList<String> items) {
		int matchedSuggestionCount = 0, unmatchedSuggestionCount = 0;
		for (String item : items) {
			if (item.contains(input)) {
				matchedSuggestionCount++;
			} else {
				unmatchedSuggestionCount++;
			}
		}
		log4jlogger.info("No. of matched suggestions : " + matchedSuggestionCount);
		log4jlogger.info("No. of unmatched suggestions : " + unmatchedSuggestionCount);
		return matchedSuggestionCount > unmatchedSuggestionCount;
	}
}
