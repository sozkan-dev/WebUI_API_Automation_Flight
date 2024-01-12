package test1;

import com.sun.tools.javac.Main;
import junit.framework.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageObjects.BasePage;
import pageObjects.mainPage.MainPage;

import java.io.IOException;

import static driver.DriverFactory.cleanDriver;

public class MainPageTest {

    public MainPageTest() throws IOException {
        super();
    }

    @BeforeAll
    public static void setUp() {
        BasePage.getDriver();
    }

    @AfterAll
    public static void tearDown() {
        cleanDriver();
    }

    @Test
    public void tc_1_FromContainsTo() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.goToMainPage();
        Assert.assertEquals(false, mainPage.isSameToAndFrom());


    }

    @Test
    public void tc_2_ItemCountMatch(){
        MainPage mainPage = new MainPage();
        mainPage.goToMainPage();
        Assert.assertEquals(true, mainPage.isItemsCountMatch());
    }

}
