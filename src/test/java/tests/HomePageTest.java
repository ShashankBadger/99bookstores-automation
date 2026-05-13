package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pages.HomePage;
import pages.ProductCategories;
import utils.ScreenshotUtil;

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
        
        ScreenshotUtil.captureSceeenshot(driver, "Home");

        log.info("Home Page validation test completed");
	}
	
	@Epic("Home Page")
	@Feature("Home Page UI Validation")
	@Story("Verify navigation in the 99BookStore")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify that all navigation tabs on the Home Page work correctly.")
	@Test(priority = 2, enabled = true)
	public void verifyAllNavigationWorks() {
		
		HomePage homePage = new HomePage(driver, wait);
		String title;

		log.info("Verifying Product Categories tab...");
        homePage.verifyProductCategoriesTab();
        ScreenshotUtil.captureSceeenshot(driver, "Home");
        title = homePage.getPageTitle();
        Assert.assertEquals(title, "Collections – 99Bookstore");	
        log.info("Product Categories tab verified successfully.");

        log.info("Verifying Bulk Purchase tab...");
        homePage.verifyBulkPurchaseTab();
        ScreenshotUtil.captureSceeenshot(driver, "Home");
        title = homePage.getPageTitle();
        Assert.assertEquals(title, "Bulk Purchase – 99Bookstore");
        log.info("Bulk Purchase tab verified successfully.");

        log.info("Verifying About Us tab...");
        homePage.verifyAboutUsTab();
        ScreenshotUtil.captureSceeenshot(driver, "Home");
        title = homePage.getPageTitle();
        Assert.assertEquals(title, "About Us – 99Bookstore");
        log.info("About Us tab verified successfully.");

        log.info("Verifying Return and Replacement tab...");
        homePage.verifyReturnAndReplacementTab();
        ScreenshotUtil.captureSceeenshot(driver, "Home");
        title = homePage.getPageTitle();
        Assert.assertEquals(title, "Refund policy – 99Bookstore");
        log.info("Return and Replacement tab verified successfully.");

        log.info("Verifying Contact Us tab...");
        homePage.verifyContactUsTab();
        ScreenshotUtil.captureSceeenshot(driver, "Home");
        title = homePage.getPageTitle();
        Assert.assertEquals(title, "Contact information – 99Bookstore");
        log.info("Contact Us tab verified successfully.");

        log.info("Verifying Terms and Conditions tab...");
        homePage.verifyTermsAndConditionsTab();
        ScreenshotUtil.captureSceeenshot(driver, "Home");
        title = homePage.getPageTitle();
        Assert.assertEquals(title, "Terms of service – 99Bookstore");
        log.info("Terms and Conditions tab verified successfully.");

        log.info("Verifying Privacy Policy tab...");
        homePage.verifyPrivacyPolicyTab();
        ScreenshotUtil.captureSceeenshot(driver, "Home");
        title = homePage.getPageTitle();
        Assert.assertEquals(title, "Privacy policy – 99Bookstore");
        log.info("Privacy Policy tab verified successfully.");

        log.info("All navigation menu items verified successfully.");
	}
	
	@Epic("Home Page")
	@Feature("Business & Finance Navigation")
	@Story("Verify Business & Finance tab navigation")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Ensures that clicking on the Business & Finance tab loads the correct product category list.")
	@Test(priority = 2, enabled = true)
	public void verifyBusinessAndFinance() {
	    log.info("Starting Business & Finance tab verification...");

	    HomePage homePage = new HomePage(driver, wait);
	    ProductCategories productCategories = new ProductCategories(driver, wait);

	    homePage.businessAndFinanceTab();
	    String title = homePage.getPageTitle();
	    Assert.assertEquals(title, "Business and Finance – 99Bookstore");
	    log.info("Clicked on Business & Finance tab.");

	    productCategories.getProductListDetails();
	    log.info("Retrieved product list details for Business & Finance category.");

	    ScreenshotUtil.captureSceeenshot(driver,"Business And Finance");
	    log.info("Business & Finance tab verification completed successfully.");
	}


	@Epic("Home Page")
	@Feature("Mythology Navigation")
	@Story("Verify Mythology tab navigation")
	@Severity(SeverityLevel.NORMAL)
	@Description("Ensures that clicking on the Mythology tab loads the correct product category list.")
	@Test(priority = 2, enabled = true)
	public void verifyMythology() {
	    log.info("Starting Mythology tab verification...");

	    ProductCategories productCategories = new ProductCategories(driver, wait);
	    HomePage homePage = new HomePage(driver, wait);

	    homePage.mythologyTab();
	    String title = homePage.getPageTitle();
	    Assert.assertEquals(title,"Mythology – 99Bookstore");
	    log.info("Clicked on Mythology tab.");

	    productCategories.getProductListDetails();
	    log.info("Retrieved product list details for Mythology category.");

	    ScreenshotUtil.captureSceeenshot(driver, "Mythology");
	    log.info("Mythology tab verification completed successfully.");
	}

}
