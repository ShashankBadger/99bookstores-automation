package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	WebDriver driver;
	WebDriverWait wait;
	static Logger log = LogManager.getLogger(HomePage.class);
	
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
    
    public String getPageTitle() {
    		return driver.getTitle();
    }
      
}  
