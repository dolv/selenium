package ui_tests.final_task;


import core.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.rozetka.*;
import utils.Log4Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class FinalTest extends TestBase{

    private final String PAGE_URL = "http://rozetka.com.ua/";

    private final String NOTEBOOKS_TABLETS_AND_PC_PAGE_TITLE = "Компьютеры и ноутбуки";

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

    private final String EXPECTED_TEXT = "Сравниваем Ноутбуки";

    private final String[] PRODUCT = {
            "Apple MacBook Pro Retina 13\" (Z0QP000X6)",
            "Apple MacBook Pro Retina 15\" (MGXA2UA/A)"
    };

    @Test
    public void executeFinalTest() throws IOException {

        RozetkaMainPage rozetkaMainPage = new RozetkaMainPage(driver);

        rozetkaMainPage.open(PAGE_URL);

        assertTrue(rozetkaMainPage.isOpened(PAGE_URL));

        rozetkaMainPage.clickMenuItemNotebooksTabletsAndPCs();



        RozetkaNotebooksTabletsAdnPCsPage rozetkaNotebooksTabletsAdnPCsPage = new RozetkaNotebooksTabletsAdnPCsPage(driver);

        rozetkaNotebooksTabletsAdnPCsPage.waitForPageLoad();

        assertTrue(rozetkaNotebooksTabletsAdnPCsPage.doesTitleContain(NOTEBOOKS_TABLETS_AND_PC_PAGE_TITLE));

        assertTrue(rozetkaNotebooksTabletsAdnPCsPage.doesNotebookBlockWithGoodsItemsNumberIs(NUMBER_OF_NOTEBOOK_VARIANTS));

        rozetkaNotebooksTabletsAdnPCsPage.clickAllNotebooksCategoryLink();



        RozetkaAllNotebooksPage rozetkaAllNotebooksPage = new RozetkaAllNotebooksPage(driver);

        rozetkaAllNotebooksPage.waitForPageLoad();

        assertTrue(rozetkaAllNotebooksPage.areAllManufacturesPresent(MANUFACTURES_LIST));

        rozetkaAllNotebooksPage.clickManufactureLinkIfItContains(MANUFACTURE_NAME);



        RozetkaAppleNotebooksPage onRozetkaAppleNotebooksPage=new RozetkaAppleNotebooksPage(driver);

        onRozetkaAppleNotebooksPage.waitForPageLoad();

        assertTrue(onRozetkaAppleNotebooksPage.isLoadedPageIsAppleNotebooksPage());

        onRozetkaAppleNotebooksPage.clickFromExpensiveToCheapInSortDropDown();

        boolean productExists ;

        productExists = onRozetkaAppleNotebooksPage.inBlockWithGoodsExists(PRODUCT[0]);

        assertTrue(productExists);

        onRozetkaAppleNotebooksPage.clickAddProductToComparison();

        assertTrue(onRozetkaAppleNotebooksPage.doesComparisonListContainAddedProducts(new String[]{PRODUCT[0]}));

        productExists = onRozetkaAppleNotebooksPage.inBlockWithGoodsExists(PRODUCT[1]);

        assertTrue(productExists);

        onRozetkaAppleNotebooksPage.clickAddProductToComparison();

        boolean allArePresent;

        allArePresent = onRozetkaAppleNotebooksPage.doesComparisonListContainAddedProducts(PRODUCT);

        assertTrue(allArePresent);

        onRozetkaAppleNotebooksPage.clickCompareLink();


        RozetkaComparisonPage comparisonPage = new RozetkaComparisonPage(driver);

        comparisonPage.waitForPageLoad();

        assertTrue(comparisonPage.doesThePageContain(EXPECTED_TEXT));

        assertTrue(comparisonPage.doesThePageContainProcucts(PRODUCT));

    }

    @AfterTest
    public void TestCompletion(){

        Log4Test.info("Test Completed.");

    }

}
