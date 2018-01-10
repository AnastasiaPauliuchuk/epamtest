package tests;

import base.browser.Browser;
import base.test.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.MainSearchPage;
import pages.SearchResultPage;
import utils.PassengerSet;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/5/2018.
 */
public class VerifyCostTest extends BaseTest {

        private static String departure;
        private static String arrival;
        private static PassengerSet passengerSet;

    @BeforeClass
        @Parameters({"departure", "arrival","passengers.adult","passengers.children","passengers.babies"})
        public void setUp(String departure,String arrival,int passAdult,int passChild, int passBaby) {
            this.departure = departure;
            this.arrival = arrival;
            this.passengerSet = new PassengerSet(passAdult,passChild,passBaby);
         }


        @Override

        public void runTest() {
            step(1, "Open main page");

           MainSearchPage firstPage = new MainSearchPage("Start page");
            firstPage.init((WebDriver) Browser.getInstance().getDriver());
           step(2, "Select departure airport");
            firstPage.selectDepartureByName(departure);
            step(3, "Select arrival airport");
            firstPage.selectArrivalByName(arrival);
            step(4, "Select passengers count");
            firstPage.setPassengersCountBySpinner(passengerSet);
            firstPage.search();
            SearchResultPage secondPage = new SearchResultPage("Search results");
            secondPage.init((WebDriver) Browser.getInstance().getDriver());
            secondPage.assertFlightAvailiable();
            secondPage.selectNearestOutboundDay();

        }
}
