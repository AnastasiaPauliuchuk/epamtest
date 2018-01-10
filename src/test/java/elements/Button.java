package elements;

import base.element.AbstractBaseElement;
import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/4/2018.
 */
public class Button extends AbstractBaseElement {

    private final static String ELEMENT_TYPE = "Button";

    public Button(WebElement wrappedElement) {
        super(wrappedElement);
    }


    @Override
    public String getElementType() {
        return ELEMENT_TYPE;
    }




}
