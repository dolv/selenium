package pages.rozetka;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log4Test;

public class RozetkaPersonalInformationPage extends TestBase{

    private final By PERSONAL_INFORMATION_PAGE_HEADER_LOCATOR = By.xpath("//div[@id='personal_information']//h1");

    private WebElement personalInformationPageHeader;

    private final By NAME_LOCATOR = By.xpath(".//*[@id='personal_information']/div/div[1]/div[2]/div[2]");

    private WebElement personalInformationName;

    private final String PAGE_URL = "https://my.rozetka.com.ua/";

    private boolean myOtionsPageOpened;

    private final By LOGOUT_LINK_LOCATOR = By.xpath(".//ul[@id='header_user_menu']//a[@href='https://my.rozetka.com.ua/signout/']");

    private WebElement logoutLink;

    public RozetkaPersonalInformationPage(WebDriver driver) {

        Log4Test.info("Instantiating a RozetkaPersonalInformationPage");

        this.driver = driver;

        Log4Test.info("RozetkaPersonalInformationPage instance has been received.");

    }

    public void waitForPageLoad() {

        Log4Test.info(" Applying page load wait.");

        final ExpectedCondition<Boolean> pageURLContainsString = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver wd) {

                final String pageURL = wd.getCurrentUrl().toLowerCase();

                boolean pageURLContainsString = pageURL.contains(PAGE_URL);

                if (!pageURLContainsString) Log4Test.error(" WebElement [PAGE_URL] doesn't contain=\"" + PAGE_URL + "\"");

                return pageURLContainsString;

            }

        };

        Log4Test.info("     Waiting until page URL contains \"" + PAGE_URL + "\"");

        webDriverWait(driver).until(pageURLContainsString);

        Log4Test.info("     Waiting timeout is over.");
        //Log4Test.info("     Initializing WebElement [personalInformationHeader] by locator =\"" + PERSONAL_INFORMATION_PAGE_HEADER_LOCATOR.toString() + "\"");
        personalInformationPageHeader = driver.findElement(PERSONAL_INFORMATION_PAGE_HEADER_LOCATOR);
        //Log4Test.info("     WebElement [personalInformationHeader] has been successfully initialized.");

        Log4Test.info("     Waiting for the WebElement [personalInformationHeader] is loaded and visible");

        webDriverWait(driver).until(ExpectedConditions.visibilityOf(personalInformationPageHeader));

        Log4Test.info("     Waiting timeout is over.");

        Log4Test.info("     The WebElement [personalInformationHeader] text is \"" + personalInformationPageHeader.getText() + "\"");

        Log4Test.info(" Applied page load wait is over.");

    }

    public String getCurrentURL() {

        String result;

        result = driver.getCurrentUrl();

        Log4Test.info(" Current page URL is\"" + result + "\"");

        return result;

    }

    public Boolean doesPersonalInformationNameContains(String[] words) {

        Log4Test.info(" Started verification if Name on the RozetkaPersonalInformationPage Contains some of the offered words.");

        boolean result = false;

        if (words.length>0) {

            Log4Test.info("     The offered words are:");

            int i = 0;
            for (String word : words) {

                Log4Test.info("         "+ Integer.toString(++i) + ") \"" + word+"\";");

            }

            if (personalInformationNameIsDisplayed()) {

                if (personalInformationName.getText().length()>0) {

                    Log4Test.info("     Processing the above mentioned word list.");
                    String linkText = personalInformationName.getText().toLowerCase();
                    i=0;
                    for (String word : words) {

                        if (linkText.contains(word.toLowerCase())) {

                            i++;

                            Log4Test.info("     The word \""+word+"\" found.");

                        }
                        result = words.length==i;

                    }

                }else Log4Test.error("     Verification is imposable because WebElement [personalInformationName] contains no words.");

            }else Log4Test.error("     Verification is imposable due to invisibility of WebElement [personalInformationName].");

        } else Log4Test.error(" There were no words for verification provided.");

        Log4Test.info(" Completed verification if Name on the RozetkaPersonalInformationPage Contains some of the offered words.");

        logResult(result);

        return result;

    }

    private boolean personalInformationNameIsDisplayed() {

        Log4Test.info(" Started verification if WebElement [personalInformationName] is Displayed.");
        Log4Test.info("     Initializing WebElement [personalInformationName] by locator =\""+NAME_LOCATOR.toString()+"\"");

        personalInformationName=driver.findElement(NAME_LOCATOR);

        Log4Test.info("     WebElement [personalInformationName] initialized successfully.");
        Log4Test.info("     Checking if WebElement [personalInformationName] is Displayed.");

        boolean result = personalInformationName.isDisplayed();

        Log4Test.info(" Completed verification if WebElement [personalInformationName] is Displayed.");

        logResult(result);

        return result;
    }

    public boolean isOpenedOptionsPageIsMine(String[] possibleNameWords) {
        Log4Test.info(" Started verification if opened options page is mine.");

        myOtionsPageOpened = false;

        Log4Test.info("     Applying explicit wait until element located by locator =\"" + NAME_LOCATOR.toString() + "\" is present.");

        webDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(NAME_LOCATOR));

        Log4Test.info("     Applied explicit wait is over.");

        if (personalInformationNameIsDisplayed()){

            myOtionsPageOpened = doesPersonalInformationNameContains(possibleNameWords);

        }

        Log4Test.info(" Completed verification if opened options page is mine.");

        logResult(myOtionsPageOpened);

        return myOtionsPageOpened;

    }

    public void clickLogoutLink() {

        logoutLink = driver.findElement(LOGOUT_LINK_LOCATOR);

        logoutLink.click();

    }

}
