package elements;

import base.element.AbstractBaseElement;
import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/7/2018.
 */
public class Label extends AbstractBaseElement {

    public Label(WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public String getElementType() {
        return "Label";
    }


    public String getText() {
        return getWrappedElement().getText();
    }
}
