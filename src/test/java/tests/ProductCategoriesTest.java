package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pages.ProductCategories;
import utils.ScreenshotUtil;
@Epic("Product Categories")
public class ProductCategoriesTest extends BaseTest {
	
	@Feature("Category Navigation")
	@Story("Verify user can navigate to Product Categories and open Fiction collection")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verifies that user can open Product Categories page and navigate to Fiction collection successfully.")
	@Test
	public void tc007_VerifyProductCategoriesNavigation() {
		
		log.info("=========== START TEST ============");
		
		log.info("Starting test: Verify Product Categories Navigation");
		
		ProductCategories productCategories = new ProductCategories(driver, wait);
		String title;
		
		productCategories.verifyProductCategoriesTab();
		title = productCategories.getPageTitle();
		log.info("Navigated to page with title: {}", title);
		Assert.assertEquals(title, "Collections – 99Bookstore", "Page is not open");
		
		productCategories.verifyCollectionListTab();
		title = productCategories.getPageTitle();
		log.info("Navigated to page with title: {}", title);
		Assert.assertEquals(title, "Fiction – 99Bookstore", "Page is not open");
		
		ScreenshotUtil.captureScreenshotForAllure(driver, "Product Categories Navigation");
		
		log.info("Completed test: Product Categories Navigation verified successfully");
		
		log.info("=========== END TEST ============");
		
	}

	@Feature("Availability Filter")
	@Story("Verify user can apply In-Stock filter")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verifies that user can apply In-Stock availability filter and view only available products.")
	@Test(dependsOnMethods = "tc007_VerifyProductCategoriesNavigation")
	public void tc008_testInStockFilter() {
		
		log.info("=========== START TEST ============");
		
		log.info("Starting test: Verify In-Stock filter functionality");
		
		ProductCategories productCategories = new ProductCategories(driver, wait);
		
		productCategories.verifyAvailabiltyFilterElement();
		productCategories.verifyInStockFilter();
		
		boolean isApplied = productCategories.verifyInStockFilterApply();
		Assert.assertTrue(isApplied, "In Stock Filter is not applied");
		
		ScreenshotUtil.captureScreenshotForAllure(driver, "In Stock Filter");
		
		log.info("Completed test: In-Stock filter applied and verified successfully");
		
		log.info("=========== END TEST ============");

	}

	@Feature("Price Filter")
	@Story("Verify user can filter products based on price range")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verifies that user can apply price filter and see only products within the selected price range.")
	@Test(dependsOnMethods = "tc008_testInStockFilter")
	public void tc009_testPriceFilterValidation() {
		
		log.info("=========== START TEST ============");
		
		log.info("Starting test: Validate price filter functionality");
		
		ProductCategories productCategories = new ProductCategories(driver, wait);
				
		productCategories.applyAndValidatePriceFilter(300, 1200);
		
		log.info("Validating products are within price range {} to {}", 300, 1200);
		boolean isBetweenPrice = productCategories.productValidateBasedOnPrice(300, 1200);
		Assert.assertTrue(isBetweenPrice, "One or more products are outside the selected price range");
		
		ScreenshotUtil.captureScreenshotForAllure(driver, "Price Filter");
		
		log.info("Completed test: Price filter applied and validated successfully");
		
		log.info("=========== END TEST ============");
	}

	@Feature("Category Navigation, Filters and Sorting")
	@Story("Verify user can navigate to a category, apply availability filter, and sort products by price")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verifies that user can open Product Categories, navigate to Fiction collection, apply In-Stock availability filter, sort products by Price (High to Low), and view the updated product list.")
	@Test(dependsOnMethods = "tc009_testPriceFilterValidation")
	public void tc010_verifyProductCategoryFilterAndSortFlow() {
		
		log.info("=========== START TEST ============");
		
		ProductCategories productCategories = new ProductCategories(driver, wait);
		
		log.info("Starting test: Verify complete product category filter and sorting flow");
		
		productCategories.applySortHighToLowByURL();
		
		productCategories.getProductListDetails();
				
		log.info("Validating products are within price range {} to {}", 300, 1200);
		boolean isBetweenPrice = productCategories.productValidateBasedOnPrice(300, 1200);
		Assert.assertTrue(isBetweenPrice, "One or more products are outside the selected price range");
		
		ScreenshotUtil.captureScreenshotForAllure(driver, "All Filter is added");
				
		log.info("Completed test: Product category filtering and sorting flow executed successfully");
		
		log.info("=========== END TEST ============");
	}

	@Feature("Availability Filter")
	@Story("Verify user can apply Out-of-Stock filter")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verifies that user can apply Out-of-Stock filter and view only products marked as Sold Out.")
	@Test
	public void tc011_testOutOfStockFilterValidation() {
		
		log.info("=========== START TEST ============");
		
		ProductCategories productCategories = new ProductCategories(driver, wait);
		
		log.info("Starting test: Verify Out-of-Stock filter functionality");
		
		productCategories.verifyProductCategoriesTab();
		
		productCategories.verifyCollectionListTab();
		
		productCategories.verifyAvailabiltyFilterElement();
		
		productCategories.verifyOutOfStockFilter();
		
		boolean isApplied = productCategories.verifyOutStockFilterApply();
		Assert.assertTrue(isApplied, "Out Stock Filter is not applied");
		
		boolean isItContainsSoldOutProd = productCategories.productValidateBasedOutOfStock();
		
		log.info("Out-of-stock product validation result: {}", isItContainsSoldOutProd);
		Assert.assertTrue(isItContainsSoldOutProd, "The list contains in-stock products as well");
		
		ScreenshotUtil.captureScreenshotForAllure(driver, "Out of Stock Filter");
		
		log.info("Completed test: Out-of-Stock filter applied and validated successfully");
		
		log.info("=========== END TEST ============");
	}

}
