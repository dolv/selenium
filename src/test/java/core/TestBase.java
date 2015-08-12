package core;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ui_tests.lesson_7.TestData;
import utils.Log4Test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver driver;

    @BeforeSuite
    public void setUP()throws IOException{
        try {
            System.setProperty("log4j.configurationFile", Log4Test.class.getClassLoader().getResource("log4j.properties").getPath());
            System.setProperty("SELENIUM_TEST_LOG_FILE", System.getProperty("user.dir")+"\\application.log");

        }catch (Exception e){
            System.out.println("Cannot set system property log4j.configurationFile \n" + e.getMessage());
        }
        Log4Test.info("Instantiating a WebDriver");
        driver = WebDriverFactory.getWebDriver(TestData.BROWSER_NAME);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        switch (TestData.BROWSER_NAME) {
            case IE:
                driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
                break;
            default:
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        }
        Log4Test.info("WebDriver instance has been received.");
    }
    public WebDriverWait webDriverWait(WebDriver driver){
        Log4Test.info("WebDriverWait applied.");
        return new WebDriverWait(driver,50);
    }
    public String getCurrentTimestamp() {return new Timestamp(new java.util.Date().getTime()).toString();}
    public void logResult(boolean result){
        if (result) {
            Log4Test.info("Verification result is POSITIVE.");
        }else {
            Log4Test.info("Verification result is NEGATIVE.");
        }
    }
    @AfterSuite
    public void tearDown(){
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log4Test.info("WebDriver has quitted.");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
