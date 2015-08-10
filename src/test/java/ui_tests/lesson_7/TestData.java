package ui_tests.lesson_7;


import core.BrowserTypes;
import utils.PropertyLoader;

public class TestData {
    //public static final BrowserTypes BROWSER_NAME = BrowserTypes.FIRE_FOX;
    public static final BrowserTypes BROWSER_NAME = BrowserTypes.valueOf(PropertyLoader.loadProperty("browser.name"));
}
