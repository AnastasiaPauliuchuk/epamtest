package tests;

import base.browser.Browser;
import base.test.BaseTest;
import org.openqa.selenium.WebDriver;
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
        //Browser.getInstance().getDriver().navigate().refresh();
        step(1,"Open main page");
        MainSearchPage firstPage = new MainSearchPage("Start Page");
        firstPage.init((WebDriver) Browser.getInstance().getDriver());
        step(2,"Click 'View all of our destinations' link");
        firstPage.goAllDestinations();

        DestinationsPage destinationsPage = new DestinationsPage("Destinations");
        destinationsPage.init((WebDriver) Browser.getInstance().getDriver());

        step(3,"Click 'Find the perfect destination' link");
        destinationsPage.goPerfectDestination();

        FindPerfectDestinationPage findPerfectDestinationPage = new FindPerfectDestinationPage("Find Perfect Destination");
        findPerfectDestinationPage.init((WebDriver) Browser.getInstance().getDriver());

        step(4,"Fill the 'From' field");
        findPerfectDestinationPage.selectDeparture(departurePlace);
        step(5,"Fill the 'My budget' field");
        findPerfectDestinationPage.setBudget(budget);
        step(6,"Click the 'Search' button");
        findPerfectDestinationPage.search();
        check("Verify destinations");
        findPerfectDestinationPage.assertFlightsExists();
        check("Verify prices are under budget");
        findPerfectDestinationPage.assertResultPrices(budget);



    }
}
