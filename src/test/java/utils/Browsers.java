package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browsers
{
	private static WebDriver driver = null;
	
	public static WebDriver getDriver(String key)
	{
		String browser = ConfigReader.getProperty(key);
		
		switch (browser.toLowerCase()) 
		{
			case "chrome":
				return new ChromeDriver();
			case "firefox":
				return  new FirefoxDriver();
			case "edge":
				return new EdgeDriver();
			default :
				return new ChromeDriver();
		}
	}
	
	public static void quitDriver()
	{
		driver.quit();
	}
	
	public static void closeDriver()
	{
		driver.close();
	}
}
