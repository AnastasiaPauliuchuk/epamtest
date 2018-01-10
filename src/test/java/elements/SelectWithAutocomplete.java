package elements;

import base.element.AbstractBaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/5/2018.
 */
public class SelectWithAutocomplete extends AbstractBaseElement {


    private static final String OPTION_BY_INDEX_SELECTOR_TEMPLATE_XPATH = "//ol[@class=\"results\"]/li[@data-relative-index=%d]";
    private static final String OPTION_BY_TEXT_SELECTOR_TEMPLATE_XPATH = "//ol[@class=\"results\"]//*[contains(text(),\"%s\")]/parent::li";

    private static final String OPTION_BY_INDEX_RELOADED_SELECTOR_TEMPLATE_XPATH =
            "//ol[@class=\"results\"]/li[@data-relative-index=%d and @data-parent-index=0]";
    private static final String OPTION_BY_TEXT_RELOADED_SELECTOR_TEMPLATE_XPATH =
            "//ol[@class=\"results\"]//*[contains(text(),\"%s\")]/parent::li[@data-parent-index=0]";

    private static final String MARKER_OPTIONS_RELOADED_XPATH = ".//ol[@class=\"results\"]//h6";

    private static final String SELECTED_VALUE_ELEMENT_LOCATOR =
            "parent::div /parent::div /parent::div [contains(@class,\"fake-selectfield\")]//span[@class=\"value\"]";

    public SelectWithAutocomplete(WebElement wrappedElement) {
        super(wrappedElement);
    }



    public String getElementType() {
        return "Select";
    }

    public String selectByIndex(int index) {

        wrappedElement.click();
        WebElement el = wrappedElement.findElement(getOptionLocator(index));
        String value = el.getText();
        el.click();
        info(String.format("select <%s>", value));
        return value;
    }

   /* public void selectByText(String value) {
        wrappedElement.click();
        WebElement el = wrappedElement.findElement(getOptionLocator(value));
        el.click();
        info(String.format("select <%s>", value));
    }*/

    public String searchAndSelect(String placeName) {
        wrappedElement.sendKeys(placeName);
        WebElement el = wrappedElement.findElement(getOptionLocator(placeName));
        String value = el.getAttribute("innerHTML");
        el.click();

        info(String.format("select <%s>", value));
        return value;
    }


    public void assertSelectedText(String selectedValue) {
        String actual = getElementText();
        assertInfo(selectedValue,actual);
        Assert.assertEquals(selectedValue,actual);

    }

    private By getValueElementLocator() {
        return new By.ByXPath(SELECTED_VALUE_ELEMENT_LOCATOR);
    }

    private boolean isReloaded() {
        return isRelativeElementPresent(new By.ByXPath(MARKER_OPTIONS_RELOADED_XPATH));
    }

    private By getOptionLocator(int index) {
        if (isReloaded()) {
            return new By.ByXPath(String.format(OPTION_BY_INDEX_RELOADED_SELECTOR_TEMPLATE_XPATH, index));
        } else {
            return new By.ByXPath(String.format(OPTION_BY_INDEX_SELECTOR_TEMPLATE_XPATH, index));
        }
    }

    private By getOptionLocator(String value) {
        if (isReloaded()) {
            return new By.ByXPath(String.format(OPTION_BY_TEXT_RELOADED_SELECTOR_TEMPLATE_XPATH, value));
        } else {
            return new By.ByXPath(String.format(OPTION_BY_TEXT_SELECTOR_TEMPLATE_XPATH, value));
        }
    }

    private String getElementText() {
        By loc = getValueElementLocator();
        WebElement element = findElement(loc);
        return element.getAttribute("innerHTML");
    }



}
