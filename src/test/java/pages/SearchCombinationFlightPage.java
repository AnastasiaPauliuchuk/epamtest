package pages;

import base.page.BasePage;
import elements.Button;
import elements.InputWithDatepicker;
import elements.SelectWithAutocomplete;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/13/2018.
 */
public class SearchCombinationFlightPage extends BasePage {


    private static final String DATE_FORMAT ="dd-mm-yyyy";
    @FindBy(id="openJawRouteSelection_DepartureStationOutbound-input")
    SelectWithAutocomplete departureOutbound;

    @FindBy(id="openJawRouteSelection_ArrivalStationOutbound-input")
    SelectWithAutocomplete arrivalOutbound;

    @FindBy(id="openJawRouteSelection_DepartureStationInbound-input")
    SelectWithAutocomplete departureInbound;

    @FindBy(id="openJawRouteSelection_ArrivalStationInbound-input")
    SelectWithAutocomplete arrivalInbound;

    @FindBy(xpath = "//div[contains(@class,\"outbound-date\")]")
    private InputWithDatepicker outboundDateInput;

    @FindBy(xpath = "//div[contains(@class,\"inbound-date\")]")
    private InputWithDatepicker inboundDateInput;

    @FindBy(xpath="//div[contains(@class,\"panel_section--button-search\")]//button[@type=\"submit\"]")
    private Button searchButton;



    private final static String MARKER_LOCATOR = "//section[contains(@class,\"panel--logo-search\")]";

    public SearchCombinationFlightPage(String name) {
        super(name);
    }


    @Override
    public By getMarkerLocator() {
        return new By.ByXPath(MARKER_LOCATOR);
    }

    public void selectOutboundDeparture(String outboundDeparture) {
        departureOutbound.searchAndSelect(outboundDeparture);
    }

    public void selectOutboundArrival(String outboundArrival) {
        arrivalOutbound.searchAndSelect(outboundArrival);
    }

    public void selectInboundDeparture(String inboundDeparture) {
        departureInbound.searchAndSelect(inboundDeparture);
    }

    public void selectInboundArrival(String inboundArrival) {
        arrivalInbound.searchAndSelect(inboundArrival);
    }

    public void setOutboundDate(LocalDate date) {
        outboundDateInput.setDate(date);
    }

    public void setInboundDate(LocalDate date) {
        inboundDateInput.setDate(date);
    }

    public void search() {
        searchButton.click();
    }

}
