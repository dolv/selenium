package pages.rozetka;



import core.BrowserTypes;
import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui_tests.lesson_7.TestData;
import utils.Log4Test;



public class RozetkaMainPage extends TestBase {
    private final String PAGE_TITLE = "ROZETKA";
    private By ENTER_USER_ACCOUNT_LINK_LOCATOR = By.xpath(".//*[@id='header_user_menu_parent']//a");
    private final By ENTER_USER_ACCOUNT_LINK_LOCATOR1 = By.name("profile");
    private final By ENTER_USER_ACCOUNT_LINK_LOCATOR2 = By.name("splash-button");
    private final By ENTER_USER_ACCOUNT_LINK_LOCATOR_IE = By.name("signin");
    private final By LOGIN_TITLE_LOCATOR = By.xpath("//div[@class='popup-css popup-auth']");
    private final By LOGIN_VK_LOCATOR = By.xpath("//div[@type='vkontakte']//a");
    private final By LOGIN_FACEBOOK_LOCATOR = By.xpath("//div[@type='facebook']//a");
    private final By REMEMBER_ME_CHECKBOX_LOCATOR = By.xpath("//label[@class='checkbox auth-f-i-remember']/span");
    private final By MENU_ITEM_NOTEBOOKS_TABLETS_AND_PCS_LOCATOR = By.xpath("//li[@menu_id='2']/a");
    private WebElement menuItemNotebooksTabletsAndPCs;
    private WebElement loginTitle;
    private WebElement loginVKButton;
    private WebElement loginFacebookButton;
    private WebElement rememberMeCheckBox;
    private WebElement enterUserAccountLink;
    private WebElement userAccountLink;
    private String popupWindowId;
    private String mainWindowId;

    public RozetkaMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(final String PAGE_URL) {

        Log4Test.info(getCurrentTimestamp() + ": Opening page with address =\""+PAGE_URL+"\"");
        //This wrapper is for IE in particular;
        try {
            driver.get(PAGE_URL);
        }catch (Exception e){
            e.printStackTrace();
            Log4Test.error(getCurrentTimestamp() + ": Page opening timeout. Reloading.");
            driver.get(PAGE_URL);
        }
        if (TestData.BROWSER_NAME.equals(BrowserTypes.IE)){
            Log4Test.info(getCurrentTimestamp() + ": wait until page contains element located by =\"" + ENTER_USER_ACCOUNT_LINK_LOCATOR_IE.toString() + "\"");
            webDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(ENTER_USER_ACCOUNT_LINK_LOCATOR_IE));
            Log4Test.info(getCurrentTimestamp() + ": After timeout opened page address is \"" + driver.getCurrentUrl() + "\"");
        }
        else {
            Log4Test.info(getCurrentTimestamp() + ": wait until page title contains \"" + PAGE_TITLE + "\"");
            webDriverWait(driver).until(ExpectedConditions.titleContains(PAGE_TITLE));
            Log4Test.info(getCurrentTimestamp() + ": After timeout opened page title is \"" + driver.getTitle() + "\"");
        }
        mainWindowId=driver.getWindowHandle();
    }

    public boolean isOpened(String PAGE_URL){
        Boolean result = false;
        if (driver.getCurrentUrl().contains(PAGE_URL)){
            result = true;
            Log4Test.info(getCurrentTimestamp() + ": isOpened() result =\"" + result + "\"");
        } else {
            Log4Test.error(getCurrentTimestamp() + ": Openned page URL = \"" + driver.getCurrentUrl() + " differs from passer value of =\"" + PAGE_URL + "\"");
        }
        return result;
    }

    public void clickEnterUserAccountLink(){
//        if (TestData.BROWSER_NAME.equals(BrowserTypes.IE)){
//            ENTER_USER_ACCOUNT_LINK_LOCATOR=ENTER_USER_ACCOUNT_LINK_LOCATOR_IE;
//        }
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

    public boolean menuItemNotebooksTabletsAndPCsIsDisplayed(){
        Log4Test.info(getCurrentTimestamp() + ": WebElement [menuItemNotebooksTabletsAndPCs] initialization by locator =\""+MENU_ITEM_NOTEBOOKS_TABLETS_AND_PCS_LOCATOR+"\"");
        menuItemNotebooksTabletsAndPCs=driver.findElement(MENU_ITEM_NOTEBOOKS_TABLETS_AND_PCS_LOCATOR);
        Boolean result = menuItemNotebooksTabletsAndPCs.isDisplayed();
        Log4Test.info(getCurrentTimestamp() + ": WebElement [menuItemNotebooksTabletsAndPCs] displayed =\""+ Boolean.toString(result)+"\"");
        return result;
    }

    public void clickMenuItemNotebooksTabletsAndPCs(){
        if (menuItemNotebooksTabletsAndPCsIsDisplayed()){
            Log4Test.info(getCurrentTimestamp() + ": sending WebElement [menuItemNotebooksTabletsAndPCs] click event.");
            menuItemNotebooksTabletsAndPCs.click();
            if (TestData.BROWSER_NAME.equals(BrowserTypes.IE)){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            Log4Test.error(getCurrentTimestamp() + ": WebElement [menuItemNotebooksTabletsAndPCs] is not displayed on the page.");
        }
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
        Log4Test.info(getCurrentTimestamp() + ": WebElement [loginVKButton] initialization by locator =\"" + LOGIN_VK_LOCATOR + "\"");
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
            if (TestData.BROWSER_NAME.equals(BrowserTypes.IE)){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            final ExpectedCondition<Boolean> popupWindowIsLoaded = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver wd) {
                    wd = driver;
                    int openedWindows = wd.getWindowHandles().size();
                    boolean popupWindowIsLoaded = openedWindows>1?true:false;
                    if (!popupWindowIsLoaded) Log4Test.error(getCurrentTimestamp() + ": WebDriver contains " + String.valueOf(openedWindows) + " opened windows.");
                    return popupWindowIsLoaded;
                }
            };

            webDriverWait(driver).until(popupWindowIsLoaded);
            String[] handles = driver.getWindowHandles().toArray(new String[0]);
            popupWindowId = handles[handles.length - 1];
            driver.switchTo().window(popupWindowId);
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
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (TestData.BROWSER_NAME) {
            case FIRE_FOX:
            case IE:
                ENTER_USER_ACCOUNT_LINK_LOCATOR=ENTER_USER_ACCOUNT_LINK_LOCATOR1;
                break;
            case CHROME:
                driver.switchTo().window(mainWindowId);
                ENTER_USER_ACCOUNT_LINK_LOCATOR=ENTER_USER_ACCOUNT_LINK_LOCATOR2;
                break;
            default: break;
        }
//        if (TestData.BROWSER_NAME.equals(BrowserTypes.IE)) {
//            webDriverWait(driver).until(ExpectedConditions.elementToBeClickable(ENTER_USER_ACCOUNT_LINK_LOCATOR));
//        }else
            webDriverWait(driver).until(ExpectedConditions.elementToBeClickable(ENTER_USER_ACCOUNT_LINK_LOCATOR));

        userAccountLink = driver.findElement(ENTER_USER_ACCOUNT_LINK_LOCATOR);
        String linkText = userAccountLink.getText().toLowerCase();

        for (String word : words) if (linkText.contains(word.toLowerCase())) result = true;
        return result;
    }

    public void switchToParentWindow(){
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] handles = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(handles[handles.length - 1]);
        if (TestData.BROWSER_NAME==BrowserTypes.CHROME){
            driver.switchTo().defaultContent();
        }
    }

}
