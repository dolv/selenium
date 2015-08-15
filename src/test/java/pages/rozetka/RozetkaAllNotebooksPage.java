package pages.rozetka;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Log4Test;
import java.util.List;


public class RozetkaAllNotebooksPage extends TestBase{

    private final By MANUFACTURES_LIST_LOCATOR = By.xpath("//li[@class='m-cat-l-i'][2]");

    private WebElement manufacturesList;

    private final By MANUFACTURES_LIST_ITEMS_LOCATOR = By.xpath("//li[@class='m-cat-l-i'][2]//li/a");

    private List<WebElement> manufacturesListItems;

    public RozetkaAllNotebooksPage(WebDriver driver){

        Log4Test.info("     -----=====-----");

        Log4Test.info("Instantiating a RozetkaAllNotebooksPage");

        this.driver = driver;

        Log4Test.info("RozetkaAllNotebooksPage instance has been received.");

    }

    public void waitForPageLoad() {

        if (webElementIsDisplayed("manufacturesList", MANUFACTURES_LIST_LOCATOR))

            Log4Test.info("RozetkaAllNotebooksPage has been loaded.");

    }

    public boolean areAllManufacturesPresent(List<String> manufactures_list) {

        Log4Test.info("Started verification if all manufactures from provided list are present.");

        boolean result = false;

        if (!manufactures_list.isEmpty()) {

            if (manufacturesListIsDisplayed()) {

                if (iteratedManufacturesListIsDisplayed()) {

                    if (!manufacturesListItems.isEmpty()) {

                        Log4Test.info("Processing input list of " + Integer.toString(manufactures_list.size()) +" element(s).");

                        for (String recevedlistItem : manufactures_list) {

                            for (WebElement manufacturer : manufacturesListItems) {

                                if (manufacturer.getText().toLowerCase().contains(recevedlistItem.toLowerCase())) {

                                    Log4Test.info("    Coincidence for \""+ recevedlistItem +"\" has been found.");

                                    result = true;

                                    break;

                                }

                            }

                            if (!result) {

                                Log4Test.info("     The received item \"" + recevedlistItem +"\" was not found. Further steps are unnecessary. Quiting loop.");

                                break;
                            }

                        }

                        Log4Test.info("Processing completed.");

                    } else Log4Test.error("Verification is imposable because manufactures list is empty.");

                } else Log4Test.error("Verification is imposable due to invisibility of the manufacture list items.");

            } else Log4Test.error("Verification is imposable due to invisibility of the manufacture list WebElement.");

        } else Log4Test.error("Verification is imposable because provided for verification list is empty.");

        Log4Test.info("Completed verification if all manufactures from provided list are present.");

        logResult(result);

        return result;

    }

    private boolean manufacturesListIsDisplayed() {

        boolean result = webElementIsDisplayed("manufactures list",MANUFACTURES_LIST_LOCATOR);

        if (result) manufacturesList = driver.findElement(MANUFACTURES_LIST_LOCATOR);

        return result;

    }

    private boolean iteratedManufacturesListIsDisplayed() {

        boolean result = webElementIsDisplayed("items of the manufactures list",MANUFACTURES_LIST_ITEMS_LOCATOR);

        if (result) manufacturesListItems = driver.findElements(MANUFACTURES_LIST_ITEMS_LOCATOR);

        return result;

    }

    public void clickManufactureLinkIfItContains(String manufacture_name) {

        if (manufacturesListIsDisplayed() & iteratedManufacturesListIsDisplayed()) {

            Log4Test.info("Looping through the all manufactures.");

            for (WebElement manufacturer : manufacturesListItems) {

                Log4Test.info("Checking if manufacture contains \"" + manufacture_name + "\"");

                if (manufacturer.getText().toLowerCase().contains(manufacture_name.toLowerCase())) {

                    Log4Test.info("     Coincidence has been found. Sending Click event.");

                    manufacturer.click();

                    Log4Test.info("Click performed.");

                    break;

                }else Log4Test.info("    Coincidence has not been found. Skipping...");

            }

        }

    }

}
