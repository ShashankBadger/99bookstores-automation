package tests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.Browsers;
import utils.ConfigReader;

public class BaseTest {
	
	WebDriver driver;
	WebDriverWait wait;
	static Logger log = LogManager.getLogger(BaseTest.class);
	
	@BeforeMethod
	public void setup() {
		
		log.info("Launching Chrome browser");
		
		driver = Browsers.getDriver("browser");
		driver.manage().window().maximize();
		
		String baseUrl = ConfigReader.getProperty("baseUrl");
		driver.get(baseUrl);
		
		Long timeouts = Long.parseLong(ConfigReader.getProperty("timeout"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		
		log.info("Navigated to 99Bookstores home page");
	}
	
	@AfterMethod
	public void tearDown() {
		
		log.info("Closing browser");
		driver.quit();
		
	}

}
