package pages.rozetka;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log4Test;

import java.util.List;

public class RozetkaNotebooksTabletsAdnPCsPage extends TestBase{
    private final By TITLE_PAGE_LOCATOR = By.xpath("//div[@class='portal-page']//header[@class='title-page']");
    private WebElement titlePage;
    private final By NOTEBOOK_BLOCK_WITH_GOODS_LOCATOR = By.xpath("//div[@class='container']/*[1]//*[1][@name='block_with_goods']//ul");
    private final By NOTEBOOK_BLOCK_WITH_GOODS_LIST_LOCATOR = By.xpath("//div[@class='container']/*[1]//*[1][@name='block_with_goods']//ul/li");
    private WebElement notebookBlockWithGoods;
    private List<WebElement> notebookBlockWithGoodsList;
    private final By ALL_NOTEBOOKS_CATEGORY_LINK = By.xpath("//div[@class='container']/*[1]//*[1][@name='block_with_goods']//a[@name='group_category']");
    private WebElement allNotebooksCategoryLink;
    public RozetkaNotebooksTabletsAdnPCsPage(WebDriver driver) {
        this.driver=driver;
    }

    public void waitForPageLoad() {
        webDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(TITLE_PAGE_LOCATOR));
        titlePage = driver.findElement(TITLE_PAGE_LOCATOR);
        webDriverWait(driver).until(ExpectedConditions.visibilityOf(titlePage));
    }

    public boolean doesTitleContain(String noteboks_tablets_and_pc_page_title) {
        boolean result = false;
        if (titlePageIsDisplayed()) {
            result = titlePage.getText().toLowerCase().contains(noteboks_tablets_and_pc_page_title.toLowerCase());
        }
        if (result){
            Log4Test.info(getCurrentTimestamp() + ": WebElement [titlePage] test is \"" + titlePage.getText() + "\"");
            Log4Test.info(getCurrentTimestamp() + ": WebElement [titlePage] contains \"" + noteboks_tablets_and_pc_page_title + "\"");
        }else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [titlePage] does not contain \"" + noteboks_tablets_and_pc_page_title+ "\"");
            Log4Test.error(getCurrentTimestamp() + ": WebElement [titlePage] test is \"" + titlePage.getText() + "\"");
        }
        return result;
    }

    private boolean titlePageIsDisplayed() {
        boolean result = false;
        titlePage = driver.findElement(TITLE_PAGE_LOCATOR);
        if (titlePage.isDisplayed()){
            result = true;
            Log4Test.info(getCurrentTimestamp() + ": WebElement [titlePage] is displayed.");
        } else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [titlePage] is not displayed on the [RozetkaNotebooksTabletsAdnPCsPage].");
        }
        return result;
    }

    private boolean notebookBlockWithGoodsIsDisplayed() {
        boolean result = false;
        notebookBlockWithGoods = driver.findElement(NOTEBOOK_BLOCK_WITH_GOODS_LOCATOR);
        if (notebookBlockWithGoods.isDisplayed()){
            result = true;
            Log4Test.info(getCurrentTimestamp() + ": WebElement [notebookBlockWithGoods] is displayed.");
        } else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [notebookBlockWithGoods] is not displayed on the [RozetkaNotebooksTabletsAdnPCsPage].");
        }
        return result;
    }

    public boolean doesNotebookBlockWithGoodsItemsNumberIs(int variantsNumber) {
        boolean result = false;
        if (notebookBlockWithGoodsIsDisplayed()) {
            notebookBlockWithGoodsList=driver.findElements(NOTEBOOK_BLOCK_WITH_GOODS_LIST_LOCATOR);
            if (!notebookBlockWithGoodsList.isEmpty()){
                result=notebookBlockWithGoodsList.size()==variantsNumber?true:false;
            }else result=variantsNumber<1?true:false;
        }
        if (result){
            Log4Test.info(getCurrentTimestamp() + ": WebElement [notebookBlockWithGoodsList] contains \"" + new Integer(notebookBlockWithGoodsList.size()).toString() + " elements.");

            if (notebookBlockWithGoodsList.size()>0) {
                int i = 0;
                String patternt = new Integer(notebookBlockWithGoodsList.size()).toString().replaceAll("[0-9]", "0");
                Log4Test.info(getCurrentTimestamp() + ": they are as follows:");
                for (WebElement item : notebookBlockWithGoodsList) {
                    Log4Test.info("    : WebElement[" + (patternt + new Integer(i).toString()).substring(new Integer(i).toString().length()) + "] = " + item.getText() + ";");
                }
            }
        }else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [notebookBlockWithGoodsList] does not contain \"" + new Integer(variantsNumber).toString() + " variants but "+new Integer(notebookBlockWithGoodsList.size()).toString() + " elements.");

        }
        return result;
    }

    public void clickAllNotebooksCategoryLink() {
        if (allNotebooksCategoryLinkIsDisplayed()){
            Log4Test.info(getCurrentTimestamp() + ": Sending click event to WebElement [allNotebooksCategoryLink].");
            allNotebooksCategoryLink.click();
        }else Log4Test.info(getCurrentTimestamp() + ": Click event to WebElement [allNotebooksCategoryLink] cancelled.");

    }

    private boolean allNotebooksCategoryLinkIsDisplayed() {
        boolean result = false;
        allNotebooksCategoryLink = driver.findElement(ALL_NOTEBOOKS_CATEGORY_LINK);
        if (allNotebooksCategoryLink.isDisplayed()){
            result = true;
            Log4Test.info(getCurrentTimestamp() + ": WebElement [allNotebooksCategoryLink] is displayed.");
        } else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [allNotebooksCategoryLink] is not displayed on the [RozetkaNotebooksTabletsAdnPCsPage].");
        }
        return result;
    }
}
