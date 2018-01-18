package tests;

import base.page.PageManager;
import base.test.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.MainSearchPage;
import pages.SearchCombinationFlightPage;
import pages.SearchResultPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/13/2018.
 */
public class VerifyCombinationFlightCostTest extends BaseTest {

    private static String outboundDeparture;
    private static String outboundArrival;
    private static LocalDate outboundDate;
    private static String inboundDeparture;
    private static String inboundArrival;
    private static LocalDate inboundDate;
    private static final String DATE_FORMAT = "yyyy/MM/dd"; //todo format to parameter

    @BeforeClass
    @Parameters({"outboundDeparture", "outboundArrival", "outboundDate", "inboundDeparture", "inboundArrival", "inboundDate"})
    public void setUp(String outboundDeparture, String outboundArrival, String outboundDate,
                      String inboundDeparture, String inboundArrival, String inboundDate) {
        this.outboundDeparture = outboundDeparture;
        this.inboundDeparture = inboundDeparture;
        this.outboundArrival = outboundArrival;
        this.inboundArrival = inboundArrival;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        this.outboundDate = LocalDate.parse(outboundDate, formatter);
        this.inboundDate = LocalDate.parse(inboundDate, formatter);
    }

    @Override
    public void runTest() {
        step(1, "Open main page");
        MainSearchPage firstPage = PageManager.createPage(MainSearchPage.class, "Start Page");

        step(2, "Click 'Add multiple destinations' link");
        firstPage.goMultipleDestination();

        check("Combination flight search page is displayed");
        SearchCombinationFlightPage flightCombinationPage = PageManager.createPage(SearchCombinationFlightPage.class, "Flight Combination");

        step("3.1", "Set 1st departure");
        flightCombinationPage.selectOutboundDeparture(outboundDeparture);

        step("3.2", "Set 1st arrival");
        flightCombinationPage.selectOutboundArrival(outboundArrival);

        step("3.3", "Set 1st date");
        flightCombinationPage.setOutboundDate(outboundDate);

        step("4.1", "Set 2nd departure");
        flightCombinationPage.selectInboundDeparture(inboundDeparture);

        step("4.2", "Set 2nd arrival");
        flightCombinationPage.selectInboundArrival(inboundArrival);

        step("4.3", "Set 2nd date");
        flightCombinationPage.setInboundDate(inboundDate);

        step(5, "Submit form");
        flightCombinationPage.search();
        SearchResultPage searchResultPage = PageManager.createPage(SearchResultPage.class, "Search results");

        check("Verify flights are availiable");
        searchResultPage.assertFlightAvailiable();

        step(6, "Select outbound flight");
        searchResultPage.selectNearestOutboundDay();

        step(7, "Select inbound flight");
        searchResultPage.selectNearestInboundDay();

        check("Verify total price");
        searchResultPage.assertPrice();


    }
}
