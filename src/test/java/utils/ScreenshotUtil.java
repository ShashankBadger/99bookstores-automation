package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	public static void captureSceeenshot(WebDriver driver, String folder) {
		
		try {
			
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS")
					.format(new Date());
			
			String path = "Screenshot/"+folder+"/Screenshot"+"_"+timeStamp+".png";
			
			FileUtils.copyFile(src, new File(path));
			
			System.out.println("INFO: Screenshot saved at -> " + path);
			
		} catch (Exception e) {
			
			System.out.println("ERROR: Failed to capture screenshot");
			e.printStackTrace();
		}
	}
}

