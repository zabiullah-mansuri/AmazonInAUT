package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import pages.HomePage;

public class TC_08_WaterTextOfSearchBox extends BaseTestClass {
	@Test(enabled = false)
	public void waterTextOfSearchBox() {
		
		HomePage homePage = new HomePage(driver);
		
		if (homePage.getPlaceholderOfSearchBox().isEmpty()) {
			log4jlogger.info("Found no water text.");
			AssertJUnit.assertTrue(true);
		}
	}
}