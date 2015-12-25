package pages.stylus;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class StylusAppStorePage extends TestBase{

    private final String TITLE = "Apple |";
    private final By IPHONE_CATEGORY_LINK_LOCATOR = By.xpath("//div[contains(concat(' ', @class, ' '),' categories ')][1]//a[contains(concat(' ',@href,' '),'apple_iphones')]/span");
    private WebElement iphoneCategoryLink;


    public StylusAppStorePage(WebDriver driver) {
        this.driver = driver;
    }
    public void waitForTitleLoad (){
        webDriverWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), TITLE));
    }
    public void clickIphoneCategoryLink (){
        iphoneCategoryLink = driver.findElement(IPHONE_CATEGORY_LINK_LOCATOR);
        iphoneCategoryLink.click();
    }
}
