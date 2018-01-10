package elements;

import base.browser.Browser;
import base.element.BaseContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.PassengerSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Anastasia Pauliuchuk
 *         created:  1/7/2018.
 */
public class InputPassengersExtended extends BaseContainer {

    @FindBy(id = "booking-passengers-input")
    private TextBox passengerInput;


    @FindBy(xpath = "//div[@class=\"passengers\"]/following::div//button[contains(@class,\"close\")]")
    private Button btnSave;



    @FindBy(xpath = "//div[contains(@class,\"selectfield\") and contains(@class,\"adults\")]")
    private PassCountSpinner adultCount;

    @FindBy(xpath = "//div[contains(@class,\"selectfield\") and contains(@class,\"children\")]")
    private PassCountSpinner childCount;

    @FindBy(xpath = "//div[contains(@class,\"selectfield\") and contains(@class,\"babies\")]")
    private PassCountSpinner babyCount;


    private static final By CONTAINER_MARKER_LOCATOR =
            new By.ByXPath("//div[@class=\"passengers\"]/following::div//button[contains(@class,\"close\")]");

    private static final By CONTAINER_LOCATOR = new By.ByXPath(".//div[contains(@class,\"togglepanel-passengers\")]");
    private static final String VALUE_LOCATOR = "parent::div/div";
    private static final String COUNT_ADULT_PERSON_REGEXP = "(\\d+) Adult.*";
    private static final String INPUT_TYPE_LOCATOR_TEMPLATE = "//div[contains(@class,\"%s\") and contains(@class,\"selectfield\")]//input[@type=\"text\"]";


    @Override
    public String getElementType() {
        return "Input Passengers";
    }


    public void openContainer() {
        passengerInput.click();
        waitContainerVisibility();
    }

    private void waitContainerVisibility() {
        info("wait");
       /* try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(browser.getDriver())
                    .withTimeout(Long.parseLong(browser.getTimeoutForLoad()), SECONDS)
                    .pollingEvery(Long.parseLong(browser.getTimeoutForCondition()), SECONDS);
            wait.until(ExpectedConditions.visibilityOf(btnSave.getWrappedElement()));
        } catch (Exception e) {

        }*/
       isElementVisible(CONTAINER_MARKER_LOCATOR);
    }

    public PassengerSet setPassengersCount(PassengerSet passengerSet) {
        openContainer();
        adultCount.setField(passengerSet.getAdultCount());
        childCount.setField(passengerSet.getChildCount());
        babyCount.setField(passengerSet.getBabyCount());
        btnSave.click();
        return passengerSet;

    }

    public PassengerSet setPassengersCountBySpinner(PassengerSet passengerSet) {
        openContainer();
        /*adultCount.setFieldBySpinner(passengerSet.getAdultCount());
        childCount.setFieldBySpinner(passengerSet.getChildCount());*/
        babyCount.setFieldBySpinner(passengerSet.getBabyCount());
        btnSave.click();
        return passengerSet;
    }

    public void assertAdultPersonCount(int count) {
        WebElement el = findElement(new By.ByXPath(VALUE_LOCATOR));
        String value = el.getAttribute("innerHTML");
        Pattern p = Pattern.compile(COUNT_ADULT_PERSON_REGEXP);
        Matcher m = p.matcher(value);
        int i = 0;
        if (m.find()) {
            i = Integer.parseInt(m.group(1));
        }
        Assert.assertEquals(i, count);
        assertInfo(i, count);
    }

    public void assertPassengerSet(PassengerSet pSet) {
        WebElement el = findElement(new By.ByXPath(VALUE_LOCATOR));
        String value = el.getAttribute("innerHTML");
        //parse string
    }

    private void setField(int count, PassengerTypes passengerType) {
        WebElement el = Browser.getInstance().getDriver().findElement(new By.ByXPath(String.format(INPUT_TYPE_LOCATOR_TEMPLATE, passengerType.toString())));
        TextBox tbxCount = new TextBox(el);
        tbxCount.clear();
        tbxCount.type(String.valueOf(count));

    }


    public enum PassengerTypes {

        ADULTS("adults"),
        CHILDS("children"),
        BABIES("babies");

        public String value;

        PassengerTypes(final String values) {
            value = values;
        }

        public String toString() {
            return value;
        }
    }


}
