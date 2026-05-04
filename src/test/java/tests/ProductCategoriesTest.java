package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.ProductCategories;

public class ProductCategoriesTest extends BaseTest {
	
	@Test(priority = 2)
	public void verifyProductCategoryFilterAndSortFlow() {
		
		log.info("Starting Product Category Filter and Sort Test");
		
		ProductCategories productCategories = new ProductCategories(driver, wait);
		
		productCategories.verifyProductCategoriesTab();
		
		productCategories.verifyCollectionListTab();
		
		productCategories.verifyFilter();
		
		productCategories.productListDetails();
		
		log.info("Completed Product Category Filter and Sort Test");
	}

}
