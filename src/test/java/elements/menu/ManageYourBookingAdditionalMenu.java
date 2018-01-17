package elements.menu;

import elements.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/14/2018.
 */
public class ManageYourBookingAdditionalMenu extends TopAdditionalMenu{

    private final static String BOOKING_OVERVIEW_LOCATOR =
            "//a[contains(@href,\"/en-EU/my-transavia/booking/booking-overview/\")and contains(@class,\"sub-navigation_link\") and contains(@class,\"h5\")]";

    public ManageYourBookingAdditionalMenu(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public ManageYourBookingAdditionalMenu(By by) {
        super(by);
    }

    private void selectItem(By by) {
        Link linkItem = new Link(findElement(by));
        linkItem.click();
    }
    public void goViewBooking() {
       selectItem(new By.ByXPath(BOOKING_OVERVIEW_LOCATOR));
    }
}
