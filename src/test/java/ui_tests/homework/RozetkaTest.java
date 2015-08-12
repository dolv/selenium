package ui_tests.homework;


import core.BrowserTypes;
import core.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.rozetka.FacebookLoginPage;
import pages.rozetka.RozetkaMainPage;
import pages.rozetka.RozetkaPersonalInformationPage;
import ui_tests.lesson_7.TestData;
import utils.Log4Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

public class RozetkaTest extends TestBase{
    private final String PAGE_URL = "http://rozetka.com.ua/";
    private final String FIRSTNAME = "Oleksandr";
    private final String LASTNAME  = "Dudchenko";
    private final String NICKNAME = "dolv";
    private final String EXPECTED_PAGE_HEADER= "Личные данные";
    private final String EXPECTED_PAGE_URL= "https://my.rozetka.com.ua/";
    private final String SIGNOUT_URL= "https://my.rozetka.com.ua/signout/";
    @Test
    public void verifyIfMyOptionsIsOpened() {
        RozetkaTestInputData credentials = new RozetkaTestInputData();
        RozetkaMainPage rozetkaMainPage = new RozetkaMainPage(driver);
        rozetkaMainPage.open(PAGE_URL);
        assertTrue(rozetkaMainPage.isOpened(PAGE_URL));

        rozetkaMainPage.clickEnterUserAccountLink();
        rozetkaMainPage.waitForLoginFormIsDisplayed();

        assertTrue(rozetkaMainPage.loginFormIsDisplayed());
        rozetkaMainPage.clickLoginViaFacebook();


        FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);
        facebookLoginPage.waitForPageLoad();

        facebookLoginPage.enterFacebookLogin(credentials.getFacebookLogin());
        facebookLoginPage.enterFacebookPassword(credentials.getFacebookPassword());
        facebookLoginPage.clickLoginButton();

        rozetkaMainPage.switchToParentWindow();

        String[] possibleWords = {FIRSTNAME, LASTNAME, NICKNAME};
        assertTrue(rozetkaMainPage.areRightUpperConnerCredentialsCorrect(possibleWords));

        rozetkaMainPage.clickEnterUserAccountLink();

        RozetkaPersonalInformationPage rosetkaPersonalInformationPage = new RozetkaPersonalInformationPage(driver);
        rosetkaPersonalInformationPage.waitForPageLoad();

        assertTrue(rosetkaPersonalInformationPage.getCurrentURL().contains(EXPECTED_PAGE_URL));
        assertTrue(rosetkaPersonalInformationPage.isOpenedOptionsPageIsMine(possibleWords));

    }
    @AfterTest
    public void logOut(){
        Log4Test.info("Test complete. Loggin out from the page.");
        driver.get(SIGNOUT_URL);
    }

}
