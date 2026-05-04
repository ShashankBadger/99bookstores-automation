package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.ProductCategories;

public class HomePageTest extends BaseTest {
	
	@Test(priority = 1, enabled = true)
	public void verifyHomePageElements() {
		
		log.info("Starting Home Page validation test");
		
		HomePage homePage = new HomePage(driver, wait);

        Assert.assertTrue(homePage.isLogoDisplayed(),
                "Logo is not displayed");
        log.info("Verified logo is displayed");

        Assert.assertTrue(homePage.isSearchBarDisplayed());
        log.info("Verified search bar is displayed");

        Assert.assertTrue(homePage.isSaleBannerDisplayed());
        log.info("Verified sale banner is displayed");

        Assert.assertTrue(homePage.isCartIconDisplayed());
        log.info("Verified cart icon is displayed");

        log.info("Home Page validation test completed");
		
	}
	
	@Test(priority = 2)
	public void test() {
		log.info("Test1 open");
		ProductCategories productCategories = new ProductCategories(driver, wait);
		productCategories.verifyProductCategoriesTab();
		productCategories.verifyCollectionListTab();
		productCategories.verifyFilter();
	}
	
}
