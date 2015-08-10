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

    public RozetkaPersonalInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForPageLoad() {
        final ExpectedCondition<Boolean> pageTitleContainsString = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                final String pageURL = wd.getCurrentUrl().toLowerCase();
                boolean pageTitleContainsString = pageURL.contains(PAGE_URL);
                if (!pageTitleContainsString) Log4Test.error(getCurrentTimestamp() + ": WebElement [PAGE_URL] doesn't contain=\"" + PAGE_URL + "\"");
                return pageTitleContainsString;
            }
        };
        webDriverWait(driver).until(pageTitleContainsString);

        Log4Test.info(getCurrentTimestamp() + ": WebElement [personalInformationHeader] initialization by locator =\"" + PERSONAL_INFORMATION_PAGE_HEADER_LOCATOR + "\"");
        personalInformationPageHeader = driver.findElement(PERSONAL_INFORMATION_PAGE_HEADER_LOCATOR);
        Log4Test.info(getCurrentTimestamp() + ": waiting for the WebElement [personalInformationHeader] is loaded and visible");
        webDriverWait(driver).until(ExpectedConditions.visibilityOf(personalInformationPageHeader));
        Log4Test.info(getCurrentTimestamp() + ": waiting timeout is over the WebElement [personalInformationHeader] is \"" + personalInformationPageHeader.getText()+"\"");
    }

    public String getPageHeaderText() {
        String result = "";
        if (pageHeaderIsDisplayed()) {
            result = personalInformationPageHeader.getText();
        }
        Log4Test.info(getCurrentTimestamp() + ": WebElement [personalInformationHeader] text = \"" + result+"\"");
        return result;
    }

    private boolean pageHeaderIsDisplayed() {
        Log4Test.info(getCurrentTimestamp() + ": WebElement [personalInformationPageHeader] initialization by locator =\""+PERSONAL_INFORMATION_PAGE_HEADER_LOCATOR+"\"");
        personalInformationPageHeader = driver.findElement(PERSONAL_INFORMATION_PAGE_HEADER_LOCATOR);
        Boolean result = personalInformationPageHeader.isDisplayed();
        Log4Test.info(getCurrentTimestamp() + ": WebElement [personalInformationPageHeader] displayed =\""+ Boolean.toString(result)+"\"");
        return result;
    }

    public String getCurrentURL() {
        String result;
        result = driver.getCurrentUrl();
        Log4Test.info(getCurrentTimestamp() + ": getCurrentURL() = \"" + result+"\"");
        return result;
    }
    public Boolean doesPersonalInformationNameContains(String[] words) {
        boolean result = false;
        if (personalInformationNameIsDisplayed()) {
            String linkText = personalInformationName.getText().toLowerCase();
            for (String word : words) {
                if (linkText.equals(word.toLowerCase())) result = true;
            }
        }
        return result;
    }

    private boolean personalInformationNameIsDisplayed() {
        Log4Test.info(getCurrentTimestamp() + ": WebElement [personalInformationName] initialization by locator =\""+NAME_LOCATOR+"\"");
        personalInformationName=driver.findElement(NAME_LOCATOR);
        Boolean result = personalInformationName.isDisplayed();
        Log4Test.info(getCurrentTimestamp() + ": WebElement [personalInformationName] displayed =\""+ Boolean.toString(result)+"\"");
        return result;
    }

    public boolean isOpenedOptionsPageIsMine(String[] possibleNameWords) {
        myOtionsPageOpened = false;
        webDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(NAME_LOCATOR));
        if (personalInformationNameIsDisplayed()){
            myOtionsPageOpened = this.doesPersonalInformationNameContains(possibleNameWords);
        }
        return myOtionsPageOpened;
    }
}
