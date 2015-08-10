package ui_tests.lesson_6;

import core.TestBase;
import org.testng.annotations.Test;
import pages.google.GoogleSearchPage;
import static org.testng.AssertJUnit.assertTrue;

public class GoogleSearchTest extends TestBase {

    private String googleWebSite = "https://www.google.com.ua";
    private String searchText = "Selenium";

    @Test
    public void seleniumSearchTest() {
        GoogleSearchPage onGoogleSearchPage = new GoogleSearchPage(driver);
        onGoogleSearchPage.open(googleWebSite);
        onGoogleSearchPage.searchText(searchText);
        assertTrue(onGoogleSearchPage.findURL().getText().contains(searchText));
    }


}
