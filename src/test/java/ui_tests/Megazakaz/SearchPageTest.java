package ui_tests.Megazakaz;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class SearchPageTest {

    protected static WebDriver webDriver;

    protected static WebDriverWait wait;

    private final String MAINPAGE_URL = "http://www.megazakaz.com/";

    protected int defaultWaitingTimeInSeconds = 50;

    public void webDriverWaitUntil(By bySelector){

         new WebDriverWait(webDriver,defaultWaitingTimeInSeconds).until(ExpectedConditions.visibilityOfElementLocated(bySelector));

    }

    public void webElementClick(By bySelector){

        WebElement element = webDriver.findElement(bySelector);

        Locatable hoverItem = (Locatable) element;

        Mouse mouse = ((HasInputDevices) webDriver).getMouse();

        mouse.mouseMove(hoverItem.getCoordinates());

        element.click();

    }
    @BeforeTest
    public void setUP(){

        ProfilesIni profile = new ProfilesIni();

        FirefoxProfile ffprofile = profile.getProfile("MyTestingProfile");

        webDriver = new FirefoxDriver(ffprofile);

        wait = new WebDriverWait(webDriver, 50);

        webDriver.manage().window().maximize();

        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        webDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

    }

    @Test
    public void executeTest() throws IOException {

        webDriver.get(MAINPAGE_URL);

        assertTrue(webDriver.getCurrentUrl().equalsIgnoreCase(MAINPAGE_URL));

        webDriverWaitUntil(By.xpath("(.//*[@class='selectedTxt'])[1]"));

        webElementClick(By.xpath("(.//*[@class='selectedTxt'])[1]"));

        webDriverWaitUntil(By.xpath(".//*[@id='isearchstore-categoryfilter-form']/div/div[2]/div/ul/li[13]/a"));

        webElementClick(By.xpath(".//*[@id='isearchstore-categoryfilter-form']/div/div[2]/div/ul/li[13]/a"));

        webElementClick(By.id("edit-searchsubmit"));

        webDriverWaitUntil(By.xpath("//*[@id='maincontent']/div[3]/h1"));

        assertTrue(webDriver.findElement(By.xpath("//*[@id='maincontent']/div[3]/h1")).getText().toLowerCase().contains("Компьютеры, планшеты и нетбуки".toLowerCase()));

        webElementClick(By.xpath("//*[@id='block-ebaysearch-ebaycategories']//li[3]/a"));

        webDriverWaitUntil(By.xpath("//*[@class='morecategory']/a"));

        assertTrue(webDriver.findElement(By.xpath("//*[@id='maincontent']/div[3]/h1")).getText().toLowerCase().contains("Комплектующие и запчасти для компьютеров".toLowerCase()));

        webElementClick(By.xpath("//*[@class='morecategory']/a"));

        webDriverWaitUntil(By.xpath("//*[@id='block-ebaysearch-ebaycategories']//li[7]/a"));

        webElementClick(By.xpath("//*[@id='block-ebaysearch-ebaycategories']//li[7]/a"));

        assertTrue(webDriver.findElement(By.xpath("//*[@id='edit-brand']")).isDisplayed());

        webElementClick(By.xpath("//*[@class='form-item form-type-checkbox form-item-Brand-Intel']/span"));

        webElementClick(By.xpath(".//*[@class='form-item form-type-checkbox form-item-Processor-Type-Core-i7-4th-Gen.']/span"));

        String URI_QUERY = webDriver.getCurrentUrl();

        URI_QUERY = URI_QUERY.substring(URI_QUERY.indexOf("?"));

        webElementClick(By.xpath("//*[@class='pager']/a[3]"));

        String URI_QUERY_page2 = webDriver.getCurrentUrl();

        URI_QUERY_page2 = URI_QUERY_page2.substring(URI_QUERY_page2.indexOf("?"));

        assertTrue(URI_QUERY_page2.equalsIgnoreCase(URI_QUERY));

    }

    @AfterTest
    public void tearDown(){

        try {

            webDriver.quit();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
