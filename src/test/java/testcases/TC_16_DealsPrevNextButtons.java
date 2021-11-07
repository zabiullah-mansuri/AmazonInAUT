package testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;

public class TC_16_DealsPrevNextButtons extends BaseTestClass {

	@Test(enabled = true, description = "Verify : Carousel's PREVIOUS and NEXT buttons functionality")
	public void dealsPrevNextButtonsWorking() throws InterruptedException {

		HomePage homePage = new HomePage(driver);

		Assert.assertTrue(homePage.isCarouselPrevButtonDisplayed(), "Previous button on Carousel : Visible");
		log4jlogger.info("Previous button on Carousel : Visible");

		Assert.assertTrue(homePage.isCarouselNextButtonDisplayed(), "Next button on Carousel : Visible");
		log4jlogger.info("Next button on Carousel : Visible");

		List<String> carouselItemNames = homePage.getCarouselItemNames();
		log4jlogger.info("Names of items in Carousel :" + carouselItemNames.toString());

		for (int i = 0; i < carouselItemNames.size(); i++) {

			homePage.clickNextCarousel();
			log4jlogger.info("Clicked : Next button on Carousel");
			Thread.sleep(1500);

			String visibleCarouselName = homePage.getVisibleCarouselItemName();
			Assert.assertNotNull(visibleCarouselName, "No visible carousel found");
			log4jlogger.info("Now visible : " + visibleCarouselName);
		}

		for (int i = 0; i < carouselItemNames.size(); i++) {

			homePage.clickPreviousCarousel();
			log4jlogger.info("Clicked : Previous button on Carousel");
			Thread.sleep(1500);

			String visibleCarouselName = homePage.getVisibleCarouselItemName();
			Assert.assertNotNull(visibleCarouselName, "No visible carousel found");
			log4jlogger.info("Now visible : " + visibleCarouselName);
		}
	}
}
