package elements;

import base.element.AbstractBaseElement;
import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/13/2018.
 */
public class Link extends AbstractBaseElement {


    public Link(WebElement wrappedElement) {
        super(wrappedElement);
    }



    @Override
    public String getElementType() {
        return "Link";
    }
}
