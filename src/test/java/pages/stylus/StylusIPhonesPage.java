package pages.stylus;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.AssertJUnit.assertTrue;

public class StylusIPhonesPage extends TestBase{

    private final String HEAD = "Apple iPhone";
    private final By PAGE_HEAD_LOCATOR = By.xpath("//h1");
    private WebElement pageHead;
    private final By IPHONE6_FILTER_LINK_LOCATOR = By.xpath("//label[@for='check144-2362']");
    private WebElement iphone6FilterLink;
    private final By FILTER_PRICE_MIN_LOCATOR = By.id("min");
    private WebElement filterPriceMin;
    private final By FILTER_PRICE_MAX_LOCATOR = By.id("max");
    private WebElement filterPriceMax;
    private final By SHOW_FILTERED_LINK_LOCATOR = By.xpath("//div[@class='tooltip filter right']/a");
    private WebElement showFiltered;

    public StylusIPhonesPage (WebDriver driver){
        this.driver = driver;
    }
    public void waitForPageHeadLoad (){
        webDriverWait(driver).until(ExpectedConditions.textToBePresentInElement(driver.findElement(PAGE_HEAD_LOCATOR),HEAD));
        assertTrue(driver.findElement(PAGE_HEAD_LOCATOR).getText().contains(HEAD));
    }

    public void clickIphone6FilterLink (){
        iphone6FilterLink = driver.findElement(IPHONE6_FILTER_LINK_LOCATOR);
        iphone6FilterLink.click();
    }
    public void clickDisplayFiltered(){
        showFiltered = driver.findElement(SHOW_FILTERED_LINK_LOCATOR);
        showFiltered.click();
    }
    public void enterFilterPriceMin (String minPrice){
        filterPriceMin = driver.findElement(FILTER_PRICE_MIN_LOCATOR);
        filterPriceMin.sendKeys(minPrice);
    }
    public void enterFilterPriceMax (String maxPrice){
        filterPriceMax = driver.findElement(FILTER_PRICE_MAX_LOCATOR);
        filterPriceMax.sendKeys(maxPrice);
    }
    public String getPageHeadText(){
        pageHead = driver.findElement(PAGE_HEAD_LOCATOR);
        return pageHead.getText();
    }
}
