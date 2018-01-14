package base.element;

import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/8/2018.
 */
public abstract class BaseContainer extends BaseElement {

    public final void init(final WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }

    public boolean isDisplayed() {
        return getWrappedElement().isDisplayed();
    }

    @Override
    public void init() {}

    @Override
    public String getElementType() {
        return "Container";
    }

    /*public abstract WebElement getMainElement() ;

    @Override
    public WebElement getWrappedElement() {
        return getMainElement();
    }*/
}
