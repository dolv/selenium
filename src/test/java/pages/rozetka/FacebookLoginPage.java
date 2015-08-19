package pages.rozetka;


import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log4Test;

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

        boolean result = webElementIsDisplayed("emailField",EMAIL_FIELD_LOCATOR);

        if (result) emailField = driver.findElement(EMAIL_FIELD_LOCATOR);

        return result;
    }

    public boolean rememberMeCheckBoxIsDisplayed(){

        boolean result = webElementIsDisplayed("rememberMeCheckBox",REMEMBER_ME_CHECKBOX_LOCATOR);

        if (result) rememberMeCheckBox = driver.findElement(REMEMBER_ME_CHECKBOX_LOCATOR);

        return result;
    }

    public boolean passwordFieldIsDisplayed(){

        boolean result = webElementIsDisplayed("passwordField",PASSWORD_FIELD_LOCATOR);

        if (result) passwordField = driver.findElement(PASSWORD_FIELD_LOCATOR);

        return result;
    }

    public void enterFacebookLogin(String login) {

        if (emailFieldIsDisplayed()){

            Log4Test.info("Inserting string \"" + login+"\" into WebElement [emailField].");

            emailField.clear();

            emailField.sendKeys(login);


        }
    }

    public void enterFacebookPassword(String password) {

        if (passwordFieldIsDisplayed()){

            Log4Test.info("Inserting string password string into WebElement [emailField].");

            passwordField.clear();

            passwordField.sendKeys(password);

        }

    }

    public void uncheckRememberMeCheckBox() {

        if (rememberMeCheckBoxIsDisplayed()) {

            if ( rememberMeCheckBox.isSelected()) {

                Log4Test.info(" Unchecking WebElement [rememberMeCheckBox].");

                rememberMeCheckBox.click();

            }

        }

    }

    public boolean loginButtonIsDisplayed(){

        Log4Test.info(" Started verification if WebElement [loginButton] is Displayed.");

        webDriverWaitUntilElementIsVisible(driver, LOGIN_BUTTON_LOCATOR);

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

        webDriverWaitUntilElementIsVisible(driver, EMAIL_FIELD_LOCATOR);

        webDriverWaitUntilPageTitleContains(driver, PAGE_TITLE);

    }

}

