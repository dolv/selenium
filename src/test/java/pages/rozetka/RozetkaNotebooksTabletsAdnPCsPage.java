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

    private final By ALL_NOTEBOOKS_CATEGORY_LINK = By.xpath(".//*[@data-name='group_category']/span[contains(text(),'Все ноутбуки')]");

    private WebElement allNotebooksCategoryLink;

    public RozetkaNotebooksTabletsAdnPCsPage(WebDriver driver) {

        Log4Test.info("     -----=====-----");

        Log4Test.info("Instantiating a RozetkaNotebooksTabletsAdnPCsPage");

        this.driver = driver;

        Log4Test.info("RozetkaNotebooksTabletsAdnPCsPage instance has been received.");

    }

    public void waitForPageLoad() {

        Log4Test.info("     Applying explicit wait until WebElement located the selector \""+TITLE_PAGE_LOCATOR.toString()+"\" is visible.");

        webDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(TITLE_PAGE_LOCATOR));

        Log4Test.info("     The applied explicit wait is over.");

    }

    public boolean doesTitleContain(String notebooks_tablets_and_pc_page_title) {

        Log4Test.info("Searching the page title contents for\"" + notebooks_tablets_and_pc_page_title +"\"");

        webDriverWaitUntilPageTitleContains(driver, notebooks_tablets_and_pc_page_title);

        boolean result = false;

        if (notebooks_tablets_and_pc_page_title.length()>0) {

            if (titlePageIsDisplayed()) {

                result = titlePage.getText().toLowerCase().contains(notebooks_tablets_and_pc_page_title.toLowerCase());

            } else Log4Test.error("     The verification is imposable due to invisibility of WebElement [titlePage]. ");

            if (result) {

                Log4Test.info("     The page title text is \"" + titlePage.getText() + "\"");
                Log4Test.info("     And it contains \"" + notebooks_tablets_and_pc_page_title + "\"");

            } else {

                Log4Test.error("     The page title text is \"" + titlePage.getText() + "\"");
                Log4Test.error("     And it does not contain \"" + notebooks_tablets_and_pc_page_title + "\"");

            }

        } else Log4Test.error("     There is nothing to verify. The received for verification string is empty.");

        Log4Test.info("Completed verification of the page title contents.");

        logResult(result);

        return result;

    }

    private boolean titlePageIsDisplayed() {

        boolean result = webElementIsDisplayed("Page title",TITLE_PAGE_LOCATOR);

        if (result) titlePage = driver.findElement(TITLE_PAGE_LOCATOR);

        return result;

    }

    private boolean notebookBlockWithGoodsIsDisplayed() {

        boolean result = webElementIsDisplayed("notebookBlockWithGoods",NOTEBOOK_BLOCK_WITH_GOODS_LOCATOR);

        if (result) notebookBlockWithGoods = driver.findElement(NOTEBOOK_BLOCK_WITH_GOODS_LOCATOR);

        return result;
    }

    public boolean doesNotebookBlockWithGoodsItemsNumberIs(int variantsNumber) {

        Log4Test.info("Starting verification if there are " + variantsNumber +" variant(s) available under the Notebooks. ");

        boolean result = false;

        if (notebookBlockWithGoodsIsDisplayed()) {

            webDriverWaitUntilElementIsVisible(driver, NOTEBOOK_BLOCK_WITH_GOODS_LIST_LOCATOR);

            Log4Test.info("     Initializing WebElement [notebookBlockWithGoodsList] by locator =\"" + NOTEBOOK_BLOCK_WITH_GOODS_LIST_LOCATOR.toString() + "\".");

            notebookBlockWithGoodsList=driver.findElements(NOTEBOOK_BLOCK_WITH_GOODS_LIST_LOCATOR);

            Log4Test.info("     WebElement [notebookBlockWithGoodsList] initialized successfully.");
            Log4Test.info("     Checking if WebElement [notebookBlockWithGoodsList] is not empty.");

            if (!notebookBlockWithGoodsList.isEmpty()){

                Log4Test.info("          It is not empty.");

                result = notebookBlockWithGoodsList.size() == variantsNumber;

            }else {

                Log4Test.info("          It is empty.");

                result= variantsNumber < 1;

            }

        }

        if (result){

            Log4Test.info("WebElement [notebookBlockWithGoodsList] contains \"" + Integer.toString(notebookBlockWithGoodsList.size()) + " elements.");

            if (notebookBlockWithGoodsList.size()>0) {

                Log4Test.info("   they are as follows:");

                int i = 0;
                for (WebElement item : notebookBlockWithGoodsList) {

                    Log4Test.info("     WebElement[" + zeroPatternedString(++i,notebookBlockWithGoodsList.size()) + "] = " + item.getText() + ";");

                }

            }

        }else {

            Log4Test.error("WebElement [notebookBlockWithGoodsList] does not contain \"" + Integer.toString(variantsNumber) + " variants but "+ Integer.toString(notebookBlockWithGoodsList.size()) + ".");

        }

        Log4Test.info("Completed verification if there are \"" + variantsNumber +"\" variant(s) available under the Notebooks. ");

        logResult(result);

        return result;

    }

    public void clickAllNotebooksCategoryLink() {

        Log4Test.info("Invoking [clickAllNotebooksCategoryLink] method.");

        if (allNotebooksCategoryLinkIsDisplayed()){

            Log4Test.info("Sending click event to WebElement [allNotebooksCategoryLink].");

            allNotebooksCategoryLink.click();

            Log4Test.info("Click performed.");

        }else Log4Test.info("Click event to WebElement [allNotebooksCategoryLink] cancelled.");

        Log4Test.info("The method [clickAllNotebooksCategoryLink] completed.");

    }

    private boolean allNotebooksCategoryLinkIsDisplayed() {

        boolean result = webElementIsDisplayed("allNotebooksCategoryLink",ALL_NOTEBOOKS_CATEGORY_LINK);

        if (result) allNotebooksCategoryLink = driver.findElement(ALL_NOTEBOOKS_CATEGORY_LINK);

        return result;

    }

}
