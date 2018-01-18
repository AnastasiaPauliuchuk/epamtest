package pages;

import base.page.BasePage;
import elements.Button;
import elements.FlightsWeekSection;
import elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    @FindBy(xpath = "//section[contains(@class,\"outbound\")]//div[contains(@class,\"is-selected\")]//span[@class=\"price\"]")
    private Label outboundPrice;

    @FindBy(xpath = "//section[contains(@class,\"inbound\")]//div[contains(@class,\"is-selected\")]//span[@class=\"price\"]")
    private Label inboundPrice;

    private static final String TOTAL_PRICE_LOCATOR = "//section[@class=\"price-section\"]//div[@class=\"back\"]";

    @FindBy(xpath = "//section[@class=\"price-section\"]//div[@class=\"back\"]")
    private Label totalPrice;

    private final static String PRICE_REGEXP = "\\W(\\d+)";
    private final static String TOTAL_PRICE_REGEXP = "\\W(\\d+\\.)(.*)(\\d{2})";

    public void assertFlightsNotFound() {
        boolean error = isElementPresent(new By.ByXPath(ERROR_LOCATOR));
        Assert.assertTrue(error);
        checkInfo("Error message displayed","true" );
    }

    public void assertFlightAvailiable() {
        outboundSection.assertDaysAvailiable();

    }

    public void assertPrice() {

        double priceOut = 0.00;
        double priceIn = 0.00;
        double priceTotal = 0.00;
        Pattern p = Pattern.compile(PRICE_REGEXP);
        Matcher m = p.matcher(outboundPrice.getText());
        if(m.find()) {
            priceOut = Double.parseDouble(m.group(1));
        }
        m = p.matcher(inboundPrice.getText());
        if(m.find()) {
            priceIn = Double.parseDouble(m.group(1));
        }
        if( isElementVisible(new By.ByXPath(TOTAL_PRICE_LOCATOR))) {
            p = Pattern.compile(TOTAL_PRICE_REGEXP);
            m = p.matcher(totalPrice.getText());
            if (m.find()) {
                priceTotal = Double.parseDouble(m.group(1) + m.group(3));
            }
        }
        assertEquals(priceIn+priceOut,priceTotal);
    }

    public void selectNearestOutboundDay() {
        outboundSection.checkout();
    }

    public void selectNearestInboundDay() {
        this.scrollToElement(inboundSection.getWrappedElement());
        waitReload(2000);
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
