package pages.rozetka;


import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log4Test;

import java.util.Iterator;
import java.util.Set;

public class RozetkaMainPage extends TestBase {
    private final String PAGE_TITLE = "ROZETKA";
    private final By ENTER_USER_ACCOUNT_LINK_LOCATOR = By.xpath(".//span[@id='header_user_menu_parent']//a");
    private final By LOGIN_TITLE_LOCATOR = By.xpath("//div[@class='popup-css popup-auth']");
    private final By LOGIN_VK_LOCATOR = By.xpath("//div[@type='vkontakte']//a");
    private final By LOGIN_FACEBOOK_LOCATOR = By.xpath("//div[@type='facebook']//a");
    private final By REMEMBER_ME_CHECKBOX_LOCATOR = By.xpath("//label[@class='checkbox auth-f-i-remember']/span");
    private WebElement loginTitle;
    private WebElement loginVKButton;
    private WebElement loginFacebookButton;
    private WebElement rememberMeCheckBox;
    private WebElement enterUserAccountLink;
    private WebElement userAccountLink;
    private String mainWindowId;

    public RozetkaMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(final String PAGE_URL){

        Log4Test.info(getCurrentTimestamp() + ": Opening page with address =\""+PAGE_URL+"\"");
        driver.get(PAGE_URL);
        Log4Test.info(getCurrentTimestamp() + ": wait until page title contains =\"" + PAGE_TITLE + "\"");
        webDriverWait(driver).until(ExpectedConditions.titleContains(PAGE_TITLE));
        Log4Test.info(getCurrentTimestamp() + ": After timeout opened page title is =\"" + driver.getTitle() + "\"");
    }

    public boolean isOpened(String PAGE_URL){
        Boolean result = false;
        if (driver.getCurrentUrl().contains(PAGE_URL)){
            result = true;
        } else {
            Log4Test.error(getCurrentTimestamp() + ": Openned page URL = \""+ driver.getCurrentUrl()+" differs from passer value of =\"" + PAGE_URL + "\"");
        }
        Log4Test.info(getCurrentTimestamp() + ": isOpened() result =\"" + result + "\"");
        return result;
    }

    public void clickEnterUserAccountLink(){
        Log4Test.info(getCurrentTimestamp() + ": WebElement [User Account Link] initialization by locator =\""+ENTER_USER_ACCOUNT_LINK_LOCATOR+"\"");
        enterUserAccountLink = driver.findElement(ENTER_USER_ACCOUNT_LINK_LOCATOR);
        Log4Test.info(getCurrentTimestamp() + ": WebElement [User Account Link] initialized");
        enterUserAccountLink.click();
        Log4Test.info(getCurrentTimestamp() + ": performed click event on the WebElement [User Account Link]");
    }

    public boolean loginFormIsDisplayed(){
        Log4Test.info(getCurrentTimestamp() + ": WebElement [loginTitle] initialization by locator =\""+LOGIN_TITLE_LOCATOR+"\"");
        loginTitle = driver.findElement(LOGIN_TITLE_LOCATOR);
        Boolean result = loginTitle.isDisplayed();
        Log4Test.info(getCurrentTimestamp() + ": WebElement [Login Form] displayed =\""+ Boolean.toString(result)+"\"");
        return result;
    }

    public boolean userAccountLinkIsDisplayed(){
        Log4Test.info(getCurrentTimestamp() + ": WebElement [userAccountLink] initialization by locator =\""+ENTER_USER_ACCOUNT_LINK_LOCATOR+"\"");
        userAccountLink=driver.findElement(ENTER_USER_ACCOUNT_LINK_LOCATOR);
        Boolean result = userAccountLink.isDisplayed();
        Log4Test.info(getCurrentTimestamp() + ": WebElement [userAccountLink] displayed =\""+ Boolean.toString(result)+"\"");
        return result;
    }

    public boolean loginFacebookButtonIsDisplayed(){
        Log4Test.info(getCurrentTimestamp() + ": WebElement [loginFacebookButton] initialization by locator =\""+LOGIN_FACEBOOK_LOCATOR+"\"");
        loginFacebookButton=driver.findElement(LOGIN_FACEBOOK_LOCATOR);
        Boolean result = loginFacebookButton.isDisplayed();
        Log4Test.info(getCurrentTimestamp() + ": WebElement [loginFacebookButton] displayed =\""+ Boolean.toString(result)+"\"");
        return result;
    }

    public boolean rememberMeCheckBoxIsDisplayed(){
        Log4Test.info(getCurrentTimestamp() + ": WebElement [rememberMeCheckBox] initialization by locator =\""+REMEMBER_ME_CHECKBOX_LOCATOR+"\"");
        rememberMeCheckBox=driver.findElement(REMEMBER_ME_CHECKBOX_LOCATOR);
        Boolean result = rememberMeCheckBox.isDisplayed();
        Log4Test.info(getCurrentTimestamp() + ": WebElement [rememberMeCheckBox] displayed =\"" + Boolean.toString(result) + "\"");
        return result;
    }

    public boolean loginVKButtonIsDisplayed(){
        Log4Test.info(getCurrentTimestamp() + ": WebElement [loginVKButton] initialization by locator =\""+LOGIN_VK_LOCATOR+"\"");
        loginVKButton=driver.findElement(LOGIN_VK_LOCATOR);
        Boolean result = loginVKButton.isDisplayed();
        Log4Test.info(getCurrentTimestamp() + ": WebElement [loginVKButton] displayed =\"" + Boolean.toString(result) + "\"");
        return result;
    }

    public void waitForLoginFormIsDisplayed(){
        webDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(LOGIN_TITLE_LOCATOR));
        loginTitle = driver.findElement(LOGIN_TITLE_LOCATOR);
        webDriverWait(driver).until(ExpectedConditions.visibilityOf(loginTitle));
    }

    public void clickLoginViaFacebook(){
        if (rememberMeCheckBoxIsDisplayed()){
            if (rememberMeCheckBox.isEnabled()){
                Log4Test.info(getCurrentTimestamp() + ": disabling WebElement [rememberMeCheckBox]");
                rememberMeCheckBox.click();
            }
        }
        if (loginFacebookButtonIsDisplayed()) {
            Log4Test.info(getCurrentTimestamp() + ": sending WebElement [loginFacebookButton] click event.");
            loginFacebookButton.click();
            Iterator<String> itererator = driver.getWindowHandles().iterator();
            mainWindowId=itererator.next();
            String  newAdwinID = itererator.next();
            driver.switchTo().window(newAdwinID);
        }
        else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [loginFacebookButton] is not displayed on the page.");
        }
    }

    public void clickLoginViaVK(){
        if (loginVKButtonIsDisplayed()) {
            Log4Test.info(getCurrentTimestamp() + ": sending WebElement [loginVKButton] click event.");
            loginVKButton.click();}
        else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [loginVKButton] is not displayed on the page.");
        }
    }

    public Boolean areRightUpperConnerCredentialsCorrect(String[] words) {
        boolean result = false;
        if (userAccountLinkIsDisplayed()) {
            String linkText = userAccountLink.getText().toLowerCase();
            for (String word : words) {
                if (linkText.equals(word.toLowerCase())) result = true;
            }
        }
        return result;
    }

    public void switchToParentWindow(){
        driver.switchTo().window(mainWindowId);
        webDriverWait(driver).until(ExpectedConditions.titleContains(PAGE_TITLE));
    }

}
