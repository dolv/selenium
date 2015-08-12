package pages.rozetka;


import core.BrowserTypes;
import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui_tests.lesson_7.TestData;
import utils.Log4Test;

import java.util.List;

public class FacebookLoginPage extends TestBase {
    private final By EMAIL_FIELD_LOCATOR = By.id("email");
    private final By PASSWORD_FIELD_LOCATOR = By.id("pass");
    private final By LOGIN_BUTTON_LOCATOR = By.id("u_0_2");
    private final By REMEMBER_ME_CHECKBOX_LOCATOR = By.id("persist_box");
    private final String PAGE_URL = "www.facebook.com";
    private final String PAGE_TITLE = "Facebook";
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement rememberMeCheckBox;
    private WebElement loginButton;

    public FacebookLoginPage(WebDriver driver) {
        Log4Test.info("Instantiating a FacebookLoginPage");
        this.driver = driver;
        Log4Test.info("FacebookLoginPage instance has been received.");
    }

    public boolean emailFieldIsDisplayed(){
        Log4Test.info(" Started verification if WebElement [emailField] is Displayed.");
        Log4Test.info("     Initializing WebElement [emailField] by locator =\""+EMAIL_FIELD_LOCATOR.toString()+"\"");
        emailField = driver.findElement(EMAIL_FIELD_LOCATOR);
        Log4Test.info("     WebElement [emailField] initialized successfully.");
        Log4Test.info("     Checking if WebElement [emailField] is Displayed.");
        boolean result = emailField.isDisplayed();
        Log4Test.info(" Completed verification if WebElement [emailField] is Displayed.");
        logResult(result);
        return result;
    }

    public boolean rememberMeCheckBoxIsDisplayed(){
        Log4Test.info(" Started verification if WebElement [rememberMeCheckBox] is Displayed.");
        Log4Test.info("     Initializing WebElement [rememberMeCheckBox] by locator =\""+REMEMBER_ME_CHECKBOX_LOCATOR.toString()+"\"");
        rememberMeCheckBox=driver.findElement(REMEMBER_ME_CHECKBOX_LOCATOR);
        Log4Test.info("     WebElement [rememberMeCheckBox] initialized successfully.");
        Log4Test.info("     Checking if WebElement [rememberMeCheckBox] is Displayed.");
        Boolean result = rememberMeCheckBox.isDisplayed();
        Log4Test.info(" Completed verification if WebElement [rememberMeCheckBox] is Displayed.");
        logResult(result);
        return result;
    }

    public boolean passwordFieldIsDisplayed(){
        Log4Test.info(" Started verification if WebElement [passwordField] is Displayed.");
        Log4Test.info("     Initializing WebElement [passwordField] by locator =\""+PASSWORD_FIELD_LOCATOR.toString()+"\"");
        passwordField = driver.findElement(PASSWORD_FIELD_LOCATOR);
        Log4Test.info("     WebElement [passwordField] initialized successfully.");
        Log4Test.info("     Checking if WebElement [passwordField] is Displayed.");
        boolean result = passwordField.isDisplayed();
        Log4Test.info(" Completed verification if WebElement [passwordField] is Displayed.");
        logResult(result);
        return result;
    }

    public void enterFacebookLogin(String login) {
        webDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(EMAIL_FIELD_LOCATOR));
        if (emailFieldIsDisplayed()){
            Log4Test.info("Inserting string \"" + login+"\" into WebElement [emailField].");
            emailField.clear();
            emailField.sendKeys(login);
        }
    }

    public void enterFacebookPassword(String password) {
        webDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PASSWORD_FIELD_LOCATOR));
        if (passwordFieldIsDisplayed()){
            Log4Test.info("Inserting string \"" + password +"\" into WebElement [emailField].");
            passwordField.clear();
            passwordField.sendKeys(password);
        }
    }

    public void uncheckRememberMeCheckBox() {
        Log4Test.info(" Invoking [uncheckRememberMeCheckBox] method.");
        webDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(REMEMBER_ME_CHECKBOX_LOCATOR));
        rememberMeCheckBox = driver.findElement(REMEMBER_ME_CHECKBOX_LOCATOR);
        if (rememberMeCheckBoxIsDisplayed()) {
            if ( rememberMeCheckBox.isSelected()) {
                Log4Test.info(" Unchecking WebElement [rememberMeCheckBox].");
                rememberMeCheckBox.click();
            }
        }
    }

    public boolean loginButtonIsDisplayed(){
        Log4Test.info(" Started verification if WebElement [loginButton] is Displayed.");
        Log4Test.info("     Initializing WebElement [loginButton] by locator =\""+LOGIN_BUTTON_LOCATOR.toString()+"\"");
        loginButton = driver.findElement(LOGIN_BUTTON_LOCATOR);
        Log4Test.info("     WebElement [loginButton] initialized successfully.");
        Log4Test.info("     Checking if WebElement [loginButton] is Displayed.");
        boolean result = loginButton.isDisplayed();
        Log4Test.info(" Completed verification if WebElement [loginButton] is Displayed.");
        logResult (result);
        return result;
    }

    public void clickLoginButton() {
        uncheckRememberMeCheckBox();
        webDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LOGIN_BUTTON_LOCATOR));
        if (loginButtonIsDisplayed()){
            Log4Test.info(" Sending WebElement [loginButton] click event.");
            loginButton.click();
        }
    }

    public void waitForPageLoad() {
        Log4Test.info(" Applying page load wait.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (TestData.BROWSER_NAME){
            case IE:
                webDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(EMAIL_FIELD_LOCATOR));
                break;
            default:
        ExpectedCondition<Boolean> expectedCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wDr) {
                return driver.getCurrentUrl().toLowerCase().contains(PAGE_URL);
            }
        };
        Log4Test.info("     Waiting until page URL contains \"" + PAGE_URL + "\"");
        webDriverWait(driver).until(expectedCondition);

        }
        Log4Test.info("     Loaded page URL is\"" + driver.getCurrentUrl() + "\"");
        Log4Test.info("     Waiting until page title contains =\"" + PAGE_TITLE + "\"");
        webDriverWait(driver).until(ExpectedConditions.titleContains(PAGE_TITLE));
        Log4Test.info("     After timeout opened page title is =\"" + driver.getTitle() + "\"");
        Log4Test.info(" Applied page load wait is over.");
    }
}

