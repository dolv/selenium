package pages.rozetka;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log4Test;

import java.util.List;

public class RozetkaAllNotebooksPage extends TestBase{

    private final By MANUFACTURES_LIST_LOCATOR = By.xpath("//li[@class='m-cat-l-i'][2]");
    private WebElement manufacturesList;
    private final By MANUFACTURES_LIST_ITEMS_LOCATOR = By.xpath("//li[@class='m-cat-l-i'][2]//li/a");
    private List<WebElement> iteratedManufacturesList;

    public RozetkaAllNotebooksPage(WebDriver driver){
        this.driver=driver;
    }

    public void waitForPageLoad() {
        webDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(MANUFACTURES_LIST_LOCATOR));
        manufacturesList = driver.findElement(MANUFACTURES_LIST_LOCATOR);
        webDriverWait(driver).until(ExpectedConditions.visibilityOf(manufacturesList));
    }

    public boolean areAllManufacturesPresent(List<String> manufactures_list) {
        boolean result = false;
        if (manufacturesListIsDisplayed()){
            iteratedManufacturesList=driver.findElements(MANUFACTURES_LIST_ITEMS_LOCATOR);
            if (iteratedManufacturesList.size()>0 & manufactures_list.size()>0) {
                for (String recevedlistItem : manufactures_list) {
                    for (WebElement manufacturesListItem: iteratedManufacturesList) {
                        if (manufacturesListItem.getText().toLowerCase().contains(recevedlistItem.toLowerCase())) {
                            result = true;
                            break;
                        }
                    }
                    if (!result) break;
                }
            }
        }
        return result;
    }

    private boolean manufacturesListIsDisplayed() {
        boolean result = false;
        manufacturesList = driver.findElement(MANUFACTURES_LIST_LOCATOR);
        if (manufacturesList.isDisplayed()){
            result = true;
            Log4Test.info(getCurrentTimestamp() + ": WebElement [manufacturesList] is displayed.");
        } else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [manufacturesList] is not displayed on the [RozetkaAllNotebooksPage].");
        }
        return result;
    }

    public void clickManufactureLinkIfItContains(String manufacture_name) {
        if (manufacturesListIsDisplayed()){
            iteratedManufacturesList=driver.findElements(MANUFACTURES_LIST_ITEMS_LOCATOR);
            for (WebElement manufacturesListItem: iteratedManufacturesList) {
                if (manufacturesListItem.getText().toLowerCase().contains(manufacture_name.toLowerCase())) {
                    if (manufacturesListItem.isDisplayed()) manufacturesListItem.click();
                    break;
                }
            }
        }
    }
}
