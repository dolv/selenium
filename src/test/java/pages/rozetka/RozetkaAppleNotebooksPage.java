package pages.rozetka;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log4Test;

import java.util.List;

public class RozetkaAppleNotebooksPage extends TestBase{
    private final By MANUFACTURE_CATEGORY_APPLE_CHECKBOX_LOCATOR=By.xpath(".//*[@id='sort_producer']/li[@class='filter-parametrs-i-l-i']");
    private WebElement manufactureCategoryAppleCheckBox;
    private boolean loadedPageIsAppleNotebooksPage;
    private final By SORT_DROP_LINK_LOCATOR = By.name("drop_link");
    private WebElement sortDropLink;
    private final By SORT_DROP_LINK_LIST_LOCATOR = By.name("drop_menu");
    private WebElement sortDropMenu;
    private WebElement blockWithGoods;
    private final By BLOCK_WITH_GOODS_LOCATOR=By.xpath(".//div[@id='block_with_goods']");
    private final By SORT_DROP_MENU_ITEMS_LIST_LOCATOR = By.xpath(".//*[@name='drop_menu']//li/a");
    private final By BLOCK_WITH_GOODS_LINK_LIST_LOCATOR=By.xpath("//div[@class='g-i-tile-i-box-desc']");
    private final By BLOCK_WITH_GOODS_ITEM_TOCOMPARISON_LOCATOR=By.xpath("//*[@name='tocomparison']/..");
    private List<WebElement> sortDropMenuItemsList;
    private List<WebElement> blockWithGoodsLinkList;


    public RozetkaAppleNotebooksPage (WebDriver driver){
        this.driver=driver;
    }

