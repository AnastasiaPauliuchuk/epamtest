package elements.menu;

import base.element.AbstractBaseElement;
import elements.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/14/2018.
 */
public class TopMenuItem extends AbstractBaseElement {


    public static final String LINK_LOCATOR = "preceding-sibling::a[contains(@class,\"primary-navigation_link\")]";
    public static final String ADDITIONAL_MENU_LOCATOR = "descendant::div[contains(@class,\"togglepanel-container\")]";

    public TopMenuItem(WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public String getElementType() {
        return "Top Menu Item";
    }

    public WebElement openAdditionalMenu() {
        Link linkMenu =  new Link(wrappedElement.findElement(new By.ByXPath(LINK_LOCATOR)));
        linkMenu.click();
        By by = new By.ByXPath(ADDITIONAL_MENU_LOCATOR);
        if(isRelativeElementPresent(by))
            return wrappedElement.findElement(by);
        return null;

    }

    public By getAdditionalMenuLocator() {
        return new By.ByXPath(ADDITIONAL_MENU_LOCATOR);
    }
}
