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
	
	@Test
	public void verifyAllNavigationWorks() {
		
		HomePage homePage = new HomePage(driver, wait);

		log.info("Verifying Product Categories tab...");
        homePage.verifyProductCategoriesTab();
        log.info("Product Categories tab verified successfully.");

        log.info("Verifying Bulk Purchase tab...");
        homePage.verifyBulkPurchaseTab();
        log.info("Bulk Purchase tab verified successfully.");

        log.info("Verifying About Us tab...");
        homePage.verifyAboutUsTab();
        log.info("About Us tab verified successfully.");

        log.info("Verifying Return and Replacement tab...");
        homePage.verifyReturnAndReplacementTab();
        log.info("Return and Replacement tab verified successfully.");

        log.info("Verifying Contact Us tab...");
        homePage.verifyContactUsTab();
        log.info("Contact Us tab verified successfully.");

        log.info("Verifying Terms and Conditions tab...");
        homePage.verifyTermsAndConditionsTab();
        log.info("Terms and Conditions tab verified successfully.");

        log.info("Verifying Privacy Policy tab...");
        homePage.verifyPrivacyPolicyTab();
        log.info("Privacy Policy tab verified successfully.");

        log.info("All navigation menu items verified successfully.");
	}
	
}
