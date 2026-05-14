package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By cartIcon =
            By.id("cart-icon-bubble");

    private By cartItemsContainer =
            By.id("CartDrawer-CartItems");

    private By continueShoppingBtn =
            By.xpath("//a[normalize-space()='Continue shopping']");

    private By quantityInput =
            By.xpath("//tr[contains(@id,'CartDrawer-Item')]//input");

    private By plusButton =
            By.xpath("//tr[contains(@id,'CartDrawer-Item')]//button[@name='plus']");

    private By minusButton =
            By.xpath("//tr[contains(@id,'CartDrawer-Item')]//button[@name='minus']");

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public boolean isCartDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartItemsContainer)
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

    /* =======================
       NAVIGATION ACTIONS
       ======================= */

    /** ✅ CART → PRODUCT PAGE */
    public void continueShoppingFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn)).click();
        
        //wait.until(ExpectedConditions.urlContains("/products"));
    }
    
    // Inside your CartPage class
    private By totalPriceLocator = By.cssSelector(".cart-item__price-wrapper .price--end");

    public String getTotalPrice() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading-overlay")));
        return driver.findElement(totalPriceLocator).getText().trim();
    }
    
    public void waitForPriceToUpdate(String oldPrice) {
       
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading-overlay")));
     
        wait.until(d -> !driver.findElement(By.cssSelector(".price--end")).getText().contains(oldPrice));
    }
}