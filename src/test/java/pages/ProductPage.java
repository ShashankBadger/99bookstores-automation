package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;

    private By productTitle = By.xpath("//div[@class='product__title']//h1");
    private By addToCartButton = By.xpath("//button[contains(@id,'ProductSubmitButton')]");

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getProductTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
        return title.getText();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }
}
