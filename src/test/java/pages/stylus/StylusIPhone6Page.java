package pages.stylus;

import core.BrowserTypes;
import core.TestBase;
import core.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StylusIPhone6Page extends TestBase{

    private final String HEAD = "iPhone 6";
    private final By PAGE_HEAD_LOCATOR = By.xpath("//h1");
    private WebElement pageHead;
    private final By FILTER_PRICE_MIN_LOCATOR = By.id("min");
    private WebElement filterPriceMin;
    private final By FILTER_PRICE_MAX_LOCATOR = By.id("max");
    private WebElement filterPriceMax;
    private final By OK_BUTTON_LOCATOR = By.xpath("//input[@type='submit'][@value='OK']");
    private WebElement okButton;

    public StylusIPhone6Page(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFilterPriceMin (String minPrice){
        filterPriceMin = driver.findElement(FILTER_PRICE_MIN_LOCATOR);
        if (BrowserTypes.IE.equals(TestData.BROWSER_NAME))
            new Actions(driver).moveToElement(filterPriceMin).perform();
        filterPriceMin.clear();
        filterPriceMin.sendKeys(minPrice);
    }

    public void enterFilterPriceMax (String maxPrice){
        filterPriceMax = driver.findElement(FILTER_PRICE_MAX_LOCATOR);
        if (BrowserTypes.IE.equals(TestData.BROWSER_NAME))
            new Actions(driver).moveToElement(filterPriceMax).perform();
        filterPriceMax.clear();
        filterPriceMax.sendKeys(maxPrice);
    }
    public void clickOkButton(){
        okButton = driver.findElement(OK_BUTTON_LOCATOR);
        okButton.click();}

    public String getPageHeadText(){
        pageHead = driver.findElement(PAGE_HEAD_LOCATOR);
        return pageHead.getText();
    }
    public void waitForHead (){
        webDriverWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(PAGE_HEAD_LOCATOR, HEAD));
    }
    public void waitForFilterPriceMin (String text){
        webDriverWait(driver).until(ExpectedConditions.textToBePresentInElementValue(FILTER_PRICE_MIN_LOCATOR, text));
    }
    public void waitForFilterPriceMax (String text){
        webDriverWait(driver).until(ExpectedConditions.textToBePresentInElementValue(FILTER_PRICE_MAX_LOCATOR, text));
    }
}
