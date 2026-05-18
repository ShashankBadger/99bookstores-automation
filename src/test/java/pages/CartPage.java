package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver, WebDriverWait wait) {
       super(driver, wait);
    }
    
    @FindBy(id = "cart-icon-bubble")
    WebElement cartIcon;

    @FindBy(id = "CartDrawer-CartItems")
    WebElement cartItemsContainer;

    @FindBy(xpath = "//a[normalize-space()='Continue shopping']")
    WebElement continueShoppingBtn;

    
    @FindBy(xpath = "//tr[contains(@id,'CartDrawer-Item')]//button[@name='plus']")
    WebElement plusButton;

    @FindBy(xpath = "//tr[contains(@id,'CartDrawer-Item')]//button[@name='minus']")
    WebElement minusButton;
    
    @FindBy(css = ".cart-item__price-wrapper .price--end")
    WebElement totalPriceLocator;

    By quantityInput = By.xpath("//tr[contains(@id,'CartDrawer-Item')]//input");

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public boolean isCartDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(cartItemsContainer)
        ).isDisplayed();
    }

    public int getQuantity() {
        WebElement qty =
                wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        return Integer.parseInt(qty.getAttribute("value"));
    }

    public void increaseQuantity() {
        wait.until(ExpectedConditions.elementToBeClickable(plusButton)).click();
    }

    public void decreaseQuantity() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(minusButton)).click();
    }

    public void continueShoppingFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn)).click();
        
    }
    
    public String getTotalPrice() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading-overlay")));
        return totalPriceLocator.getText().trim();
    }
    
    public void waitForPriceToUpdate(String oldPrice) {
       
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading-overlay")));
     
        wait.until(d -> !driver.findElement(By.cssSelector(".price--end")).getText().contains(oldPrice));
    }
}