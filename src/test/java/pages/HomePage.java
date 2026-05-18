package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{
		
	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	@FindBy(id = "HeaderMenu-home")
	WebElement headerMenuHome;
	
	@FindBy(xpath="//summary[@aria-label='Search']//span")
	WebElement searchButton;
	
	@FindBy(id="Search-In-Modal")
	WebElement searchBox;

	@FindBy(xpath = "//div[contains(@class, 'logo')]//img[contains(@alt,'99Bookstore')]")
    WebElement logo;

    @FindBy(xpath = "//summary[contains(@class, 'header__icon--search')]//span")
    WebElement searchBar;

    @FindBy(xpath = "//div[contains(@class, 'slideshow banner')]")
    WebElement saleBanner;

    @FindBy(id = "cart-icon-bubble")
    WebElement cartIcon;
    
    @FindBy(id = "HeaderMenu-product-categories")
	WebElement productCategoriesTab;
    
    @FindBy(id = "HeaderMenu-bulk-purchase")
	WebElement bulkPurchaseTab;   
    
    @FindBy(id = "HeaderMenu-about-us")
	WebElement AboutUsTab;
    
    @FindBy(id = "HeaderMenu-return-replacement")
	WebElement ReturnAndReplacementTab;
    
    @FindBy(id = "HeaderMenu-contact-us")
	WebElement 	ContactUsTab;
    
    @FindBy(id = "HeaderMenu-terms-conditions")
	WebElement 	TermsAndConditionsTab;
    
    @FindBy(id = "HeaderMenu-privacy-policy")
	WebElement 	PrivacyPolicyTab;
    
    @FindBy(xpath = "//li[contains(@class, 'ai')]//a[contains(@href, 'business')]")
    WebElement BusinessAndFinanceTab;
    
    @FindBy(xpath = "//li[contains(@class, 'ai')]//a[contains(@href, 'mythology')]")
    WebElement MythologyTab;
    
    @FindBy(className = "drawer__close")
    WebElement cartClose;
    
    
    public void headerMenuHomeTab() {
    		headerMenuHome.click();
    }
    
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
    
    public void verifyProductCategoriesTab() {
    		productCategoriesTab.click();
    }
    
    public void verifyBulkPurchaseTab() {
		bulkPurchaseTab.click();		
    }
    
    public void verifyAboutUsTab() {
    		AboutUsTab.click();    		
    }
    
    public void verifyReturnAndReplacementTab() {
    		ReturnAndReplacementTab.click();    		
    }
    
    public void verifyContactUsTab() {
    		ContactUsTab.click();    		
    }
    
    public void verifyTermsAndConditionsTab() {
    		TermsAndConditionsTab.click();    		
    }
    
    public void verifyPrivacyPolicyTab() {
    		PrivacyPolicyTab.click();    		
    }
    
    public void businessAndFinanceTab() {
    		BusinessAndFinanceTab.click();
    }
    
    public void mythologyTab() {
    	  	MythologyTab.click();    	  	
    }
    		
	public void searchText(String text)
	{
		wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
		searchBox.sendKeys(text,Keys.ENTER);
	}
	
	public void closeCart() {
		cartClose.click();
	}
	
	public boolean isCloseCartDisplay() {
		return cartClose.isDisplayed();
	}

}

