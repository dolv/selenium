package ui_tests.lesson_5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;
//import static org.testng.Assert.*;

public class StylusTest {

    private WebDriver driver;
    private FirefoxDriver firefoxDriver;
    private WebElement searchField;
    private String searchText;
    private WebElement[] foundItemList;
    private WebElement searchButton;
    private WebElement foundItemsNumber;
    private boolean LinkTextContainsAllSearchWords = false;

    @BeforeTest
    public void setUp(){
        searchText = "Sony Z2";
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://stylus.com.ua");
    }

    @Test
    public void foundNumberStylusTest() {
        searchField = driver.findElement(By.name("q"));
        searchField.sendKeys(searchText);
        searchButton = driver.findElement(By.xpath(".//*[@id='header']/div[2]/div[2]/form/input[2]"));
        searchButton.click();
        foundItemsNumber = driver.findElement(By.xpath(".//*[@id='content']/span/span"));
        assertTrue(Integer.parseInt(foundItemsNumber.getText()) > 0);
    }

    @Test ( dependsOnMethods = {"foundNumberStylusTest"},enabled = false)
    public void searchLinkExistenceTestByClassName() {
        String[] wList = searchText.split("\\s+");
        int words;
        List<WebElement> foundItemList = driver.findElements(By.className("title"));

        for (WebElement ele : foundItemList) {
            words = 0;
            for(String word : wList){
                if (ele.getText().contains(word)) {
                    words ++;
                } else {break;}
            }
            if (words == wList.length) {
                LinkTextContainsAllSearchWords = true;
                break;
            }
        }
        assertTrue(LinkTextContainsAllSearchWords);
    }
    @Test ( dependsOnMethods = {"foundNumberStylusTest"},enabled = true)
    public void searchLinkExistenceTestByPartialLinkText() {
        String[] wList = searchText.split("\\s+");
        int words;
        List<WebElement> foundItemList = driver.findElements(By.partialLinkText(wList[0]));
        if (wList.length>1) {
            wList = Arrays.copyOfRange(wList, 1, wList.length);

            for (WebElement ele : foundItemList) {
                words = 0;
                for(String word : wList){
                    if (ele.getText().contains(word)) {
                        words ++;
                    } else {break;}
                }
                if (words == wList.length) {
                    LinkTextContainsAllSearchWords = true;
                    break;
                }
            }
        } else {
            LinkTextContainsAllSearchWords = true;
        }
        assertTrue(LinkTextContainsAllSearchWords);
    }
    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
