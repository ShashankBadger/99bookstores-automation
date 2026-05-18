package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;
import pages.SearchPage;
import utils.ScreenshotUtil;

@Epic("Cart and Search Functionality")
@Feature("End-to-End Product Purchase Flow")
public class CartSearchAddProductTest extends BaseTest {

    @Story("Search for a product and navigate to product details")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verified that a user can search for a specific book and click on the result to open the product page.")
    @Test
    public void tc004_testSearchAndSelectProduct() {
    	
    		log.info("=========== START TEST ============");
    		
        log.info("Starting Search and Select Product test");
        
        SearchPage searchPage = new SearchPage(driver, wait);
        CartPage cartPage = new CartPage(driver, wait);
        cartPage.openCart();
        log.info("Cart opened");
        cartPage.continueShoppingFromCart();
        log.info("Navigated back to shopping");

        // Search Logic
        searchPage.openSearch();
        String words = "its starts with us";
        searchPage.searchProduct(words);
        log.info("Search keyword '"+ words +"' entered");

        WebElement firstProduct = searchPage.getFirstProductCard();
        String searchedProductName = firstProduct.getText();
        log.info("Product found: " + searchedProductName);
        
        Assert.assertFalse(searchedProductName.isEmpty(), "==Product name not visible==");
        
        firstProduct.click();
        log.info("Product clicked and navigating to Product Page");

        ScreenshotUtil.captureScreenshotForAllure(driver, "Add product to cart and verify visibility");
        
        log.info("=========== END TEST ============");
    }

    @Story("Add product to cart and verify visibility")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that the selected product can be added to the cart and is visible in the cart drawer.")
    @Test(dependsOnMethods = "tc004_testSearchAndSelectProduct")
    public void tc005_testAddToCart() {
    		
    		log.info("=========== START TEST ============");
        
    		log.info("Starting Add to Cart validation");
        
        ProductPage productPage = new ProductPage(driver, wait);
        CartPage cartPage = new CartPage(driver, wait);

        String productTitle = productPage.getProductTitle();
        Assert.assertFalse(productTitle.isEmpty(), " Product title not displayed");
        
        productPage.addToCart();
        log.info("Clicked 'Add to Cart'");

        cartPage.openCart();
        Assert.assertTrue(cartPage.isCartDisplayed(), " Cart items not visible");
        log.info("Product successfully added and verified in cart");
        
        ScreenshotUtil.captureScreenshotForAllure(driver, "Modify product quantity in cart");
        
        log.info("=========== END TEST ============");
    }

    @Story("Modify product quantity in cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validates that increasing and decreasing product quantity in the cart updates the values correctly.")
    @Test(dependsOnMethods = "tc005_testAddToCart")
    public void tc006_testUpdateQuantityInCart() throws InterruptedException {
    		
    		log.info("=========== START TEST ============");
        
    		log.info("Starting Quantity Increase/Decrease test");
        
        CartPage cartPage = new CartPage(driver, wait);

        int initialQty = cartPage.getQuantity();
        log.info("Initial quantity: " + initialQty);
        log.info("Initial price: " + cartPage.getTotalPrice());
        String initialPrice = cartPage.getTotalPrice();
        // Increase
        cartPage.increaseQuantity();
        cartPage.waitForPriceToUpdate(initialPrice);
        wait.until(d -> cartPage.getQuantity() == initialQty + 1);
        int increasedQty = cartPage.getQuantity();
        log.info("Quantity after increase: " + increasedQty);
        Assert.assertTrue(increasedQty > initialQty, " Quantity did not increase");
        
        String priceAfterIncrease = cartPage.getTotalPrice();
        log.info("Dynamic Price Update (+): " + priceAfterIncrease);
        
        // Decrease
        cartPage.decreaseQuantity();
        wait.until(d -> cartPage.getQuantity() == initialQty);
        int decreasedQty = cartPage.getQuantity();
        String priceAfterDecrease = cartPage.getTotalPrice();
        log.info("Dynamic Price Update (-): " + priceAfterDecrease);
        log.info("Quantity after decrease: " + decreasedQty);
        Assert.assertEquals(decreasedQty, initialQty, " Quantity did not decrease correctly");

        log.info("Quantity modification validated successfully");
        
        ScreenshotUtil.captureScreenshotForAllure(driver, "Search for a product and navigate to product details");
        
        log.info("=========== END TEST ============");
    }
}