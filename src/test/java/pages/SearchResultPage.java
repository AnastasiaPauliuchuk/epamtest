package pages;

import base.browser.Browser;
import base.page.BasePage;
import elements.Button;
import elements.FlightsWeekSection;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/7/2018.
 */
public class SearchResultPage extends BasePage {

    private final static String MARKER_LOCATOR = "//section[contains(@class,\"panel--logo-search\")]";
    private final static String AVAIL_DAY_LOCATOR = "//ol[contains(@class,\"days\")]//div[contains(@class,\"day-with-availability\")]";
    private final static String NEXT_LOCATOR = "next_button";
    private final static String ERROR_LOCATOR ="//div[contains(@class,\"notification-error\") and @data-validation-name=\"routesFilter\"]";
    @FindBy(xpath = "//section[contains(@class,\"outbound\")]//div[contains(@class,\"c-flight-results-panel\")]")
    private FlightsWeekSection outboundSection;

    @FindBy(xpath = "//section[contains(@class,\"inbound\")]//div[contains(@class,\"c-flight-results-panel\")]")
    private FlightsWeekSection inboundSection;


    public void assertFlightsNotFound() {
        boolean error = isElementPresent(new By.ByXPath(ERROR_LOCATOR));
        Assert.assertTrue(error);
        assertInfo("true", "Error message displayed");
    }

    public void assertFlightAvailiable() {
        //info("assert flight availiable");
        //if(outboundSection.isDisplayed())
        outboundSection.assertDaysAvailiable();

    }

    public void selectNearestOutboundDay() {
        // outboundSection.selectDayByIndex(0);
        outboundSection.checkout();
    }

    public void selectNearestInboundDay() {
        //todo
        JavascriptExecutor js = ((JavascriptExecutor) Browser.getInstance().getDriver());
        js.executeScript("window.scrollTo(0, document.body.scrollHeight-400)");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inboundSection.checkout();

    }

    public SearchResultPage(String name) {
        super(name);
    }

    @Override
    public By getMarkerLocator() {
        return new By.ByXPath(MARKER_LOCATOR);
    }

    public void next() {

        Button btnNext = new Button(findElement(new By.ByName(NEXT_LOCATOR)));
        btnNext.click();
    }
}
