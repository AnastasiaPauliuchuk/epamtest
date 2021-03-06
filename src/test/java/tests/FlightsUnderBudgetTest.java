package tests;

import base.page.PageManager;
import base.test.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.DestinationsPage;
import pages.FindPerfectDestinationPage;
import pages.MainSearchPage;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/16/2018.
 */
public class FlightsUnderBudgetTest extends BaseTest {

    private static String departurePlace;
    private static double budget;

    @BeforeClass
    @Parameters({"departurePlace","budget"})
    public void setUp(String departurePlace,String budgetStr) {
        this.departurePlace = departurePlace;
        this.budget = Double.parseDouble(budgetStr);
    }

    @Override
    public void runTest() {
        step("1.1","Open main page");
        MainSearchPage firstPage = PageManager.createPage(MainSearchPage.class,"Start Page");

        step("1.2","Click 'View all of our destinations' link");
        firstPage.goAllDestinations();
        DestinationsPage destinationsPage = PageManager.createPage(DestinationsPage.class,"Destinations");

        step("1.3","Click 'Find the perfect destination' link");
        destinationsPage.goPerfectDestination();
        FindPerfectDestinationPage findPerfectDestinationPage = PageManager.createPage(FindPerfectDestinationPage.class,"Find Perfect Destination");

        step("1.4","Fill the 'From' field");
        findPerfectDestinationPage.selectDeparture(departurePlace);

        step("1.5","Fill the 'My budget' field");
        findPerfectDestinationPage.setBudget(budget);

        step("1.6","Click the 'Search' button");
        findPerfectDestinationPage.search();

        check("Verify destinations");
        findPerfectDestinationPage.assertFlightsExists();

        check("Verify prices are under budget");
        findPerfectDestinationPage.assertResultPrices(budget);
    }
}
