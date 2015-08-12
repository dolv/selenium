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
        Log4Test.info("Instantiating a RozetkaNotebooksTabletsAdnPCsPage");
        this.driver = driver;
        Log4Test.info("RozetkaNotebooksTabletsAdnPCsPage instance has been received.");
    }

    public void waitForPageLoad() {
        Log4Test.info(" Applying explicit wait until WebElement located the selector \""+TITLE_PAGE_LOCATOR.toString()+"\" is visible.");
        webDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(TITLE_PAGE_LOCATOR));
        Log4Test.info(" The applied explicit wait is over.");
    }

    public boolean doesTitleContain(String notebooks_tablets_and_pc_page_title) {
        Log4Test.info(" Started verification if page title contents.");
        boolean result = false;
        if (notebooks_tablets_and_pc_page_title.length()>0) {
            if (titlePageIsDisplayed()) {
                result = titlePage.getText().toLowerCase().contains(notebooks_tablets_and_pc_page_title.toLowerCase());
            } else Log4Test.error("     The verification is imposable due to invisibility of WebElement [titlePage]. ");
            if (result) {
                Log4Test.info("     The page title text is \"" + titlePage.getText() + "\"");
                Log4Test.info("     And it contains \"" + notebooks_tablets_and_pc_page_title + "\"");
            } else {
                Log4Test.error("    The page title text is \"" + titlePage.getText() + "\"");
                Log4Test.error("    And it does not contain \"" + notebooks_tablets_and_pc_page_title + "\"");
            }

        } else Log4Test.error("     There is nothing to verify. The received for verification string is empty.");
        Log4Test.info(" Completed verification of page title contents.");
        logResult(result);
        return result;
    }

    private boolean titlePageIsDisplayed() {
        boolean result = false;
        titlePage = driver.findElement(TITLE_PAGE_LOCATOR);
        if (titlePage.isDisplayed()){
            result = true;
            Log4Test.info(" WebElement [titlePage] is displayed.");
        } else {
            Log4Test.error(" WebElement [titlePage] is not displayed on the [RozetkaNotebooksTabletsAdnPCsPage].");
        }
        return result;
    }

    private boolean notebookBlockWithGoodsIsDisplayed() {
        boolean result = false;
        notebookBlockWithGoods = driver.findElement(NOTEBOOK_BLOCK_WITH_GOODS_LOCATOR);
        if (notebookBlockWithGoods.isDisplayed()){
            result = true;
            Log4Test.info(" WebElement [notebookBlockWithGoods] is displayed.");
        } else {
            Log4Test.error(" WebElement [notebookBlockWithGoods] is not displayed on the [RozetkaNotebooksTabletsAdnPCsPage].");
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
            Log4Test.info(" WebElement [notebookBlockWithGoodsList] contains \"" + new Integer(notebookBlockWithGoodsList.size()).toString() + " elements.");

            if (notebookBlockWithGoodsList.size()>0) {
                int i = 0;
                String patternt = new Integer(notebookBlockWithGoodsList.size()).toString().replaceAll("[0-9]", "0");
                Log4Test.info(" they are as follows:");
                for (WebElement item : notebookBlockWithGoodsList) {
                    Log4Test.info("    : WebElement[" + (patternt + new Integer(++i).toString()).substring(new Integer(i).toString().length()) + "] = " + item.getText() + ";");
                }
            }
        }else {
            Log4Test.error(" WebElement [notebookBlockWithGoodsList] does not contain \"" + new Integer(variantsNumber).toString() + " variants but "+new Integer(notebookBlockWithGoodsList.size()).toString() + " elements.");

        }
        return result;
    }

    public void clickAllNotebooksCategoryLink() {
        if (allNotebooksCategoryLinkIsDisplayed()){
            Log4Test.info(" Sending click event to WebElement [allNotebooksCategoryLink].");
            allNotebooksCategoryLink.click();
        }else Log4Test.info(" Click event to WebElement [allNotebooksCategoryLink] cancelled.");

    }

    private boolean allNotebooksCategoryLinkIsDisplayed() {
        boolean result = false;
        allNotebooksCategoryLink = driver.findElement(ALL_NOTEBOOKS_CATEGORY_LINK);
        if (allNotebooksCategoryLink.isDisplayed()){
            result = true;
            Log4Test.info(" WebElement [allNotebooksCategoryLink] is displayed.");
        } else {
            Log4Test.error(" WebElement [allNotebooksCategoryLink] is not displayed on the [RozetkaNotebooksTabletsAdnPCsPage].");
        }
        return result;
    }
}
