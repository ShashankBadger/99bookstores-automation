 package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

    private By searchButton = By.xpath("//summary[@aria-label='Search']");
    private By searchInput = By.xpath("//input[@id='Search-In-Modal']");

    public SearchPage(WebDriver driver, WebDriverWait wait) {
       super(driver, wait);
    }

    public void openSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void searchProduct(String keyword) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.sendKeys(keyword);
        input.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.urlContains("/search"));
    }

    public WebElement getFirstProductCard() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading-overlay")));
        System.out.println("Search results loaded, looking for first product...");
		List<WebElement> allLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			    By.cssSelector("#product-grid li:first-child .full-unstyled-link")
			));
		WebElement targetElement = allLinks.get(1);
        System.out.println("First product found: " + targetElement.getText());
        return targetElement;
    }
}
