package tests;

import base.browser.Browser;
import base.test.BaseTest;
import org.openqa.selenium.WebDriver;
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
    private static final String DATE_FORMAT ="yyyy/MM/dd"; //todo format to parameter

    @BeforeClass
    @Parameters({"outboundDeparture", "outboundArrival", "outboundDate", "inboundDeparture", "inboundArrival","inboundDate"})
    public void setUp(String outboundDeparture, String outboundArrival,String outboundDate,
                      String inboundDeparture, String inboundArrival,String inboundDate) {
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
        Browser.getInstance().getDriver().navigate().refresh();
        step(1,"Open main page");
        MainSearchPage firstPage = new MainSearchPage("Start Page");
        firstPage.init((WebDriver) Browser.getInstance().getDriver());
        step(2,"Click 'Add multiple destinations' link");
        firstPage.goMultipleDestination();
        check("Combination flight search page is displayed");
        SearchCombinationFlightPage flightCombinationPage = new SearchCombinationFlightPage("Flight Combination");
        flightCombinationPage.init((WebDriver) Browser.getInstance().getDriver());
        flightCombinationPage.selectOutboundDeparture(outboundDeparture);
        flightCombinationPage.selectOutboundArrival(outboundArrival);
        flightCombinationPage.setOutboundDate(outboundDate);
        flightCombinationPage.selectInboundDeparture(inboundDeparture);
        flightCombinationPage.selectInboundArrival(inboundArrival);
        flightCombinationPage.setInboundDate(inboundDate);

        SearchResultPage secondPage = new SearchResultPage("Search results");
        secondPage.init((WebDriver) Browser.getInstance().getDriver());
        check("Verify availiable flights");
        secondPage.assertFlightAvailiable();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
