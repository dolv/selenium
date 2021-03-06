package pages.rozetka;

import core.BrowserTypes;
import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import core.TestData;
import utils.Log4Test;

import java.util.Set;


public class RozetkaMainPage extends TestBase {

    private final String PAGE_TITLE = "ROZETKA";

    private By ENTER_USER_ACCOUNT_LINK_LOCATOR = By.xpath(".//*[@id='header_user_menu_parent']//a");

    private final By ENTER_USER_ACCOUNT_LINK_LOCATOR1 = By.name("profile");

    private final By ENTER_USER_ACCOUNT_LINK_LOCATOR2 = By.name("splash-button");

    private final By ENTER_USER_ACCOUNT_LINK_LOCATOR_IE = By.name("signin");

    private final By LOGIN_TITLE_LOCATOR = By.xpath("//div[@class='popup-css popup-auth']");

    private final By LOGIN_VK_LOCATOR = By.xpath("//div[@type='vkontakte']//a");

    private final By LOGIN_FACEBOOK_LOCATOR = By.xpath("//div[@type='facebook']//a");

    private final By REMEMBER_ME_CHECKBOX_LOCATOR = By.xpath("//label[@class='checkbox auth-f-i-remember']");

    private By MENU_ITEM_NOTEBOOKS_TABLETS_AND_PCS_LOCATOR;

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

        Log4Test.info("     -----=====-----");

        Log4Test.info("Instantiating a RozetkaMainPage");

        this.driver = driver;

        switch (TestData.BROWSER_NAME){

            case IE:
                MENU_ITEM_NOTEBOOKS_TABLETS_AND_PCS_LOCATOR = By.xpath("//li[@menu_id='2']");
                break;

            default:
                MENU_ITEM_NOTEBOOKS_TABLETS_AND_PCS_LOCATOR = By.xpath("//li[@menu_id='2']/a");
        }

