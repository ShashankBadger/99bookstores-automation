package base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected static Logger log = LogManager.getLogger(BaseTest.class);
	
	@BeforeMethod
	public void setup() {
		log.info("Launching Chrome browser");
		driver = new ChromeDriver();
		driver.get("https://99bookstores.com/");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		log.info("Navigated to 99Bookstores home page");
	}
	
	@AfterMethod
	public void tearDown() {
		log.info("Closing browser");
		
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		driver.quit();
	}

}
