package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	public static Properties props;
	
	static {
		try(InputStream fis = new FileInputStream("src/test/resources/config/config.properties")) {
			props = new Properties();
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}



