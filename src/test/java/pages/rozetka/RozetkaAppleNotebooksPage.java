package pages.rozetka;

import core.BrowserTypes;
import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui_tests.lesson_7.TestData;
import utils.Log4Test;
import java.util.List;

public class RozetkaAppleNotebooksPage extends TestBase{

    private final By MANUFACTURE_CATEGORY_APPLE_CHECKBOX_LOCATOR=By.xpath(".//*[@id='sort_producer']/li[@class='filter-parametrs-i-l-i']");

    private WebElement manufactureCategoryAppleCheckBox;

    private final By SORT_DROP_LINK_LOCATOR = By.name("drop_link");

    private WebElement sortDropLink;

    private final By SORT_DROP_MENU_LOCATOR = By.name("drop_menu");

    private WebElement sortDropMenu;

    private WebElement blockWithGoods;

    private final By BLOCK_WITH_GOODS_LOCATOR=By.xpath("//div[@name='goods_list']");

    private final By SORT_DROP_MENU_ITEMS_LIST_LOCATOR = By.xpath(".//*[@name='drop_menu']//li/a");

    //private final By BLOCK_WITH_GOODS_LIST_ITEMS_LOCATOR=By.xpath("//div[contains(@class,'g-i-tile-i available') or contains(@class,'g-i-tile-i unavailable')]");
    private final By BLOCK_WITH_GOODS_LIST_ITEMS_LOCATOR=By.xpath("//div[contains(@class,'over-wraper')]");

    //private By ADD_TOCOMPARISON_LOCATOR= By.xpath("//span[@class='g-compare']");;
    private By ADD_TOCOMPARISON_LOCATOR= By.xpath("//label");

    private List<WebElement> sortDropMenuItemsList;

    private List<WebElement> blockWithGoodsListItems;

    private By COMPARISON_CATALOG_LOCATOR = By.id("catalog-comparison");

    private final By FOUND_PRODUCT_LOCATOR = By.xpath("//*[@unic_testing_id='labeled']");

    private WebElement foundProduct;

    private final By COMPARISON_CATALOG_ITEMS_LOCATOR = By.xpath(".//*[contains(@class,'list-compare-l-i')]/a[not (@class='delete')]");

    private final By COMPARE_LINK_LOCATOR = By.xpath("//a[@class='novisited list-compare-more-link arrow-link']/span");

    private WebElement compareLink;

    private WebElement comparisonCatalog;

    public RozetkaAppleNotebooksPage (WebDriver driver){

        Log4Test.info("     -----=====-----");

        Log4Test.info("Instantiating a RozetkaAppleNotebooksPage");

        this.driver = driver;

        Log4Test.info("RozetkaAppleNotebooksPage instance has been received.");

    }

    public boolean doesComparisonListContainAddedProducts(final String[] PRODUCT_NAMES){

        Log4Test.info("Started verification if comparison list contains [" + String.join(";" + NEWLINE, PRODUCT_NAMES) + "].");

        boolean result = false;

        webDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(COMPARISON_CATALOG_LOCATOR));

        if (comparisonCatalogIsDisplayed()) {

            scrollToWebElement(comparisonCatalog);

            result = doesTheWebElementListContain("Comparison catalog", COMPARISON_CATALOG_ITEMS_LOCATOR, PRODUCT_NAMES);

        } else Log4Test.error("There are no comparison list displayed. Verification cancelled.");

        Log4Test.info("Completed verification if comparison list contains [" + String.join(";"+NEWLINE, PRODUCT_NAMES)+ "].");

