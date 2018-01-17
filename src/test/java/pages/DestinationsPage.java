package pages;

import base.page.BasePage;
import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/16/2018.
 */
public class DestinationsPage extends BasePage {

       private final static String MARKER_LOCATOR = "div.destination-overview-map_map";

    @FindBy(xpath = "(//a[contains(@href,\"/book-a-flight/advanced-search/search/\")])[3]")
    private Button btnPerfectDestination;

    public DestinationsPage(String name) {
        super(name);
    }

    @Override
    public By getMarkerLocator() {
        return new By.ByCssSelector(MARKER_LOCATOR);
    }

    public void goPerfectDestination() {
        btnPerfectDestination.click();
    }
}
