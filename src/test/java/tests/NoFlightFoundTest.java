package tests;

import base.page.PageManager;
import base.test.BaseTest;
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

        step("1.1", "Open main page");
        MainSearchPage firstPage = PageManager.createPage(MainSearchPage.class,"Start page");

        step("1.2", "Select departure airport");
        firstPage.selectDepartureByName(departure);

        step("1.3", "Select arrival airport");
        firstPage.selectArrivalByName(arrival);

        step("1.4", "Submit form");
        firstPage.search();
        SearchResultPage searchResultPage = PageManager.createPage(SearchResultPage.class,"Search results");

        check("Error message is found");
        searchResultPage.assertFlightsNotFound();

    }


}

