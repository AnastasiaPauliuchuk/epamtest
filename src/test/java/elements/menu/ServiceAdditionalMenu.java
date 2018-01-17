package elements.menu;

import elements.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/14/2018.
 */
public class ServiceAdditionalMenu extends TopAdditionalMenu {

    private final static String HAND_LUGGAGE_LOCATOR = "descendant::a[contains(@href,\"/service/hand-luggage/\")]";

    public ServiceAdditionalMenu(By by) {
        super(by);
    }

    public ServiceAdditionalMenu(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void goHandLuggage() {
        Link linkHandLuggage = new Link(wrappedElement.findElement(new By.ByXPath(HAND_LUGGAGE_LOCATOR)));
        linkHandLuggage.click();
    }


}