    public void waitForPageLoad() {
        webDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(MANUFACTURE_CATEGORY_APPLE_CHECKBOX_LOCATOR));
        manufactureCategoryAppleCheckBox = driver.findElement(MANUFACTURE_CATEGORY_APPLE_CHECKBOX_LOCATOR);
        webDriverWait(driver).until(ExpectedConditions.visibilityOf(manufactureCategoryAppleCheckBox));
    }

    public boolean isLoadedPageIsAppleNotebooksPage() {
        loadedPageIsAppleNotebooksPage=false;
        if (manufactureCategoryAppleCheckBoxIsEnabled()) loadedPageIsAppleNotebooksPage=true;
        if (loadedPageIsAppleNotebooksPage) Log4Test.info(getCurrentTimestamp() + ": Loaded page is [RozetkaAppleNotebooksPage].");
        return loadedPageIsAppleNotebooksPage;
    }

    private boolean manufactureCategoryAppleCheckBoxIsEnabled() {
        boolean result=false;
        if (manufactureCategoryAppleCheckBoxIsDisplayed() & manufactureCategoryAppleCheckBox.isEnabled()){
            result = true;
            Log4Test.info(getCurrentTimestamp() + ": WebElement [manufactureCategoryAppleCheckBox] is selected.");
        } else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [manufactureCategoryAppleCheckBox] is not selected.");
        }
        return result;
    }

    private boolean manufactureCategoryAppleCheckBoxIsDisplayed() {
        boolean result = false;
        manufactureCategoryAppleCheckBox = driver.findElement(MANUFACTURE_CATEGORY_APPLE_CHECKBOX_LOCATOR);
        if (manufactureCategoryAppleCheckBox.isDisplayed()){
            result = true;
            Log4Test.info(getCurrentTimestamp() + ": WebElement [manufactureCategoryAppleCheckBox] is displayed.");
        } else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [manufactureCategoryAppleCheckBox] is not displayed on the [RozetkaAppleNotebooksPage].");
        }
        return result;
    }

    public void clickFromExpensiveToCheapInSortDropDown() {
        if (sortDropLinkIsDisplayed()){
            sortDropLink.click();
            webDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(SORT_DROP_LINK_LIST_LOCATOR));
            sortDropMenu=driver.findElement(SORT_DROP_LINK_LIST_LOCATOR);
            sortDropMenuItemsList=driver.findElements(SORT_DROP_MENU_ITEMS_LIST_LOCATOR);
            for (WebElement menuItem:sortDropMenuItemsList){
                if(menuItem.getAttribute("href").contains("sort=expensive")){
                    menuItem.click();
                    final ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver wd) {
                            wd = driver;
                            boolean condition = driver.getCurrentUrl().contains("sort=expensive");
                            if (!condition) Log4Test.error(getCurrentTimestamp() + ": RozetkaAppleNotebooksPage is not refreshed yet. ");
                            return condition;
                        }
                    };
                    webDriverWait(driver).until(condition);
                    break;
                }
            }

        }
    }

    private boolean sortDropLinkIsDisplayed() {
        boolean result = false;
        sortDropLink = driver.findElement(SORT_DROP_LINK_LOCATOR);
        if (sortDropLink.isDisplayed()){
            result = true;
            Log4Test.info(getCurrentTimestamp() + ": WebElement [sortDropLink] is displayed.");
        } else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [sortDropLink] is not displayed on the [RozetkaAppleNotebooksPage].");
        }
        return result;
    }

    private boolean blockWithGoodsIsDisplayed() {
        boolean result = false;
        blockWithGoods = driver.findElement(BLOCK_WITH_GOODS_LOCATOR);
        if (blockWithGoods.isDisplayed()){
            result = true;
            Log4Test.info(getCurrentTimestamp() + ": WebElement [blockWithGoods] is displayed.");
        } else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [blockWithGoods] is not displayed on the [RozetkaAppleNotebooksPage].");
        }
        return result;
    }

    public boolean verifyIfInBlockWithGoodsExists(String checked_item){
        boolean result = false;
        if (blockWithGoodsIsDisplayed()){
            Log4Test.info(getCurrentTimestamp() + ": Instantiating List<WebElement> blockWithGoodsLinkList.");
            blockWithGoodsLinkList=driver.findElements(BLOCK_WITH_GOODS_LINK_LIST_LOCATOR);
            if (!blockWithGoodsLinkList.isEmpty()){
                Log4Test.info(getCurrentTimestamp() + ": Successfully got [blockWithGoodsLinkList] containing " + new Integer(blockWithGoodsLinkList.size()).toString() + "items(s).");
                Log4Test.info(getCurrentTimestamp() + ": Started [blockWithGoodsLinkList] search for link with inner text " + checked_item + "\"");
                for (WebElement element: blockWithGoodsLinkList){
                    if (element.getText().toLowerCase().contains(checked_item.toLowerCase())){
                        result = true;
                        break;
                    }
                }
                Log4Test.info(getCurrentTimestamp() + ": [blockWithGoodsLinkList] processing complete. ");
                if(result){
                    Log4Test.info(getCurrentTimestamp() + ": [blockWithGoodsLinkList] contains a link with inner text \"" + checked_item + "\"");
                }else Log4Test.error(getCurrentTimestamp() + ": A link with inner text \"" + checked_item + "\" was not found among [blockWithGoodsLinkList] items.");
            } else {
                Log4Test.error(getCurrentTimestamp() + ": [blockWithGoodsLinkList] is empty. Verification is blocked.");
            }
        } else {
            Log4Test.error(getCurrentTimestamp() + ": Verification is imposable due to WebElement [blockWithGoods] is not displayed on the [RozetkaAppleNotebooksPage].");
        }
        return result;
    }

    public void clickAddProductToComparison(String product) {
        boolean result = false;
        if (blockWithGoodsIsDisplayed()){
            Log4Test.info(getCurrentTimestamp() + ": Instantiating List<WebElement> blockWithGoodsLinkList.");
            blockWithGoodsLinkList=driver.findElements(BLOCK_WITH_GOODS_LINK_LIST_LOCATOR);
            if (!blockWithGoodsLinkList.isEmpty()){
                Log4Test.info(getCurrentTimestamp() + ": Successfully got [blockWithGoodsLinkList] containing " + new Integer(blockWithGoodsLinkList.size()).toString() + "items(s).");
                Log4Test.info(getCurrentTimestamp() + ": Started [blockWithGoodsLinkList] search for link with inner text " + product + "\"");
                for (WebElement element: blockWithGoodsLinkList){
                    if (element.getText().toLowerCase().contains(product.toLowerCase())){
                        Log4Test.info(getCurrentTimestamp() + ": Sending click event to " + product + "\" to add it to comparison list.");
                        element.findElement(BLOCK_WITH_GOODS_ITEM_TOCOMPARISON_LOCATOR).click();
                        result = true;
                        break;
                    }
                }
                Log4Test.info(getCurrentTimestamp() + ": [blockWithGoodsLinkList] processing complete. ");
                if(result){
                    Log4Test.info(getCurrentTimestamp() + ": [blockWithGoodsLinkList] contains a link with inner text \"" + product + "\"");
                }else Log4Test.error(getCurrentTimestamp() + ": A link with inner text \"" + product + "\" was not found among [blockWithGoodsLinkList] items.");
            } else {
                Log4Test.error(getCurrentTimestamp() + ": [blockWithGoodsLinkList] is empty. It is imposable to add a product to comparison list.");
            }
        } else {
            Log4Test.error(getCurrentTimestamp() + ": It is imposable to add a product to comparison list due to WebElement [blockWithGoods] is not displayed on the [RozetkaAppleNotebooksPage].");
        }
    }
}
