package pages.rozetka;


import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log4Test;

public class FacebookLoginPage extends TestBase {
    private final By EMAIL_FIELD_LOCATOR = By.id("email");
    private final By PASSWORD_FIELD_LOCATOR = By.id("pass");
    private final By LOGIN_BUTTON_LOCATOR = By.id("u_0_2");
    private final String PAGE_URL = "www.facebook.com";
    private final String PAGE_TITLE = "Facebook";
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement loginButton;

    public FacebookLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean emailFieldIsDisplayed(){
        emailField = driver.findElement(EMAIL_FIELD_LOCATOR);
        boolean result = emailField.isDisplayed();
        if (result) {
            Log4Test.info(getCurrentTimestamp() + ": WebElement [emailField] is displayed");
        }else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [emailField] is not displayed on the page.");
        }
        return result;
    }

    public boolean passwordFieldIsDisplayed(){
        passwordField = driver.findElement(PASSWORD_FIELD_LOCATOR);
        boolean result = passwordField.isDisplayed();
        if (result) {
            Log4Test.info(getCurrentTimestamp() + ": WebElement [passwordField] is displayed");
        }else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [passwordField] is not displayed on the page.");
        }
        return result;
    }

    public void enterFacebookLogin(String login) {
        webDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(EMAIL_FIELD_LOCATOR));
        if (emailFieldIsDisplayed()){
            Log4Test.info(getCurrentTimestamp() + ": sending WebElement [emailField] input string.");
            emailField.sendKeys(login);
        }
    }

    public void enterFacebookPassword(String password) {
        webDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PASSWORD_FIELD_LOCATOR));
        if (passwordFieldIsDisplayed()){
            Log4Test.info(getCurrentTimestamp() + ": sending WebElement [passwordField] input string.");
            passwordField.sendKeys(password);
        }
    }

    public boolean loginButtonIsDisplayed(){
        loginButton = driver.findElement(LOGIN_BUTTON_LOCATOR);
        boolean result = loginButton.isDisplayed();
        if (result) {
            Log4Test.info(getCurrentTimestamp() + ": WebElement [loginButton] is displayed");
        }else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [loginButton] is not displayed on the page.");
        }
        return result;
    }

    public void clickLoginButton() {
        webDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LOGIN_BUTTON_LOCATOR));
        if (loginButtonIsDisplayed()){
            Log4Test.info(getCurrentTimestamp() + ": sending WebElement [passwordField] click event.");
            loginButton.click();
        }
    }

    public void waitForPageLoad() {
        Log4Test.info(getCurrentTimestamp() + ": Opening page with address =\"" + driver.getCurrentUrl() +"\"");
        Log4Test.info(getCurrentTimestamp() + ": wait until page URL contains \"" + PAGE_URL + "\"");
        webDriverWait(driver).until(ExpectedConditions.titleContains(PAGE_TITLE));
        Log4Test.info(getCurrentTimestamp() + ": loaded page URL is\"" + driver.getCurrentUrl() + "\"");
        Log4Test.info(getCurrentTimestamp() + ": wait until page title contains =\"" + PAGE_TITLE + "\"");
        webDriverWait(driver).until(ExpectedConditions.titleContains(PAGE_TITLE));
        Log4Test.info(getCurrentTimestamp() + ": After timeout opened page title is =\"" + driver.getTitle() + "\"");
    }
}

