package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
	

	@FindBy(xpath = "//ul[@id='product-grid']//li")
	List<WebElement> productList;

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
	
	public void verifyFilter() {
		
		wait.until(ExpectedConditions.elementToBeClickable(availabilityFilterElement)).click();
		
		log.info("Applying Availability filter: In Stock");
		wait.until(ExpectedConditions.elementToBeClickable(inStockElement)).click();
		
		actionsClickOutside();
				
		log.info("Applying Sort By: Price high to low");
		sortHighToLowByURL();
		
	}
	
	public void productListDetails() {
		
		log.info("Total products displayed: {}", productList.size());
		
//		for(WebElement el : productList) {
//			String value[] = el.getText().split("\n");
//			log.info("Product details: Title: {} || Regular price: {} || Sale price: {}", value[1], value[3], value[5]);
//		}
		
		for(int i = 0; i < 5; i++) {
			
			String value[] = productList.get(i).getText().split("\n");
			log.info("Product details: Title: {} || Regular price: {} || Sale price: {}", value[1], value[3], value[5]);
		}
		
	}
	

	private void actionsClickOutside() {
		
		log.info("Closing filter dropdown by pressing ESC");
	    Actions actions = new Actions(driver);
	    actions.sendKeys(Keys.ESCAPE).perform();
	    
	}
	
	public void sortHighToLowByURL() {
		
	    String currentUrl = driver.getCurrentUrl();

	    if (!currentUrl.contains("sort_by=price-descending")) {
	        driver.get(currentUrl + (currentUrl.contains("?") ? "&" : "?")
	                + "filter.v.availability=1&filter.v.price.gte=&filter.v.price.lte=&sort_by=price-descending");
	    }

	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	            By.xpath("//ul[@id='product-grid']//li")));
	    
	}


}
