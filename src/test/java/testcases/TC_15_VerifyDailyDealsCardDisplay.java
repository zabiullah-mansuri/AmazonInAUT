package testcases;

import org.apache.commons.logging.impl.Log4JLogger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;

public class TC_15_VerifyDailyDealsCardDisplay extends BaseTestClass {

	String string;
	boolean flag;

	@Test(enabled = true, description = "Verifying : Daily deals cards are displaying on Home page")
	public void VerifyDailyDealsCardDisplay() {

		HomePage homePage = new HomePage(driver);

		flag = false;
		homePage.getCarouselItemNamesAndVisibility().forEach((name, visibility) -> {

			log4jlogger.info(visibility + "\t:" + name);

			if (visibility.equals("visible")) {
				string = name;
				flag = true;
			}
		});
		Assert.assertTrue(flag, string + " is visible");
	}
}
