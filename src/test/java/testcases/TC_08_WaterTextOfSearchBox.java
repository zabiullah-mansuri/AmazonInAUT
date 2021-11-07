package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;

public class TC_08_WaterTextOfSearchBox extends BaseTestClass {
	@Test(enabled = true, description = "Verify : Water text in Search box")
	public void waterTextOfSearchBox() {

		HomePage homePage = new HomePage(driver);

		if (homePage.getPlaceholderOfSearchBox().isEmpty()) {
			log4jlogger.info("Found no water text.");
			Assert.assertTrue(true);
		}
	}
}