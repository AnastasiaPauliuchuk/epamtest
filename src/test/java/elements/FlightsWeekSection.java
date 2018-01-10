package elements;

import base.element.BaseContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/9/2018.
 */
public class FlightsWeekSection extends BaseContainer {


    private Button btnSelect;
    private Label lblDaySelected;
    private List<Label> lblListAvailiable;
    List<WebElement> listDaysAvailiable;
    private static final String BUTTON_SELECT_LOCATOR = "descendant::/button[contains(@class,\"flight-result-button\")";
    private static final String DAYS_AVAILIABLE_LIST_LOCATORS = "descendant::div[contains(@class,\"day-with-availability\")]";
    private static final String DAY_SELECTED_LOCATORS = "descendant::div[contains(@class,\"day-with-availability\") and contains(@class,\"is-selected\")]";

    public void checkout() {
        if (isDaySelected()) {
            btnSelect = new Button(findElement(new By.ByXPath(BUTTON_SELECT_LOCATOR)));
            btnSelect.click();
        }
    }

    public void init() {
        List<WebElement> listDaysAvailiable = findElements(new By.ByXPath(DAYS_AVAILIABLE_LIST_LOCATORS));
    }


    public int selectDayByIndex(int index) {

        int size = listDaysAvailiable.size();
        if ((size > 0) && (index < size)) {
            listDaysAvailiable.get(index).click();
            return index;
        } else return -1;

    }

    public boolean isDaySelected() {
        List<WebElement> listElements = findElements(new By.ByXPath(DAY_SELECTED_LOCATORS));
        return (listElements.size() > 0);
    }


    public void assertDaysAvailiable() {
        Assert.assertTrue(listDaysAvailiable.size()>0);
        assertInfo("Availiable flights exist","true");
    }
}