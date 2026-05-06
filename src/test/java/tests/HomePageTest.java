package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pages.HomePage;

public class HomePageTest extends BaseTest {
	
	@Epic("Home Page")
	@Feature("Home Page UI Validation")
	@Story("Verify important UI elements are visible on Home Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verifies that Home Page loads successfully and key UI elements such as logo, search bar, sale banner, and cart icon are displayed correctly.")
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
	
}
