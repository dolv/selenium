package utils;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.testng.Reporter;

import java.sql.Timestamp;

public class Log4Test {
    private static final Logger LOGGER = Logger.getLogger(Log4Test.class);
//    private static final String INFO_LOG = "["+getCurrentTimestamp() +"] INFO: \"%s\"";
//    private static final String ERROR_LOG = "["+getCurrentTimestamp() +"] ERROR: \"%s\" !";
//    private static final String INFO_LOG = "INFO: \"%s\"";
//    private static final String ERROR_LOG = "ERROR: \"%s\" !";
    private static final String INFO_LOG = "\"%s\"";
    private static final String ERROR_LOG = INFO_LOG;
    private Log4Test(){

    }
//    public static String getCurrentTimestamp() {return new Timestamp(new java.util.Date().getTime()).toString();}
    public static String error (String message) {
        LOGGER.error(String.format(ERROR_LOG, message));
        Reporter.log(String.format(ERROR_LOG,message));
        return String.format(ERROR_LOG,message);
    }
    public static String info(String message) {
        LOGGER.info(String.format(INFO_LOG, message));
        Reporter.log(String.format(INFO_LOG, message));
        return String.format(INFO_LOG, message);
    }
}
