package elements;

import base.element.AbstractBaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/16/2018.
 */
public class SelectOptionsWrapped extends AbstractBaseElement {


  //  private final static String SELECT_LOCATOR = "descendant::select";
    private final static String WRAPPER_LOCATOR = "ancestor::div[contains(@class,\"fake-selectfield\")]";
    private Select dropdown;

    public SelectOptionsWrapped(WebElement wrappedElement) {
        super(wrappedElement);
        dropdown = new Select(wrappedElement);
    }

    @Override
    public String getElementType() {
        return "Select";
    }

    private void focus() {
        Label wrapper = new Label(findElement(new By.ByXPath(WRAPPER_LOCATOR)));
        if(wrapper.isElementClickable())
            wrapper.click();
    }

    public void selectByVisibleText(String text) {
        focus();

        dropdown.selectByVisibleText(text);
        info("selected : "+text);

    }
    public void selectByValue(String value) {
        focus();
        dropdown.selectByValue(value);
        info ("selected : "+value);//todo get option text by value
    }

}
