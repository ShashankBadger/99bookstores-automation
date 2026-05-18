package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchInput extends BasePage
{
	private String firstProductName;
	private String secondProductName;
	
	public SearchInput(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
//	------------------ Product result page ------------------
	@FindBy(xpath="//div[@id='ProductGridContainer']")
	private WebElement productContainer;
	
	@FindBy(xpath="//div[@class='card__information']//a")
	private List<WebElement> productCards;
	
//	------------------ Cart box ------------------
	@FindBy(xpath="//button[@type='submit']")
	private WebElement addToCart;
	
	@FindBy(id = "CartDrawer-Overlay")
    private WebElement cartDrawerOverlay;

    @FindBy(xpath = "//button[@class='drawer__close']")
    private WebElement drawerCloseButton;

//	------------------ Cart details ------------------

    @FindBy(id = "cart-icon-bubble")
    private WebElement cartIcon;
    
    @FindBy(xpath = "//tbody[@role='rowgroup']//tr")
    private List<WebElement> cartRows;
    
    @FindBy(xpath = "//div[@class='totals']//p")
    private WebElement totalPriceText;
    
    public boolean hasSearchResults()
    {
    	try
    	{
    		return productContainer.isDisplayed();
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }
	
	public void listOutProducts()
	{
		
		wait.until(ExpectedConditions.visibilityOf(productContainer));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", productContainer);
	}
	
	public void addFirstTwoBooks()
	{
		WebElement firstProduct = productCards.get(1);
		WebElement secondProduct = productCards.get(3);
		
		firstProductName = firstProduct.getText();
		secondProductName = secondProduct.getText();
		
		addToCart(firstProduct);
		addToCart(secondProduct);
		driver.get(driver.getCurrentUrl());
	}
	
	private void addToCart(WebElement product)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);
		addToCart.click();
		        		

		By overlayLocator = By.id("CartDrawer-Overlay");
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
		    By.className("loading-overlay")
		));
		
		// Now safely re-fetch element
		WebElement overlay = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(overlayLocator)
		);
		
		overlay.click();

		
		driver.navigate().back();
	}
	
	public void addQuantityOfBooks(int quantity)
	{
		wait.until(ExpectedConditions.visibilityOf(cartIcon));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartIcon);
		
		// First product
	    WebElement firstRow = findCartRowByProductName(firstProductName);
	    increaseQuantity(firstRow, quantity);

	    // Second product (REFETCH after DOM updates)
	    WebElement secondRow = findCartRowByProductName(secondProductName);
		increaseQuantity(secondRow, quantity);
	}
	
	private WebElement findCartRowByProductName(String productName)
	{
	    wait.until(ExpectedConditions.visibilityOfAllElements(cartRows));
	    for (WebElement row : cartRows)
	    {
	        String name = row.findElement(By.xpath(".//td[2]//a")).getText();
	        if (name.equalsIgnoreCase(productName))	return row;
	    }

	    throw new RuntimeException("Product not found in cart: " + productName);
	}
	
	private void increaseQuantity(WebElement row, int quantity)
	{
	    for (int i = 1; i < quantity; i++)
	    {
	        WebElement priceElement = row.findElement(By.xpath(".//span[contains(@class,'price')]"));
	        String oldPrice = priceElement.getText();

	        WebElement plusButton = row.findElement(By.xpath(".//button[@name='plus']"));
	        plusButton.click();

	        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(priceElement, oldPrice)));
	        wait.until(ExpectedConditions.stalenessOf(row));
	    }
	}
		
	private Double trimValue(String price)
	{
		String res;
		res = price.substring(price.indexOf(' ')+1).trim();
		return Double.parseDouble(res);
	}
	
	public Boolean validateTotal()
	{
		Double amount = 0.0;
		
		By itemsLocator = By.xpath("//tbody[@role='rowgroup']//tr");
		List<WebElement> rows = driver.findElements(itemsLocator);
		for(WebElement row : rows)
		{
			String price = row.findElement(By.xpath(".//span[contains(@class, 'price')]")).getText();
			amount += trimValue(price);
		}
		
		String totalPrice = driver.findElement(By.xpath("//div[@class='totals']//p")).getText();
		Double total = trimValue(totalPrice);		
		
		return Math.abs(total - amount) < 0.01;
	}
	
}
