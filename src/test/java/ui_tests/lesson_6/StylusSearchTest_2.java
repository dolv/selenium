package ui_tests.lesson_6;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.stylus.StylusMainPage;

import static org.testng.AssertJUnit.assertTrue;


public class StylusSearchTest_2 extends TestBase {

    private static final String PAGE_URL = "http://stylus.com.ua";
    private String searchText = "Sony Z2";
    private WebElement foundItemsNumber;
    private WebElement foundSearchElement;

    @Test
    public void foundSearchElementStylusTest() {
        
        StylusMainPage onStylusMainPage = new StylusMainPage(driver);
        onStylusMainPage.open(PAGE_URL);
        onStylusMainPage.enterSearchFieldString(searchText);
        onStylusMainPage.clickFindButton();
        foundItemsNumber = driver.findElement(By.xpath("//span[contains(concat(' ',@class,' '),' search-result ')]/span"));
        assertTrue(Integer.parseInt(foundItemsNumber.getText()) > 0);
        foundSearchElement = driver.findElement(By.xpath("//ul[contains(concat(' ',@class,' '),' product-grid ')]//a[contains(concat(' ', @href, ' '),'198721')]//span"));
        assertTrue(foundSearchElement.isDisplayed());
    }

}
