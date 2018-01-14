package elements;

import base.element.AbstractBaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/9/2018.
 */
public class FlightsWeekSection extends AbstractBaseElement {


    private Button btnSelect;
    private Label lblDaySelected;
    private List<Label> lblListAvailiable;
    private List<WebElement> listDaysAvailiable;
    private static final String BUTTON_SELECT_LOCATOR = "descendant::button[contains(@class,\"flight-result-button\")]";
    private static final String DAYS_AVAILIABLE_LIST_LOCATORS =
            "descendant::div[contains(@class,\"day-with-availability\")]//span[@class=\"price\"]";
    private static final String DAY_SELECTED_LOCATORS = "descendant::div[contains(@class,\"day-with-availability\") and contains(@class,\"is-selected\")]";
    private static final String FLIGHT_SELECTED_DIV_LOCATOR = "//div[contains(@class,\"flight-result\") and contains(@class,\"selected\")]";

    public FlightsWeekSection(WebElement wrappedElement) {
        super(wrappedElement);
    }

   /* @Override
    public void init(){
        super.init();
        listDaysAvailiable = findElements(new By.ByXPath(DAYS_AVAILIABLE_LIST_LOCATORS));

    }
*/

    public void checkout() {
        if (!isDaySelected()) {
            selectDayByIndex(0);
        }
        //WebElement el = findElement(new By.ByXPath(BUTTON_SELECT_LOCATOR));
        By by = new By.ByXPath(BUTTON_SELECT_LOCATOR);
        btnSelect = new Button(wrappedElement.findElement(by));
        btnSelect.click();
        waitUntilSelected();
    }


    private void waitUntilSelected() {
       WebElement el = findElement(new By.ByXPath(FLIGHT_SELECTED_DIV_LOCATOR) );
        //todo
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public int selectDayByIndex(int index) {

        listDaysAvailiable = findElements(new By.ByXPath(DAYS_AVAILIABLE_LIST_LOCATORS));
        int size = listDaysAvailiable.size();
        if ((size > 0) && (index < size)) {
            listDaysAvailiable.get(index).click();
            info("select by index:");
            return index;
        } else return -1;


    }

    public boolean isDaySelected() {
        List<WebElement> listElements = findElements(new By.ByXPath(DAY_SELECTED_LOCATORS));
        return (listElements.size() > 0);
    }


    public void assertDaysAvailiable() {
        listDaysAvailiable = findElements(new By.ByXPath(DAYS_AVAILIABLE_LIST_LOCATORS));
        Assert.assertTrue(listDaysAvailiable.size() > 0);
        assertInfo("Availiable flights exist", "true");
    }

    @Override
    public String getElementType() {
        return "FlightsWeekSection";
    }

    /*@Override
    public WebElement getWrappedElement() {
        return null;
    }*/
}