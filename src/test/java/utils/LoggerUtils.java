package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {

    // Returns a logger instance for the calling class
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
}
