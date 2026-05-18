package tests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import utils.Browsers;
import utils.ConfigReader;

@Listeners(utils.TestListener.class)
public class BaseTest {
	

	protected static WebDriver driver;
	protected WebDriverWait wait;
	public Logger log;
	
	@BeforeClass
	public void setup() {
		
		log = LogManager.getLogger(this.getClass());
				
		log.info("Launching Chrome browser");
		
		driver = Browsers.getDriver("browser");
		driver.manage().window().maximize();
		
		String baseUrl = ConfigReader.getProperty("baseUrl");
		driver.get(baseUrl);
		
		Long timeouts = Long.parseLong(ConfigReader.getProperty("timeout"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		
		log.info("Navigated to 99Bookstores home page");
	}
	
	@AfterClass
	public void tearDown() {
		
		log.info("Closing browser");
		driver.quit();
		
	}
	
	public static WebDriver getDriver()
	{
		return driver;
	}
}
