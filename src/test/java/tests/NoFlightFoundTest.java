package tests;

import base.browser.Browser;
import base.test.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.MainSearchPage;
import pages.SearchResultPage;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/13/2018.
 */
public class NoFlightFoundTest extends BaseTest {

    private static String departure;
    private static String arrival;

    @BeforeClass
    @Parameters({"departure", "arrival"})
    public void setUp(String departure, String arrival) {
        this.departure = departure;
        this.arrival = arrival;
    }

    @Override
    public void runTest() {

        Browser.getInstance().getDriver().navigate().refresh();
        step(1, "Open main page");

        MainSearchPage firstPage = new MainSearchPage("Start page");

        firstPage.init((WebDriver) Browser.getInstance().getDriver());
        step(2, "Select departure airport");
        firstPage.selectDepartureByName(departure);
        step(3, "Select arrival airport");
        firstPage.selectArrivalByName(arrival);
        step(4, "Submit form");
        firstPage.search();
        step(5,"Search results page is opened");
        SearchResultPage secondPage = new SearchResultPage("Search results");
        secondPage.init((WebDriver) Browser.getInstance().getDriver());
        check("Error message is found");
        secondPage.assertFlightsNotFound();

    }


}

