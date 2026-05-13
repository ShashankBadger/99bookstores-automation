package utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class ScreenshotUtil
{
	private static final Logger log = LogManager.getLogger(ScreenshotUtil.class);
	
	public static void captureSceeenshot(WebDriver driver, String folder)
	{
		
		try
		{
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
			
			String path = "Screenshot/"+folder+"/Screenshot_"+timeStamp+".png";
			
			File dest = new File(path);
			
			FileUtils.copyFile(src, dest);
			
			log.info("Screenshot saved at -> " + path);
		}
		catch (Exception e)
		{
			log.warn("Failed to capture screenshot");
			e.getMessage();
		}
	}
	
	
	public static void captureScreenshotForAllure(WebDriver driver, String name) 
	{
		try
		{
			byte[] scrbytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment(name, "image/png", new ByteArrayInputStream(scrbytes),"png");
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Failure to load: "+e.getMessage());
		}
		
	}
	
	@Attachment(value = "{name}", type = "image/png")
	public static byte[] addScreenshotAllure(WebDriver driver, String name) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
}