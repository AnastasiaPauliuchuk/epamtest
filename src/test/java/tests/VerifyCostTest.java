package tests;

import base.browser.Browser;
import base.test.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
        Browser.getInstance().getDriver().navigate().refresh();
        step(1, "Open main page");

        MainSearchPage firstPage = new MainSearchPage("Start page");

        firstPage.init((WebDriver) Browser.getInstance().getDriver());
        step(2, "Select departure airport");
        firstPage.selectDepartureByName(departure);
        step(3, "Select arrival airport");
        firstPage.selectArrivalByName(arrival);
        step(4, "Select passengers count");
        firstPage.setPassengersCountBySpinner(passengerSet);
        step(5, "Submit form");
        firstPage.search();

        SearchResultPage secondPage = new SearchResultPage("Search results");
        secondPage.init((WebDriver) Browser.getInstance().getDriver());
        secondPage.assertFlightAvailiable();
        secondPage.selectNearestOutboundDay();
        secondPage.selectNearestInboundDay();
        secondPage.next();

        FareSelectPage optionsPage = new FareSelectPage("Fare select");
        optionsPage.init((WebDriver) Browser.getInstance().getDriver());


        optionsPage.selectPlusOption();
      //  optionsPage.assertPrices();
        //todo
        Assert.assertEquals(calculateExpectedCost(optionsPage.getPricePerPerson(),optionsPage.getPriceBaby()),optionsPage.getTotalPrice());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
