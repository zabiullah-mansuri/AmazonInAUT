package testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;

public class TC_19_CategoriesInSearchBox extends BaseTestClass {

	@Test(enabled = true, description = "Verify dropdown for categories in search box working or not")
	public void categoriesInSearchBox() {
		HomePage homePage = new HomePage(driver);

		List<String> allSearchCategories = homePage.allSearchCategories();
		log4jLogger.info("Categories are :" + allSearchCategories);
		allSearchCategories.forEach(category -> {
			log4jLogger.info("Now selecting : " + category);
			homePage.selectThisSearchCategory(category);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Assert.assertEquals(category, homePage.getCurrentSearchCategory());
		});
	}
}
