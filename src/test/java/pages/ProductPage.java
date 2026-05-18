package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    
    @FindBy(xpath = "//div[@class='product__title']//h1")
    WebElement productTitle;
    
    @FindBy(xpath = "//button[contains(@id,'ProductSubmitButton')]")
    WebElement addToCartButton;

    public String getProductTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOf(productTitle));
        return title.getText();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }
}
