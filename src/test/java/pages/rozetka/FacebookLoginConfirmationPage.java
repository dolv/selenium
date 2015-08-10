package pages.rozetka;


import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FacebookLoginConfirmationPage extends TestBase {

    public FacebookLoginConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By AUTH_HEADER_LOCATOR = By.xpath("//h3[@class='uth-fb-header-title']");
    private WebElement authHeader;

}
