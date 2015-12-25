package ui_tests.lesson_7;

import core.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.stylus.StylusAppStorePage;
import pages.stylus.StylusIPhone6Page;
import pages.stylus.StylusIPhonesPage;
import pages.stylus.StylusMainPage;
import static org.testng.AssertJUnit.assertTrue;


public class StylusSearchIPhone6 extends TestBase {
    private final String PAGE_URL = "http://stylus.com.ua";
    private StylusMainPage stylusMainPage;
    private StylusAppStorePage stylusAppStorePage;
    private StylusIPhonesPage stylusIPhonesPage;
    private StylusIPhone6Page stylusIPhone6Page;
    private final String MINPRICE = "15000";
    private final String MAXPRICE = "30000";
    private final By SEARCH_ELEMENT_LOCATOR = By.xpath("//a[.='Apple iPhone 6 64GB Space Gray']");

    @Test (testName = "StylusSearch")
    public void foundSearchElementStylusTest() {

        stylusMainPage = new StylusMainPage(driver);
        stylusMainPage.open(PAGE_URL);
        assertTrue(stylusMainPage.isOpened(PAGE_URL));
        stylusMainPage.clickNavMainAppStore();

        stylusAppStorePage = new StylusAppStorePage(driver);
        stylusAppStorePage.waitForTitleLoad();
        stylusAppStorePage.clickIphoneCategoryLink();

        stylusIPhonesPage = new StylusIPhonesPage(driver);
//        stylusIPhonesPage.waitForPageHeadLoad(); fails in CHROME
        stylusIPhonesPage.clickIphone6FilterLink();
        stylusIPhonesPage.clickDisplayFiltered();

        stylusIPhone6Page = new StylusIPhone6Page(driver);
        stylusIPhone6Page.waitForHead();
        stylusIPhone6Page.enterFilterPriceMin(MINPRICE);
        stylusIPhone6Page.enterFilterPriceMax(MAXPRICE);
        stylusIPhone6Page.clickOkButton();

        assertTrue(driver.findElement(SEARCH_ELEMENT_LOCATOR).isDisplayed());
    }


}
