package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductCategories {

	WebDriver driver;
	WebDriverWait wait;
	static Logger log = LogManager.getLogger(ProductCategories.class);
	
	public ProductCategories(WebDriver driver, WebDriverWait wait) {
		
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
		log.info("ProductCategories page initia");
		
	}
	
	@FindBy(id = "HeaderMenu-product-categories")
	WebElement productCategoriesTab;
	
	@FindBy(xpath = "//a[@href='/collections/fiction']/ancestor::li")
	WebElement fictionTab;
	
	@FindBy(xpath = "//summary[contains(@aria-label, 'Availab')]")
	WebElement availabilityFilterElement;
	
	@FindBy(xpath = "//span[contains(text(), 'In stock')]/ancestor::li")
	WebElement inStockElement;
	
	@FindBy(id = "SortBy")
	WebElement sortByElement;
	
	@FindBy(tagName = "body")
	WebElement bodyElement;
	
	@FindBy(xpath = "//span[text()='Price']/ancestor::summary[contains(@class,'caption-large' )]")
	WebElement priceFilterElement;

	@FindBy(id = "Filter-Price-GTE")
	WebElement filterPriceGTEElement;
	
	@FindBy(id = "Filter-Price-LTE")
	WebElement filterPriceLTEElement;
	
	@FindBy(xpath = "//span[contains(text(),'Remove all')]/parent::a[contains(@href,'/collections/fiction')]")
	WebElement removeAllFilterLink;
	
	@FindBy(xpath = "//span[contains(text(), 'Out of stock')]/ancestor::li")
	WebElement outOfStockElement;

	public void verifyProductCategoriesTab() {
		
		log.info("Clicking on Product Categories tab");
		wait.until(ExpectedConditions.visibilityOf(productCategoriesTab)).click();
		
		String title = driver.getTitle();
		log.info("Navigated to page with title: {}", title);
		
		Assert.assertEquals(title, "Collections – 99Bookstore", "Page is not open");
		
	}
	
	public void verifyCollectionListTab() {
		log.info("Navigating to Fiction collection");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", fictionTab);
		
		wait.until(ExpectedConditions.elementToBeClickable(fictionTab)).click();
		
		String title = driver.getTitle();
		log.info("Navigated to page with title: {}", title);
		
		Assert.assertEquals(title, "Fiction – 99Bookstore", "Page is not open");
	}
	
	public void verifyAvailabiltyFilterElement() {
		
		log.info("clicking Availability filter");
		wait.until(ExpectedConditions.elementToBeClickable(availabilityFilterElement)).click();
	}
	
	public void verifyInStockFilter() {
		
		log.info("Applying Availability filter: In Stock");
		wait.until(ExpectedConditions.elementToBeClickable(inStockElement)).click();
		
		refreshPageAfterFilterUpdate("filter.v.availability=1");
								
	}
	
	public void applySortHighToLowByURL() {
		
		log.info("Applying Sort By: Price high to low");
		
		String currentUrl = driver.getCurrentUrl();
		
		currentUrl = currentUrl.replaceAll("sort_by=[^&]*", "");
		
		String updatedUrl = currentUrl + "sort_by=price-descending";
		
		driver.get(updatedUrl);
		
		log.info("Sorting applied successfully");
	    
	}
	
	public void getProductListDetails() {

		List<WebElement> products = getFreshProductList();
		log.info("Total products in DOM: {}", products.size());
		

		for (int i = 0; i < Math.min(5, products.size()); i++) {
		        String[] value = products.get(i).getText().split("\n");
		        List<String> listValue = new ArrayList<>(Arrays.asList(value));
		        
//		        log.info(listValue.toString());
		        
		        if(listValue.get(3).contains("total reviews") && listValue.get(2).contains("(")) {
		        		listValue.remove(2);
		        		listValue.remove(3);
		        }
		        
//		        log.info(listValue.toString());
		        
		        log.info(
		            "Product details: Title: {} || Regular price: {} || Sale price: {}",
		            listValue.get(1), listValue.get(3), listValue.get(5)
		        );
		 }
		
	}
	
	public void applyAndValidatePriceFilter(int lowPrice, int highPrice) {
		
		log.info("Opening Price filter");
		priceFilterElement.click();
		
		log.info("Entering minimum price: {}",lowPrice);
		wait.until(ExpectedConditions.visibilityOf(filterPriceGTEElement)).sendKeys(""+lowPrice);
		
		log.info("Entering maximum price: {}", +highPrice);
		wait.until(ExpectedConditions.visibilityOf(filterPriceLTEElement)).sendKeys(""+highPrice);
		
		log.info("Validating products are within price range {} to {}", lowPrice, highPrice);
		boolean isBetweenPrice = productValidateBasedOnPrice(lowPrice, highPrice);
		Assert.assertTrue(isBetweenPrice, "One or more products are outside the selected price range");
		
		log.info("Price range filter validation completed successfully");
	}
	
	private boolean productValidateBasedOnPrice(int low, int high) {

		refreshPageAfterFilterUpdate("filter.v.price.gte="+low+"&filter.v.price.lte="+high);
		
		List<WebElement> products = driver.findElements(By.className("price-item--sale"));
		
		for(WebElement element : products) {
			double salePrice = Double.parseDouble(
    	            		element.getText()
    	            		.replace("Rs.", "")
    	            		.replaceAll("[^0-9.]", "")
    	        );
			
			if (salePrice < low || salePrice > high) {
	            log.warn("Out of range price: {}", salePrice);
	            return false;
	        }
		}
		
	    return true;
	}
	
	private List<WebElement> getFreshProductList() {
		
	    return wait.until(
	        ExpectedConditions.presenceOfAllElementsLocatedBy(
	            By.xpath("//ul[@id='product-grid']//li")
	        )
	    );
	}
	
	public void clickAndValidateRemoveAllFilter() {
		
		WebElement removeAllElement =
		            wait.until(ExpectedConditions.elementToBeClickable(removeAllFilterLink));
		
		log.info("Clicking 'Remove All Filters' to clear all applied filters");
		
		removeAllElement.click();

	}
	
	public void verifyOutOfStockFilter() {
		
		log.info("Applying Availability filter: Out of Stock");
		wait.until(ExpectedConditions.elementToBeClickable(outOfStockElement)).click();
		
		refreshPageAfterFilterUpdate("filter.v.availability=0");
		
	}
	
	public void productValidateBasedOutOfStock() {
		
		log.info("Validating that all displayed products are marked as 'Sold out'");
		
		List<WebElement> soldOut = driver.findElements(By.xpath("//span[text()='Sold out' and contains(@id, 'No')]"));
		
		boolean isItContainsSoldOutProd = true;
		
		for(WebElement el : soldOut) {
			String soldOutElementText = el.getText();
			if(!soldOutElementText.equalsIgnoreCase("Sold out")) {
				isItContainsSoldOutProd = false;
				break;
			}
		}
		
		log.info("Out-of-stock product validation result: {}", isItContainsSoldOutProd);
		
		Assert.assertTrue(isItContainsSoldOutProd, "The list contains in-stock products as well");
	}
	
	
	private void refreshPageAfterFilterUpdate(String expectedUrlPart) {

		wait.until(ExpectedConditions.urlContains(expectedUrlPart));
		String updatedUrl = driver.getCurrentUrl();
		log.info("Closing filter dropdown by refressing the webpage");
		driver.get(updatedUrl);
		
	}
	
}
