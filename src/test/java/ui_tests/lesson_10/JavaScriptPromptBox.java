package ui_tests.lesson_10;


import core.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class JavaScriptPromptBox extends TestBase{
    private String website= "http://www.quackit.com/javascript/codes/javascript_prompt.cfm";
    private String inputString = "Bla-bla-bla";
    @Test
    public void nashTest (){
        driver.get(website);
        WebElement iFrame = driver.findElement(By.xpath("//iframe[@name='result1']")) ;
        driver.switchTo().frame(iFrame);

        WebElement btn = driver.findElement(By.xpath("html/body/input"));
        btn.click();

        Alert alert = driver.switchTo().alert();
        alert.sendKeys(inputString);
        alert.accept();
    }
}
