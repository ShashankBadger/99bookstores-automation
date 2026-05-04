package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public HomePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = "//div[contains(@class, 'logo')]//img[contains(@alt,'99Bookstore')]")
    WebElement logo;

    @FindBy(xpath = "//summary[contains(@class, 'header__icon--search')]//span")
    WebElement searchBar;

    @FindBy(xpath = "//div[contains(@class, 'slideshow banner')]")
    WebElement saleBanner;

    @FindBy(id = "cart-icon-bubble")
    WebElement cartIcon;
    

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }
    

    public boolean isSearchBarDisplayed() {
        return searchBar.isDisplayed();
    }
    

    public boolean isSaleBannerDisplayed() {
        return saleBanner.isDisplayed();
    }

    public boolean isCartIconDisplayed() {
        return cartIcon.isDisplayed();
    }	

}