        return result;

    }

    private boolean comparisonCatalogIsDisplayed() {

        boolean result = webElementIsDisplayed("comparison list",COMPARISON_CATALOG_LOCATOR);

        if (result) comparisonCatalog= driver.findElement(COMPARISON_CATALOG_LOCATOR);

        return result;

    }

    private boolean compareLinkIsDisplayed() {

        boolean result = webElementIsDisplayed("comparison link",COMPARE_LINK_LOCATOR);

        if (result) compareLink = driver.findElement(COMPARE_LINK_LOCATOR);

        return result;

    }

    public void waitForPageLoad() {

        if (manufactureCategoryAppleCheckBoxIsDisplayed()) Log4Test.info(" The page has been loaded.");

    }

    public boolean isLoadedPageIsAppleNotebooksPage() {

        Log4Test.info("Started verification if loaded page is Apple Notebooks Page");

        boolean result = false;

        if (manufactureCategoryAppleCheckBoxIsSelected()) result = true;

        Log4Test.info("Completed verification if loaded page is Apple Notebooks Page");

        if (result) {

            Log4Test.info("Loaded page is indeed Rozetka Apple Notebooks Page.");

        }else logResult(result);

        return result;

    }

    private boolean manufactureCategoryAppleCheckBoxIsSelected() {

        boolean result=false;

        if (manufactureCategoryAppleCheckBoxIsDisplayed() & manufactureCategoryAppleCheckBox.isEnabled()){

            result = true;

            Log4Test.info("WebElement [manufactureCategoryAppleCheckBox] is selected.");

        } else {

            Log4Test.error("WebElement [manufactureCategoryAppleCheckBox] is not selected.");
        }

        return result;

    }

    private boolean manufactureCategoryAppleCheckBoxIsDisplayed() {

        boolean result = webElementIsDisplayed("manufactureCategoryAppleCheckBox",MANUFACTURE_CATEGORY_APPLE_CHECKBOX_LOCATOR);

        if (result) manufactureCategoryAppleCheckBox = driver.findElement(MANUFACTURE_CATEGORY_APPLE_CHECKBOX_LOCATOR);

        return result;

    }

    public void clickFromExpensiveToCheapInSortDropDown() {

        if (sortDropLinkIsDisplayed()){

            sortDropLink.click();

            webDriverWaitUntilElementIsVisible(driver,SORT_DROP_MENU_LOCATOR);

            if (sortDropMenuIsDisplayed()) {

                if (sortDropMenuItemsListIsDisplayed()) {

                    Log4Test.info("Looping through sort menu items.");

                    for (WebElement menuItem : sortDropMenuItemsList) {

                        Log4Test.info("     Checking if item's link contains text [sort=expensive].");

                        if (menuItem.getAttribute("href").contains("sort=expensive")) {

                            Log4Test.info("        Coincidence has been found. Sending Click event.");

                            menuItem.click();

                            Log4Test.info("Click performed.");

                            webDriverWaitUntilPageURLContains(driver, "sort=expensive");

                            break;

                        }else Log4Test.info("        Coincidence has not been found. Skipping...");

                    }

                }else Log4Test.error("It is imposable to click Sort from expensive to cheap due to Sort Drop-down menu appeared but items are not visible.");

            }else Log4Test.error("It is imposable to click Sort from expensive to cheap due to Sort Drop-down menu has not appeared after click on Sort Drop-down element.");

        } else Log4Test.error("It is imposable to click Sort Drop-down element because it is not displayed.");

    }

    private boolean sortDropMenuIsDisplayed() {

        boolean result = webElementIsDisplayed("Items list of the Sort drop-down menu",SORT_DROP_MENU_LOCATOR);

        if (result) sortDropMenu = driver.findElement(SORT_DROP_MENU_LOCATOR);

        return result;

    }

    private boolean sortDropMenuItemsListIsDisplayed() {

        boolean result = webElementIsDisplayed("Items list of the Sort drop-down menu",SORT_DROP_MENU_ITEMS_LIST_LOCATOR);

        if (result) sortDropMenuItemsList = driver.findElements(SORT_DROP_MENU_ITEMS_LIST_LOCATOR);

        return result;

    }

    private boolean sortDropLinkIsDisplayed() {

        boolean result = webElementIsDisplayed("Drop-down menu sort",SORT_DROP_LINK_LOCATOR);

        if (result) sortDropLink = driver.findElement(SORT_DROP_LINK_LOCATOR);

        return result;

    }

    private boolean blockWithGoodsIsDisplayed() {

        boolean result = webElementIsDisplayed("Block with goods",BLOCK_WITH_GOODS_LOCATOR);

        if (result) blockWithGoods = driver.findElement(BLOCK_WITH_GOODS_LOCATOR);

        return result;

    }

    public boolean inBlockWithGoodsExists(String product){

        Log4Test.info("Started verification if there is [" + product + "] among displayed goods.");

        boolean result = false;

        if (blockWithGoodsIsDisplayed()){

            Log4Test.info("Initializing [block with goods list].");

            blockWithGoodsListItems = driver.findElements(BLOCK_WITH_GOODS_LIST_ITEMS_LOCATOR);

            Log4Test.info("Initializing [block with goods list].");

            Integer total = blockWithGoodsListItems.size();

            Log4Test.info("Successfully got list with goods containing " + total + " items(s).");

            if (!blockWithGoodsListItems.isEmpty()) {

                Log4Test.info("Started search for an item whose link inner text contains [" + product + "].");

                int i=0;

                for (WebElement element : blockWithGoodsListItems) {

                    Log4Test.info("     Got element ["+patternedString(++i, total ," ")+"/"+total+"] containing a text ["+element.getText()+"]");

                    scrollToWebElement(element);

                    if (element.isDisplayed()) {

                        Log4Test.info("        Checking if it contains ["+ product+"].");

                        if (element.getText().toLowerCase().contains(product.toLowerCase())) {

                            Log4Test.info("            COINCIDENCE DETECTED.");

                            result = true;

                            ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('unic_testing_id', 'labeled')",element);

                            foundProduct = driver.findElement(FOUND_PRODUCT_LOCATOR);

                            break;

                        }else Log4Test.info("            Coinsidence has not been found. Skipping...");

                    }else Log4Test.error("The web element [" +element.getText()+"] is not displayed.");

                }

                Log4Test.info("Completed search procedure. ");

            } else Log4Test.error("There are no items in the block with goods. Further verification is meaningless.");

        } else Log4Test.error("Verification is imposable due to WebElement [blockWithGoods] is not displayed on the [RozetkaAppleNotebooksPage].");

        Log4Test.info("Completed verification if there is [" + product + "] among displayed goods.");

        logResult(result);

        return result;

    }

    public void clickAddProductToComparison() {

        Log4Test.info("Attempting to add a [" + driver.findElement(FOUND_PRODUCT_LOCATOR).getText() + "] to comparison list.");

        scrollToWebElement(driver.findElement(FOUND_PRODUCT_LOCATOR));

        Log4Test.info("Initializing WebElement [add to comparison list] of the founded product.");

        String addToComparisonXPath = FOUND_PRODUCT_LOCATOR.toString().substring(10)+ADD_TOCOMPARISON_LOCATOR.toString().substring(10);

        WebElement addToComparison = driver.findElement(By.xpath(addToComparisonXPath));

        Log4Test.info("The WebElement [add to comparison list] of the product successfully initialized.");

        scrollToWebElement(addToComparison);

        Log4Test.info("Positioning a mouse pointer at the WebElement [add to comparison list].");

        new Actions(driver).moveToElement(addToComparison).perform();

        Log4Test.info("Appliying explicit wait until the web element is clickable.");

        webDriverWait(driver).until(ExpectedConditions.elementToBeClickable(addToComparison));

        Log4Test.info("Applied explicit wait is over.");

        Log4Test.info("Removing unic-tesing-id.");

        ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('unic_testing_id')", foundProduct);

        Log4Test.info("Sending click event.");

        addToComparison.click();

        Log4Test.info("Click performed.");

    }

    public void clickCompareLink() {

        Log4Test.info("Attempting to click [Compare link].");

        if (compareLinkIsDisplayed()){

            Log4Test.info("     Sending click event to WebElement [Compare link].");

            compareLink.click();

            Log4Test.info("     Click performed.");

        }

    }

}
