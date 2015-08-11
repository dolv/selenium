package ui_tests.final_task;


import core.TestBase;
import org.testng.annotations.Test;
import pages.rozetka.RozetkaAllNotebooksPage;
import pages.rozetka.RozetkaAppleNotebooksPage;
import pages.rozetka.RozetkaMainPage;
import pages.rozetka.RozetkaNotebooksTabletsAdnPCsPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class FinalTest extends TestBase{
    private final String PAGE_URL = "http://rozetka.com.ua/";
    private final String NOTEBOKS_TABLETS_AND_PC_PAGE_TITLE = "Компьютеры и ноутбуки";
    private final int NUMBER_OF_NOTEBOOK_VARIANTS = 6;
    private final List<String> MANUFACTURES_LIST = Arrays.asList(
                                                        "Acer",
                                                        "Apple",
                                                        "Asus",
                                                        "Dell",
                                                        "Fujitsu",
                                                        "HP (Hewlett Packard)",
                                                        "Lenovo",
                                                        "MSI",
                                                        "Toshiba"
                                                   );
    private final String MANUFACTURE_NAME="Apple";
    private final String PRODUCT="Apple MacBook Pro Retina 13\" (Z0QP000X6)";

    @Test
    public void executeFinalTest(){
        RozetkaMainPage rozetkaMainPage = new RozetkaMainPage(driver);
        rozetkaMainPage.open(PAGE_URL);
        assertTrue(rozetkaMainPage.isOpened(PAGE_URL));
        rozetkaMainPage.clickMenuItemNotebooksTabletsAndPCs();

        RozetkaNotebooksTabletsAdnPCsPage rozetkaNotebooksTabletsAdnPCsPage = new RozetkaNotebooksTabletsAdnPCsPage(driver);
        rozetkaNotebooksTabletsAdnPCsPage.waitForPageLoad();
        assertTrue(rozetkaNotebooksTabletsAdnPCsPage.doesTitleContain(NOTEBOKS_TABLETS_AND_PC_PAGE_TITLE));
        assertTrue(rozetkaNotebooksTabletsAdnPCsPage.doesNotebookBlockWithGoodsItemsNumberIs(NUMBER_OF_NOTEBOOK_VARIANTS));
        rozetkaNotebooksTabletsAdnPCsPage.clickAllNotebooksCategoryLink();

        RozetkaAllNotebooksPage rozetkaAllNotebooksPage = new RozetkaAllNotebooksPage(driver);
        rozetkaAllNotebooksPage.waitForPageLoad();
        assertTrue(rozetkaAllNotebooksPage.areAllManufacturesPresent(MANUFACTURES_LIST));
        rozetkaAllNotebooksPage.clickManufactureLinkIfItContains(MANUFACTURE_NAME);

        RozetkaAppleNotebooksPage rozetkaAppleNotebooksPage=new RozetkaAppleNotebooksPage(driver);
        rozetkaAppleNotebooksPage.waitForPageLoad();
        assertTrue(rozetkaAppleNotebooksPage.isLoadedPageIsAppleNotebooksPage());
        rozetkaAppleNotebooksPage.clickFromExpensiveToCheapInSortDropDown();
        assertTrue(rozetkaAppleNotebooksPage.verifyIfInBlockWithGoodsExists(PRODUCT));
        rozetkaAppleNotebooksPage.clickAddProductToComparison(PRODUCT);
    }
}
