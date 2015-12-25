package pages.google;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage extends TestBase{
    private WebElement searchField;
    private WebElement searchLink;
    //private WebElement findButton;
    private WebDriver driver;

    public GoogleSearchPage (WebDriver driver){
        this.driver = driver;
    }
    public void open (String URL){
        driver.get(URL);
    }
    public void searchText (String searchText){
        searchField = driver.findElement(By.id("lst-ib"));
        searchField.sendKeys(searchText);
        searchField.sendKeys(Keys.ENTER);
      //  findButton = driver.findElement(By.xpath(".//div[@id='sblsbb']/button"));
      //  findButton.click();
    }
    public WebElement findURL(){
        searchLink = driver.findElement(By.xpath("//div[@class='rc']/h3/a"));
        return searchLink;
    }
}
