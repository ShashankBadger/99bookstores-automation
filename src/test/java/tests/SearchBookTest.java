package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pages.HomePage;
import pages.SearchInput;
import utils.ExcelUtils;
import utils.ScreenshotUtil;

@Epic("Book Search Automation")
@Feature("Search Functionality")
public class SearchBookTest extends BaseTest
{
		
	@Story("Verify user can find books with valid input")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify books are displayed and verifies the total price for the items in cart.")
	@Test(priority = 1, dataProvider = "validSearchData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc013_searchUsingValidText(int rowIndex, String testCase, String input, String expectedResult, String excelPath)
	{

		 log.info("=========== START TEST : {} ===========", testCase);
		 log.info("Search input received: {}", input);

		ExcelUtils excel = new ExcelUtils(excelPath, "Search_Input");
		
		HomePage home = new HomePage(driver, wait);
				
		home.searchText(input);
		
		log.info("Book searched with input: {}", input);
		
		SearchInput searchInput = new SearchInput(driver, wait);
	
		boolean hasSearchResult = searchInput.hasSearchResults();
		log.info("Search result displayed: {}", hasSearchResult);
		
		Assert.assertTrue(hasSearchResult, "Expected product(s) to be displayed");
		searchInput.listOutProducts();
		log.info("Product list displayed successfully");

//		Add two books to the cart
		searchInput.addFirstTwoBooks();
		log.info("First two books added to cart");
		
//		Add quantity of the book
		searchInput.addQuantityOfBooks(2);
		log.info("Book quantity increased to 2");
		
		Assert.assertTrue(searchInput.validateTotal(),"Cart total should match sum of item prices");
		
		excel.writeCellData(rowIndex, 4, "Books found for "+input+" as expected");
		excel.writeCellData(rowIndex, 5, "Pass");
		
		

		log.info("Excel updated with PASS status");
		ScreenshotUtil.captureScreenshotForAllure(driver, "Success Result For Valid text");
		
		log.info("=========== END TEST : {} ===========", testCase);

	}
	
	@Story("Verifies user can't find book with invalid input")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify that no books are displayed for invalid search.")
	@Test(priority = 2, dataProvider = "invalidSearchData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc013_searchUsingInvalidText(int rowIndex, String testCase, String input, String expectedResult, String excelPath)
	{
		log.info("=========== START TEST : {} ===========", testCase);
		log.info("Search input received (invalid): {}", input);

		ExcelUtils excel = new ExcelUtils(excelPath, "Search_Input");
		HomePage home = new HomePage(driver, wait);
		
		if(home.isCloseCartDisplay()) {
			home.closeCart();
		}
		
		home.headerMenuHomeTab();
		
		home.searchText(input);
		
		log.info("Search performed with invalid input: {}", input);

		SearchInput searchInput = new SearchInput(driver, wait);
		
		Assert.assertFalse(searchInput.hasSearchResults(), "Products should not be displayed for invalid search");

		excel.writeCellData(rowIndex, 4, "No results found for "+input+". Check the spelling or use a different word or phrase.");
		excel.writeCellData(rowIndex, 5, "Pass");

        log.info("Excel updated with PASS status for invalid search");
        ScreenshotUtil.captureScreenshotForAllure(driver, "Success Result For Invalid text");
        
        log.info("=========== END TEST : {} ===========", testCase);

	}
}
