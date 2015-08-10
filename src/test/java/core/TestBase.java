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
        Log4Test.info(getCurrentTimestamp()+": Instantiating a WebDriver");
        driver = WebDriverFactory.getWebDriver(TestData.BROWSER_NAME);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        Log4Test.info(getCurrentTimestamp() + ": WebDriver has being instantiated.");
    }
    public WebDriverWait webDriverWait(WebDriver driver){
        return new WebDriverWait(driver,50);
    }
    public String getCurrentTimestamp() {return new Timestamp(new java.util.Date().getTime()).toString();}
    @AfterSuite
    public void tearDown(){
        driver.close();
        Log4Test.info(getCurrentTimestamp() + ": WebDriver browser tab has closed.");
        driver.quit();
        Log4Test.info(getCurrentTimestamp() + ": WebDriver has quitted.");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
