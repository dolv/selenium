package pages.rozetka;


import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log4Test;
import java.io.IOException;
import java.util.List;

public class RozetkaComparisonPage extends TestBase {

    private final By COMPARISON_TABLE_FIRST_ROW_LOCATOR = By.xpath(".//tr[@class='comparison-thead-row-first']");

    private WebElement comparisonTableFirstRow;

    private final By COMPARISON_TABLE_FIRST_ROW_ITEMS_LOCATOR = By.xpath("//td[@class='comparison-col-i']");

    private List<WebElement> comparisonTableFirstRowItemsList;

    public RozetkaComparisonPage(WebDriver driver) {

        Log4Test.info("     -----=====-----");

        Log4Test.info("Instantiating a RozetkaComparisonPage");

        this.driver = driver;

        Log4Test.info("RozetkaComparisonPage instance has been received.");

    }


    public void waitForPageLoad() {

        Log4Test.info("     Applying explicit wait until WebElement located the selector \""+COMPARISON_TABLE_FIRST_ROW_LOCATOR.toString()+"\" is visible.");

        webDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(COMPARISON_TABLE_FIRST_ROW_LOCATOR));

        Log4Test.info("     The applied explicit wait is over.");

    }

    public boolean doesThePageContain(String TEXT) throws IOException {

        return doesPageContainText(TEXT,driver);

    }

    public boolean comparisonTableFirsRowIsDislated() {

        boolean result = webElementIsDisplayed("First row of the comparison table", COMPARISON_TABLE_FIRST_ROW_LOCATOR);

        if (result) comparisonTableFirstRow = driver.findElement(COMPARISON_TABLE_FIRST_ROW_LOCATOR);

        return result;

    }

    public boolean doesThePageContainProcucts(final String[] PRODUCTS){

        String message = "\"Started verification if comparison table contains [";

        Log4Test.info(message + String.join(lSep(message), PRODUCTS)+ "].");

        boolean result = false;

        if (comparisonTableFirsRowIsDislated()) {

            result = doesTheWebElementListContain("Comparison products list", COMPARISON_TABLE_FIRST_ROW_ITEMS_LOCATOR, PRODUCTS);

        } else Log4Test.error("There are no correct comparison table displayed. Verification cancelled.");

        message = "\"Completed verification if comparison table contains [";

        Log4Test.info(message + String.join(lSep(message), PRODUCTS)+ "].");

        logResult(result);

        return result;

    }

}
