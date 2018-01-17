package elements.menu;

import base.element.AbstractBaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/14/2018.
 */
public  class TopAdditionalMenu extends AbstractBaseElement {


    public TopAdditionalMenu(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public TopAdditionalMenu(By by) {
        super(by);
    }

    @Override
    public String getElementType() {
        return "Top Additional Menu";
    }
}
