package seleniumGrid;


import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import utils.Log4Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class GridTest {

    WebDriver driver = null;

    private StringBuffer verificationErrors = new StringBuffer();

  //  @Parameters({"platform", "browser", "version", "url"})
   // @BeforeTest(alwaysRun = true)
    public void setup(String platform, String browser, String version, String url) throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();

        //browsers
        if (browser.equalsIgnoreCase("Firefox")) {

            caps = DesiredCapabilities.firefox();


        }
        if (browser.equalsIgnoreCase("Internet Explorer")) {

            System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\target\\classes\\Drivers\\IEDriverServer32.exe");
            caps = DesiredCapabilities.internetExplorer();

        }

        if (browser.equalsIgnoreCase("Chrome")) {

            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/Drivers/chromedriver.exe");
            caps = DesiredCapabilities.chrome();

        }

        if (browser.equalsIgnoreCase("iPad")) {

            caps = DesiredCapabilities.ipad();

        }

        //platforms
        if (platform.equalsIgnoreCase("Windows")) {

            caps.setPlatform(Platform.WINDOWS);

        }

        if (platform.equalsIgnoreCase("Linux")) {

            caps.setPlatform(Platform.LINUX);

        }





        initializeLog4Test(platform, browser);
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), caps);

        driver.get(url);

    }

    @Test(description = "Test Bmi Calculator")
    public void testBmiCalculator() throws InterruptedException {

        WebElement height = driver.findElement(By.name("heightCMS"));
        height.sendKeys("181");

        WebElement weight = driver.findElement(By.name("weightKg"));
        weight.sendKeys("80");

        WebElement calculateButton = driver.findElement(By.id("Calculate"));
        calculateButton.click();

        try {

            WebElement bmi = driver.findElement(By.name("bmi"));
            assertEquals(bmi.getAttribute("value"), "24.4");

            WebElement bmi_category = driver.findElement(By.name("bmi_category"));
            assertEquals(bmi_category.getAttribute("value"), "Normal");

        } catch (Error e) {

            //Capture and append Exceptions/Errors
            verificationErrors.append(e.toString());

        }

    }

    @AfterTest
    public void afterTest() {
        //Close the browser
        driver.quit();

        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }

    }

    private void initializeLog4Test(String platform, String browser){
        try {

            System.setProperty("log4j.configurationFile", Log4Test.class.getClassLoader().getResource("log4j.properties").getPath());
            System.setProperty("SELENIUM_TEST_LOG_FILE", System.getProperty("user.dir") + "\\"+this.getClass().toString().replace("class ","")+"_"+ platform.replace(" ","_")+"_"+browser.replace(" ","_")+".log");

        }catch (Exception e){

            System.out.println("Cannot set system property log4j.configurationFile \n" + e.getMessage());

        }

    }

}


