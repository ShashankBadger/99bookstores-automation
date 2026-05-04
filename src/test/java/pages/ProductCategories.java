package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
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
	}
	
	@FindBy(id = "HeaderMenu-product-categories")
	WebElement productCategories;
	
	@FindBy(xpath = "//ul[contains(@class, 'collection-list')]//li[1]")
	WebElement collectionList1;
//	
//	@FindBy(xpath = "Fiction")
//	WebElement collectionList1;
	
	@FindBy(linkText = "Preloved Fiction")
	WebElement collectionList2;
	
	@FindBy(xpath = "//summary[contains(@aria-label, 'Availab')]")
	WebElement availabilityFilter;
	
	@FindBy(xpath = "//span[contains(text(), 'In stock')]/ancestor::li")
	WebElement inStock;
	
	
	public void verifyProductCategoriesTab() {
		wait.until(ExpectedConditions.visibilityOf(productCategories)).click();
		String title = driver.getTitle();
		Assert.assertEquals(title, "Collections – 99Bookstore", "Page is not open");
	}
	
	public void verifyCollectionListTab() {
		wait.until(ExpectedConditions.elementToBeClickable(collectionList2)).click();
		String title = driver.getTitle();
		log.info(title);
		Assert.assertEquals(title, "Preloved Fiction – 99Bookstore", "Page is not open");
	}
	
	public void verifyFilter() {
		availabilityFilter.click();
		inStock.click();
//		Actions actions = new Actions(driver);
//		actions.moveByOffset(10, 10).click().perform();
		driver.findElement(By.tagName("body")).click();

	}
	
	
	


}
