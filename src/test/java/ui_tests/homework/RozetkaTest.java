package ui_tests.homework;


import core.TestBase;
import org.testng.annotations.Test;
import pages.rozetka.FacebookLoginPage;
import pages.rozetka.RozetkaMainPage;
import pages.rozetka.RozetkaPersonalInformationPage;

import static org.testng.AssertJUnit.assertTrue;

public class RozetkaTest extends TestBase{
    private final String PAGE_URL = "http://rozetka.com.ua/";
    private final String FIRSTNAME = "Oleksandr";
    private final String LASTNAME  = "Dudchenko";
    private final String NICKNAME = "dolv";
    private final String EXPECTED_PAGE_HEADER= "Мои настройки";
    private final String EXPECTED_PAGE_URL= "https://my.rozetka.com.ua/";
    @Test
    public void verifyIfMyOptionsIsOpened() {

        RozetkaMainPage rozetkaMainPage = new RozetkaMainPage(driver);
        rozetkaMainPage.open(PAGE_URL);
        assertTrue(rozetkaMainPage.isOpened(PAGE_URL));

        rozetkaMainPage.clickEnterUserAccountLink();
        rozetkaMainPage.waitForLoginFormIsDisplayed();

        assertTrue(rozetkaMainPage.loginFormIsDisplayed());
        rozetkaMainPage.clickLoginViaFacebook();


        FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);
        facebookLoginPage.waitForPageLoad();

        facebookLoginPage.enterFacebookLogin(RozetkaTestInputData.getFacebookLogin());

        facebookLoginPage.enterFacebookPassword(RozetkaTestInputData.getFacebookPassword());

        facebookLoginPage.clickLoginButton();

//      Here we check if UpperRight cornerr link contains some of the provided credentials;
        String[] credentials = {FIRSTNAME, LASTNAME, NICKNAME};
        assertTrue(rozetkaMainPage.areRightUpperConnerCredentialsCorrect(credentials));

        rozetkaMainPage.clickEnterUserAccountLink();

        RozetkaPersonalInformationPage rosetkaPersonalInformationPage = new RozetkaPersonalInformationPage(driver);
        rosetkaPersonalInformationPage.waitForPageLoad();


        assertTrue(rosetkaPersonalInformationPage.getPageHeaderText().toLowerCase().contains(EXPECTED_PAGE_HEADER) & rosetkaPersonalInformationPage.getCurrentURL().contains(EXPECTED_PAGE_URL));

    }

}
