package pages.stylus;


import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;

public class StylusMainPage extends TestBase{

    private final String PAGE_TITLE = "stylus.com.ua";
    private final By SEARCH_FIELD_LOCATOR = By.name("q");
    private WebElement searchField;
    private final By SEARCH_BUTTON_LOCATOR = By.xpath("//div[contains(concat(' ',@class,' '),' search ')]//input[@type='submit']");
    private WebElement searchButton;
    private WebDriver driver;
    private WebElement navMainAppStore;
    private final By NAV_MAIN_APPSTORE_LOCATOR = By.xpath("//span[contains(text(),'Apple Store')]");



    public StylusMainPage(WebDriver driver) {
        this.driver = driver;
     }

    public void open(final String PAGE_URL){
//        try {
          webDriverWait(driver).until(ExpectedConditions.titleContains(PAGE_TITLE));
//        }catch (){
//
//        }
    }
    public boolean isOpened(String PAGE_URL){
        return driver.getCurrentUrl().contains(PAGE_URL);
    }
    public void enterSearchFieldString(String searchString){
        searchField = driver.findElement(SEARCH_FIELD_LOCATOR);
        searchField.sendKeys(searchString);
    }
    public void clickFindButton(){
        searchButton = driver.findElement(SEARCH_BUTTON_LOCATOR);
        searchButton.click();
    }
    public void clickNavMainAppStore(){
        navMainAppStore = driver.findElement(NAV_MAIN_APPSTORE_LOCATOR);
        navMainAppStore.click();
    }
}