        Log4Test.info("RozetkaMainPage instance has been received.");

    }

    public void open(final String PAGE_URL) {

        Log4Test.info("Opening a page with an address =\""+PAGE_URL+"\"");

        //This wrapper is for IE in particular;
        try {

            driver.get(PAGE_URL);

        }catch (Exception e){

            e.printStackTrace();
            Log4Test.error("Page opening timeout. Reloading.");
            driver.get(PAGE_URL);

        }

        if (TestData.BROWSER_NAME.equals(BrowserTypes.IE)){

            Log4Test.info("Wait until the page contains element located by =\"" + ENTER_USER_ACCOUNT_LINK_LOCATOR_IE.toString() + "\"");

            webDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(ENTER_USER_ACCOUNT_LINK_LOCATOR_IE));

            Log4Test.info("After a timeout the opened page address is \"" + driver.getCurrentUrl() + "\"");

        }

        else {

            Log4Test.info("Waiting until the page title contains \"" + PAGE_TITLE + "\"");

            webDriverWait(driver).until(ExpectedConditions.titleContains(PAGE_TITLE));

            Log4Test.info("After timeout the opened page title is \"" + driver.getTitle() + "\"");

        }

        mainWindowId=driver.getWindowHandle();

    }

    public boolean isOpened(String PAGE_URL){

        Boolean result = false;

        if (driver.getCurrentUrl().contains(PAGE_URL)){

            result = true;
            Log4Test.info("The page with the address of \"" + PAGE_URL + "\" is opened.");

        } else {

            Log4Test.error("The opened page URL = \"" + driver.getCurrentUrl() + " differs from expected value of =\"" + PAGE_URL + "\".");

        }

        return result;

    }

    public void clickEnterUserAccountLink(){

        Log4Test.info("Initializing WebElement [User Account Link] initialization by locator =\""+ENTER_USER_ACCOUNT_LINK_LOCATOR.toString()+"\".");

        enterUserAccountLink = driver.findElement(ENTER_USER_ACCOUNT_LINK_LOCATOR);

        Log4Test.info("The WebElement [User Account Link] has been initialized.");

        Log4Test.info("Sending click event to WebElement [User Account Link].");

        enterUserAccountLink.click();

        Log4Test.info("Click event on the WebElement [User Account Link] has performed.");

    }

    public boolean loginFormIsDisplayed(){

        Log4Test.info("Started verification if WebElement [loginForm] is Displayed.");

        boolean result = webElementIsDisplayed("loginForm", LOGIN_TITLE_LOCATOR);

        if (result) {

            loginTitle = driver.findElement(LOGIN_TITLE_LOCATOR);

            Log4Test.info("     WebElement [loginTitle] which is an inner part of [loginForm] is displayed. Assuming that [loginForm] is displayed entirely.");

        }else {

            Log4Test.info("     WebElement [loginTitle] which is an inner part of [loginForm] is not displayed. Assuming that [loginForm] is not displayed also.");

        }

        Log4Test.info("Completed verification if WebElement [loginForm] is Displayed.");

        logResult(result);

        return result;

    }

    public boolean userAccountLinkIsDisplayed(){

        boolean result = webElementIsDisplayed("userAccountLink",ENTER_USER_ACCOUNT_LINK_LOCATOR);

        if (result) userAccountLink = driver.findElement(ENTER_USER_ACCOUNT_LINK_LOCATOR);

        return result;

    }

    public boolean menuItemNotebooksTabletsAndPCsIsDisplayed(){

        boolean result = webElementIsDisplayed("menu item Ноутбуки, планшеты и компьютеры",MENU_ITEM_NOTEBOOKS_TABLETS_AND_PCS_LOCATOR);

        if (result) menuItemNotebooksTabletsAndPCs = driver.findElement(MENU_ITEM_NOTEBOOKS_TABLETS_AND_PCS_LOCATOR);

        return result;

    }

    public void clickMenuItemNotebooksTabletsAndPCs(){

        Log4Test.info("Invoking [clickMenuItemNotebooksTabletsAndPCs] method.");

        if (menuItemNotebooksTabletsAndPCsIsDisplayed()){

            Log4Test.info("Sending WebElement [menuItemNotebooksTabletsAndPCs] click event.");

            menuItemNotebooksTabletsAndPCs.click();

            Log4Test.info("WebElement [menuItemNotebooksTabletsAndPCs] click event performed.");

            if (TestData.BROWSER_NAME.equals(BrowserTypes.IE)){

                Log4Test.info("Applying additional wait of 5 sec due to IE usage.");

                try {

                    Thread.sleep(5000);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

                Log4Test.info("Applied additional wait of 5 sec is over");

            }

        } else {

            Log4Test.error("WebElement [menuItemNotebooksTabletsAndPCs] is not displayed on the page. It is imposable to click it. Exiting the method.");

        }

        Log4Test.info("The method [clickMenuItemNotebooksTabletsAndPCs] has completed.");

    }

    public boolean loginFacebookButtonIsDisplayed(){

        boolean result = webElementIsDisplayed("login via Facebook button",LOGIN_FACEBOOK_LOCATOR);

        if (result) loginFacebookButton = driver.findElement(LOGIN_FACEBOOK_LOCATOR);

        return result;

    }

    public boolean rememberMeCheckBoxIsDisplayed(){

        boolean result = webElementIsDisplayed("remember me check box",REMEMBER_ME_CHECKBOX_LOCATOR);

        if (result) rememberMeCheckBox = driver.findElement(REMEMBER_ME_CHECKBOX_LOCATOR);

        return result;

    }

    public boolean loginVKButtonIsDisplayed(){

        boolean result = webElementIsDisplayed("login via VK Button",LOGIN_VK_LOCATOR);

        if (result) loginVKButton = driver.findElement(LOGIN_VK_LOCATOR);

        return result;

    }

    public void waitForLoginFormIsDisplayed(){
        webDriverWaitUntilElementIsVisible(driver, LOGIN_TITLE_LOCATOR);
    }

    public void clickLoginViaFacebook(){

        Log4Test.info(" Invoking [clickLoginViaFacebook] method.");

        Log4Test.info(" Ensuring that [rememberMeCheckBox] is not checked.");

        if (rememberMeCheckBoxIsDisplayed()){

            if (rememberMeCheckBox.findElement(By.tagName("input")).isSelected()){

                Log4Test.info("     WebElement [rememberMeCheckBox] is checked.");

                rememberMeCheckBox.click();

                Log4Test.info("     Unchecked.");

            }

        }

        Log4Test.info(" Ensured that [rememberMeCheckBox] is not checked.");

        if (loginFacebookButtonIsDisplayed()) {

            Log4Test.info(" Sending WebElement [loginFacebookButton] click event.");

            loginFacebookButton.click();

            Log4Test.info(" WebElement [loginFacebookButton] click performed.");

            Log4Test.info(" A new window should appear. It is needed to switch to it.");

            if (TestData.BROWSER_NAME.equals(BrowserTypes.IE)){

                Log4Test.info(" Applying additional wait of 5 sec due to IE usage.");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log4Test.info(" Applied additional wait of 5 sec is over.");

            }

            Log4Test.info(" Applying explicit wait until WebDriver has more than one window.");

            final ExpectedCondition<Boolean> popupWindowIsLoaded = new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver wd) {

                    wd = driver;

                    int openedWindows = wd.getWindowHandles().size();

                    boolean popupWindowIsLoaded = openedWindows > 1;

                    if (!popupWindowIsLoaded) Log4Test.error("         WebDriver contains " + String.valueOf(openedWindows) + " opened window(s).");

                    return popupWindowIsLoaded;

                }

            };

            webDriverWait(driver).until(popupWindowIsLoaded);

            Log4Test.info(" Applied explicit wait until driver has more than one window is over.");

            Log4Test.info(" Switching to the popup window.");

            Set<String> windowHandles = driver.getWindowHandles();

            String[] handles = windowHandles.toArray(new String[windowHandles.size()]);

            popupWindowId = handles[handles.length - 1];

            driver.switchTo().window(popupWindowId);

            Log4Test.info(" Switching to the popup window completed.");
        } else {

            Log4Test.error("     WebElement [loginFacebookButton] is not displayed on the page thus can not be clicked. ");

        }

        Log4Test.info(" The [clickLoginViaFacebook] method completed.");

    }

    public void clickLoginViaVK(){

        Log4Test.info(" Invoking [clickLoginViaVK] procedure.");

        if (rememberMeCheckBoxIsDisplayed()){

            Log4Test.info("     Ensuring that [rememberMeCheckBox] is not checked.");

            if (rememberMeCheckBox.isEnabled()){

                Log4Test.info("           WebElement [rememberMeCheckBox] is checked.");

                rememberMeCheckBox.click();

                Log4Test.info("           Unchecked.");

            }

        }

        if (loginFacebookButtonIsDisplayed()) {

            Log4Test.info("     Sending WebElement [loginVKButton] click event.");

            loginVKButton.click();

            Log4Test.info("     WebElement [loginVKButton] click performed.");

            Log4Test.info("     A new window should appear after. It is needed to switch to it.");

            if (TestData.BROWSER_NAME.equals(BrowserTypes.IE)){

                Log4Test.info("     Applying additional wait of 5 sec due to IE usage.");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log4Test.info("     Applied additional wait of 5 sec is over.");
            }

            Log4Test.info("     Applying explicit wait until driver has more than one window.");

            final ExpectedCondition<Boolean> popupWindowIsLoaded = new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver wd) {

                    wd = driver;

                    int openedWindows = wd.getWindowHandles().size();

                    boolean popupWindowIsLoaded = openedWindows > 1;

                    if (!popupWindowIsLoaded) Log4Test.error("         WebDriver contains " + String.valueOf(openedWindows) + " opened window(s).");

                    return popupWindowIsLoaded;

                }

            };

            webDriverWait(driver).until(popupWindowIsLoaded);

            Log4Test.info("     Applied explicit wait until driver has more than one window is over.");

            Log4Test.info("     Switching to the popup window.");

            Set<String> windowHandles = driver.getWindowHandles();

            String[] handles = windowHandles.toArray(new String[windowHandles.size()]);

            popupWindowId = handles[handles.length - 1];

            driver.switchTo().window(popupWindowId);

            Log4Test.info("     Switching to the popup window completed.");

        } else {

            Log4Test.error("     WebElement [loginVKButton] is not displayed on the page thus can not be clicked. ");

        }

        Log4Test.info(" The [clickLoginViaVK] method completed.");

    }

    public Boolean areRightUpperConnerCredentialsCorrect(String[] words) {

        Log4Test.info(" Started verification if credentials appeared in the right upper conner of the page are correct.");

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
            webDriverWait(driver).until(ExpectedConditions.elementToBeClickable(ENTER_USER_ACCOUNT_LINK_LOCATOR));

        userAccountLink = driver.findElement(ENTER_USER_ACCOUNT_LINK_LOCATOR);

        String linkText = userAccountLink.getText().toLowerCase();
        int i=0;
        for (String word : words) if (linkText.contains(word.toLowerCase())) i++;
        result = words.length==i;

        Log4Test.info(" Completed verification of credentials appeared in right upper conner of the page.");

        logResult (result);

        return result;

    }

    public void switchToParentWindow(){

        Log4Test.info(" Switching back to parent window.");

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<String> windowHandles = driver.getWindowHandles();

        String[] handles = windowHandles.toArray(new String[windowHandles.size()]);

        driver.switchTo().window(handles[handles.length - 1]);

        if (TestData.BROWSER_NAME==BrowserTypes.CHROME){

            driver.switchTo().defaultContent();

        }

        Log4Test.info(" Now focus is on parent window.");

    }

}
