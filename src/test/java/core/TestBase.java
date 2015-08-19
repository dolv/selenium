package core;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.Log4Test;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;

    protected int defaultWaitingTimeInSeconds = 50;

    protected int coordinateX=0;

    protected int coordinateY=0;

    final public String NEWLINE = System.getProperty("line.separator");

    @BeforeSuite
    public void setUP()throws IOException{

        try {

            System.setProperty("log4j.configurationFile", Log4Test.class.getClassLoader().getResource("log4j.properties").getPath());
            System.setProperty("SELENIUM_TEST_LOG_FILE", System.getProperty("user.dir") + "\\application_"+ TestData.BROWSER_NAME.toString()+".log");

        }catch (Exception e){

            System.out.println("Cannot set system property log4j.configurationFile \n" + e.getMessage());

        }

        Log4Test.info("Instantiating a " + TestData.BROWSER_NAME.toString() + " WebDriver ");

        driver = WebDriverFactory.getWebDriver(TestData.BROWSER_NAME);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        switch (TestData.BROWSER_NAME) {

            case IE:
                driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
                break;

            default:
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        }

        Log4Test.info("WebDriver instance has been received.");

    }

    @AfterSuite
    public void tearDown(){

        try {

            driver.quit();

        } catch (Exception e) {

            e.printStackTrace();

        }

        Log4Test.info("WebDriver has quitted.");

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean webElementIsDisplayed(String webElementName, By webElementLocator){

        webElementName=webElementName.isEmpty()?"":"["+webElementName+"] ";

        Log4Test.info("Started verification if WebElement " + webElementName + " is Displayed.");

        webDriverWaitUntilElementIsVisible(driver, webElementLocator);

        Log4Test.info("     Initializing WebElement " + webElementName + "by locator [" + webElementLocator.toString()+"].");

        WebElement webElement=driver.findElement(webElementLocator);

        scrollToWebElement(webElement);

        Log4Test.info("     WebElement " + webElementName +" initialized successfully.");
        Log4Test.info("     Checking if WebElement " + webElementName + " is Displayed.");

        Boolean result = webElement.isDisplayed();

        Log4Test.info("Completed verification if WebElement " + webElementName + " is Displayed.");

        logResult(result);

        return result;
    }

    public boolean webElementIsDisplayed(By webElementLocator){

        return webElementIsDisplayed("", webElementLocator);

    }

    public boolean webElementsAreDisplayed(String webElementListName, By webElementLocator){

        Log4Test.info("Started verification if all items of a WebElements list [" + webElementListName + "] are Displayed.");

        webDriverWaitUntilAllElementsAreVisible(driver, webElementLocator);

        Log4Test.info("     Initializing WebElements list [" + webElementListName + "] by locator [" + webElementLocator.toString()+"].");

        List<WebElement> webElementList=driver.findElements(webElementLocator);

        Log4Test.info("     WebElements list [" + webElementListName +"] initialized successfully.");
        Log4Test.info("     Looping through WebElements list's elements.");

        boolean result = true;

        int i=0; Integer total = webElementList.size();
        for(WebElement element: webElementList) {

            Log4Test.info("        The WebElement item [" + patternedString(++i, total," ") +"/"+total.toString()+"] initialized successfully.");

            scrollToWebElement(element);

            Log4Test.info("            Checking if WebElement item is Displayed.");

            result = element.isDisplayed();

            if (!result){
                Log4Test.info("            The WebElement item is not Displayed. Exiting loop.");
                break;
            }

        }

        Log4Test.info("Completed verification if all items of a WebElements list [" + webElementListName + "] are Displayed.");

        logResult(result);

        return result;

    }

    public boolean doesTheWebElementListContain(final String LISTNAME, By itemsListLocator, final String[] WORDSTOCHECK){

        String message = "Started verification if a WebElements list contains [";

        Log4Test.info(message + String.join(lSep(message), WORDSTOCHECK)+ "].");

        boolean result = false;
        if (WORDSTOCHECK.length>0) {

            webDriverWait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy(itemsListLocator));

            Log4Test.info("Initialising WebElements items list.");

            List<WebElement> ListItems = driver.findElements(itemsListLocator);

            Log4Test.info("The WebElements items list initialized. It contains " + Integer.toString(ListItems.size()) + " item(s).");
            Log4Test.info("Looping through the items.");

            int i=0;
            for (String verificationString : WORDSTOCHECK){

                Log4Test.info("     Working with verification string ["
                        + patternedString(++i, WORDSTOCHECK.length," ")
                        + "/"
                        + Integer.toString(WORDSTOCHECK.length)
                        +"]  with text ["
                        +WORDSTOCHECK[i-1] +"];");

                int j=0;

                for (WebElement element : ListItems) {

                    Log4Test.info("        Got a webelement instanse ["
                            + zeroPatternedString(++j,ListItems.size())
                            +"/"
                            + Integer.toString(ListItems.size())
                            +"] which contains a text: ["+element.getText()+"].");

                    Log4Test.info("        Checking if it contains [" + verificationString + "].");

                    if (element.getText().toLowerCase().contains(verificationString.toLowerCase())) {

                        result = true;
                        Log4Test.info("            COINCIDENCE DETECTED.");
                        break;

                    } else Log4Test.info("            Coincidence has not been found. Skipping...");

                }
                if(!result) {

                    Log4Test.info("The further steps are meaningless. Exiting loop.");
                    break;

                }

            }

            Log4Test.info("The items list processing completed.");

        }else Log4Test.error("There is nothing to verify. Received for verification items list is empty. Verification cancelled.");

        message = "Completed verification if a WebElements list contains [";
        Log4Test.info(message + String.join(lSep(message), WORDSTOCHECK)+ "].");

        logResult(result);

        return result;
    }

    public boolean doesPageContainText(final String TEXT, WebDriver driver) throws IOException {

        Log4Test.info("Started verification if page contains text [" + TEXT + "]");

        boolean result=false;

        Log4Test.info("     Initializing list of the page body nodes. ");
        //Parser parser = new Parser().createParser(driver.findElement(By.tagName("HTML")).toString(), driver.findElement(By.xpath(".//meta[@charset]")).getText());

        webDriverWait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//body//*")));
        List<WebElement> pageBodyChildList = driver.findElements(By.xpath("//body//*"));

        Log4Test.info("     The page body node list initialised "+ Integer.toString(pageBodyChildList.size()) + " with node(s).");
        Log4Test.info("     Looping through the body node(s).");

        int i =0;
        for (WebElement element: pageBodyChildList) {

            Log4Test.info("        Processing the page node [" + patternedString(i++, pageBodyChildList.size(), " ") + "/" + Integer.toString(pageBodyChildList.size()) + "].");

            if (element.isDisplayed()) {

                switch (TestData.BROWSER_NAME) {
                    case CHROME:
                        new Actions(driver).moveToElement(element).perform();
                        break;
                }

                scrollToWebElement(element);

                Log4Test.info("            Checking if it contains [" + TEXT + "]");

                if (element.getText().toLowerCase().contains(TEXT.toLowerCase())){

                    if (element.isDisplayed()) {

                        Log4Test.info("                COINCIDENCE DETECTED.");
                        result = true;
                        break;

                    }

                }else Log4Test.info("                Coincidence not detected. Skipping...");

            }else Log4Test.info("            The node is not displayed. Skipping...");

        }

        Log4Test.info("Completed verification if page contains text [" + TEXT + "]");

        logResult(result);

        return result;

    }

    public String lSep(String s){

        String lineseparator="";

        for(int i = 0;i < s.length()+35; i++) {

            lineseparator += " ";

        } lineseparator+= ";" + NEWLINE;

        return  lineseparator;
    }

    public String getCurrentTimestamp() {return new Timestamp(new java.util.Date().getTime()).toString();}

    public String patternedString(int i, int total, String placeholder){

        String patternt = Integer.toString(total).replaceAll("[0-9]", placeholder);

        return (patternt + Integer.toString(i)).substring(Integer.toString(i).length());

    }

    public String zeroPatternedString(int i, int total){

        return patternedString(i, total, "0");

    }

    public void webDriverWaitUntilPageURLContains(final WebDriver driver, final String PAGE_URL){

        Log4Test.info("     Applying explicit wait up to "+ Integer.toString(defaultWaitingTimeInSeconds) +" sec. until page URL contains [" + PAGE_URL + "].");

        ExpectedCondition<Boolean> expectedCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wDr) {

                return driver.getCurrentUrl().toLowerCase().contains(PAGE_URL);

            }
        };

        new WebDriverWait(driver,defaultWaitingTimeInSeconds).until(expectedCondition);

        Log4Test.info("     Applied wait is over.");

    }

    public void webDriverWaitUntilPageTitleContains(WebDriver driver, String PAGE_TITLE){

        Log4Test.info("     Applying explicit wait up to "+ Integer.toString(defaultWaitingTimeInSeconds) +" sec. until page title contains [" + PAGE_TITLE + "].");

        new WebDriverWait(driver,defaultWaitingTimeInSeconds).until(ExpectedConditions.titleContains(PAGE_TITLE));

        Log4Test.info("     Applied explicit wait is over.");

    }

    public void webDriverWaitUntilAllElementsAreVisible(WebDriver driver, By bySelector){

        Log4Test.info("     Applying explicit wait up to " + Integer.toString(defaultWaitingTimeInSeconds) + " sec. until all elements located by [" + bySelector.toString() + "] are visible.");

        new WebDriverWait(driver,defaultWaitingTimeInSeconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bySelector));

        Log4Test.info("     Applied explicit wait is over.");

    }

    public void webDriverWaitUntilAllElementsAreVisible(WebDriver driver, By bySelector, int waitingTimeInSeconds){

        Log4Test.info("     Applying explicit wait up to "+ Integer.toString(defaultWaitingTimeInSeconds) +" sec. until all elements located by [" + bySelector.toString() + "] are visible.");

        new WebDriverWait(driver,waitingTimeInSeconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bySelector));

        Log4Test.info("     Applied explicit wait is over.");

    }

    public void webDriverWaitUntilElementIsVisible(WebDriver driver, By bySelector){

        Log4Test.info("     Applying explicit wait up to "+ Integer.toString(defaultWaitingTimeInSeconds) +" sec. until element located by [" + bySelector.toString() + "] is visible.");

        new WebDriverWait(driver,defaultWaitingTimeInSeconds).until(ExpectedConditions.visibilityOfElementLocated(bySelector));

        Log4Test.info("     Applied explicit wait is over.");

    }

    public void webDriverWaitUntilElementIsVisible(WebDriver driver, By bySelector, int waitingTimeInSeconds){

        Log4Test.info("     Applying explicit wait up to "+ Integer.toString(waitingTimeInSeconds) +" sec. until element located by [" + bySelector.toString() + "] is visible.");

        new WebDriverWait(driver,waitingTimeInSeconds).until(ExpectedConditions.visibilityOfElementLocated(bySelector));

        Log4Test.info("     Applied explicit wait is over.");

    }

    public void logResult(boolean result){

        if (result) {

            Log4Test.info("Verification result is POSITIVE.");

        }else {

            Log4Test.info("Verification result is NEGATIVE.");
        }

    }

    public void clickWebElementViaJavascript(String webElementId){

        Log4Test.info("        Clicking the WebElement using JavaScript.");

        ((JavascriptExecutor)driver).executeScript("document.getElementById(\""+webElementId+"\").click();");

    }

    public void scrollToWebElement(WebElement element){

        Log4Test.info("        Scrolling window to the WebElement.");

        ((JavascriptExecutor)driver).executeScript("window.scrollTo(" + element.getLocation().getX() + "," + element.getLocation().getY() + ");");

    }

    public WebDriverWait webDriverWait(WebDriver driver){

        Log4Test.info("     WebDriverWait applied.");

        return new WebDriverWait(driver,defaultWaitingTimeInSeconds);

    }

}
