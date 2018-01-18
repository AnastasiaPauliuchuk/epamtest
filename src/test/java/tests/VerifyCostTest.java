package tests;

import base.page.PageManager;
import base.test.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.FareSelectPage;
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
    @Parameters({"departure", "arrival", "passengers.adult", "passengers.children", "passengers.babies"})
    public void setUp(String departure, String arrival, int passAdult, int passChild, int passBaby) {
        this.departure = departure;
        this.arrival = arrival;
        this.passengerSet = new PassengerSet(passAdult, passChild, passBaby);
    }


    private double calculateExpectedCost(double pricePerPerson, double priceBaby) {
        return (passengerSet.getAdultCount() + passengerSet.getChildCount()) * pricePerPerson + 2 * passengerSet.getBabyCount() * priceBaby;
    }

    @Override
    public void runTest() {
        step(1, "Open main page");
        MainSearchPage firstPage = PageManager.createPage(MainSearchPage.class,"Start page");

        step("1.1", "Select departure airport using autocomplete");
        firstPage.selectDepartureByName(departure);

        step("1.2", "Select arrival airport using autocomplete");
        firstPage.selectArrivalByName(arrival);

        step("1.3", "Select passengers count using spinner");
        firstPage.setPassengersCountBySpinner(passengerSet);

        step("1.4", "Submit form");
        firstPage.search();
        SearchResultPage secondPage =  PageManager.createPage( SearchResultPage.class,"Search results");

        step("1.5","Select outbound flight");
        secondPage.selectNearestOutboundDay();

        step("1.6","Select inbound flight");
        secondPage.selectNearestInboundDay();

        step("1.7","Click 'Next'");
        secondPage.next();
        FareSelectPage optionsPage = PageManager.createPage(FareSelectPage.class,"Fare select");

        step("1.8","Select 'Plus' Fare");
        optionsPage.selectPlusOption();

        check("Verify total price");
        assertEquals(calculateExpectedCost(optionsPage.getPricePerPerson(),optionsPage.getPriceBaby()),optionsPage.getTotalPrice());


    }
}
