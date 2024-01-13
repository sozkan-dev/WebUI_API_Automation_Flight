package pageObjects.mainPage;

import com.google.common.base.CharMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pageObjects.BasePage;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage {

    private static @FindBy(xpath = "//*[@id=\"headlessui-combobox-button-:R1a9lla:\"]") WebElement firstDropdownButton;

    private static @FindBy(xpath = "//*[@id=\"headlessui-combobox-button-:R1ahlla:\"]") WebElement secondDropdownButton;

    private static @FindBy(xpath = "/html/body/main/div[1]/div[1]/div/ul/li") List<WebElement> firstList;

    private static @FindBy(xpath = "/html/body/main/div[1]/div[2]/div/ul/li") List<WebElement> secondList;

    private static @FindBy(xpath = "/html/body/main/div[1]/div[1]/div/input") WebElement firstInput;

    private static @FindBy(xpath = "/html/body/main/div[1]/div[2]/div/input") WebElement secondInput;


    private static @FindBy(xpath = "/html/body/main/div[2]/div/p") WebElement itemFoundText;

    private static @FindBy(xpath = "/html/body/main/div[2]/div/ul/li") List<WebElement> totalResultItems;


    public MainPage() {
        super();
    }

    public void goToMainPage() {
        navigateTo_URL(getURL());
    }

    public boolean isSameToAndFrom() throws InterruptedException {
        boolean flag = false;
        //Open the first dropdown menu
        waitForWebElementAndClick(firstDropdownButton);
        //First for loop is used for iteration of first dropdown. It selects each list item and iterate. Second for loop is used for second dropdown. It searches all items from second dropdown and check whether contains first dropdown item or not.
        for (int i = 1; i <= firstList.size(); i++) {
            By indexItem = By.xpath("/html/body/main/div[1]/div[1]/div/ul/li" + "[" + i + "]");
            WebElement elem = waitAndConvertToWebElement(indexItem);
            System.out.println("Element Text: " + elem.getText());
            String firstA = elem.getText();
            waitForWebElementAndClick(indexItem);

            waitForWebElementAndClick(secondDropdownButton);
            System.out.println("SecondList: " + secondList.size());

            for (int j = 1; j <= secondList.size(); j++) {
                WebElement elem2 = waitAndConvertToWebElement(By.xpath("/html/body/main/div[1]/div[2]/div/ul/li" + "[" + j + "]"));
                String secondA = elem2.getText();
                System.out.println("Second Element Text: " + elem2.getText());
                if (secondA.equals(firstA)) {
                    flag = true;
                }

            }

            waitForWebElementAndClick(firstDropdownButton);

            System.out.println(flag);

        }
        return flag;
    }

    public boolean isItemsCountMatch() {
        boolean flag = false;
        waitForWebElementAndClick(firstDropdownButton);
        waitForWebElementAndClick(firstList.get(0));
        waitForWebElementAndClick(secondDropdownButton);
        waitForWebElementAndClick(secondList.get(6));

        String text = itemFoundText.getText();
        String numberText = CharMatcher.inRange('0', '9').retainFrom(text);
        int count = Integer.valueOf(numberText);
        System.out.println(count);

        if (totalResultItems.size() == count) {
            flag = true;
        }


        return flag;


    }
}



