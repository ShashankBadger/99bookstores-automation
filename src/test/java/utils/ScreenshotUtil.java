package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	
	static final Logger log = LogManager.getLogger(ScreenshotUtil.class);
	
	public static void captureSceeenshot(WebDriver driver, String folder) {
		
		try {
			
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS")
					.format(new Date());
			
			String path = "Screenshot/"+folder+"/Screenshot"+"_"+timeStamp+".png";
			
			FileUtils.copyFile(src, new File(path));
			
			log.info("Screenshot saved at -> {}", path);
			
		} catch (Exception e) {
			
			log.error("Failed to capture screenshot");
			e.printStackTrace();
			
		}
	}

}

