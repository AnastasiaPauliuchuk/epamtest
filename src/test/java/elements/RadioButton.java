package elements;

import base.element.AbstractBaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/16/2018.
 */
public class RadioButton extends AbstractBaseElement {

    private final static String LABEL_FOR_LOCATOR = "parent::span";

    public RadioButton(WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public String getElementType() {
        return "RadioButton";
    }

    private void setChecked(boolean state) {
        if(wrappedElement.isSelected()!=state)
        {
            WebElement label = wrappedElement.findElement(new By.ByXPath(LABEL_FOR_LOCATOR));
            label.click();

        }

    }
    public void check() {
        setChecked(true);
        info("checked");
    }

}
