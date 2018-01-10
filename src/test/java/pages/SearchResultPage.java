package pages;

import base.page.BasePage;
import elements.FlightsWeekSection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/7/2018.
 */
public class SearchResultPage extends BasePage {

    private final static String MARKER_LOCATOR = "//div[contains(@class,\"c-flight-results-panel\")]";
    private final static String AVAIL_DAY_LOCATOR = "//ol[contains(@class,\"days\")]//div[contains(@class,\"day-with-availability\")]";

    @FindBy(xpath = "//ol[contains(@class,\"days\")]")
    private WebElement listDays;

    @FindBy(xpath="//section[contains(@class,\"outbound\")]")
    private FlightsWeekSection outboundSection;

    @FindBy(xpath="//section[contains(@class,\"outbound\")]")
    private FlightsWeekSection inboundSection;

    public void assertFlightAvailiable() {

        //FindElements!!!
        /*boolean isPresent;
        try {
            findElement(new By.ByXPath(AVAIL_DAY_LOCATOR));
            isPresent=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            isPresent=false;
        }
        Assert.assertTrue(isPresent);
        info("Availiable flights found");*/
        outboundSection.assertDaysAvailiable();

    }

    public void selectNearestOutboundDay( ){
        outboundSection.selectDayByIndex(0);
    }
    public void selectNearestInboundDay( ){
        inboundSection.selectDayByIndex(0);
    }
    public SearchResultPage(String name) {
        super(name);
    }

    @Override
    public By getMarkerLocator() {
        return new By.ByXPath(MARKER_LOCATOR);
    }
}
