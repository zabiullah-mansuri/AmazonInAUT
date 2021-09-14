package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HelpPage;
import pages.HomePage;

public class TC_17_CustomerServicePageDesiredOptions extends BaseTestClass {
	@Test(enabled = false, description = "Verify : Customer Service page has desired options")
	public void customerServicePageDesiredOptions() {

		HomePage homePage = new HomePage(driver);
		HelpPage helpPage = new HelpPage(driver);

		homePage.clickHMenuCustomerService();
		log4jlogger.info("Navigated to : Help page");

		Assert.assertTrue(helpPage.areSelfServicesCardsDisplayed(), "Self Service cards are not visible");
		log4jlogger.info("Self Service cards are visible.");

		log4jlogger.info("Options available on Help page are :");
		helpPage.getSelfServiceOptions().forEach(card -> {
			log4jlogger.info(card.get(0));
			for (int i = 1; i < card.size(); i++) {
				log4jlogger.info("        " + card.get(i));
			}
		});
	}
}
