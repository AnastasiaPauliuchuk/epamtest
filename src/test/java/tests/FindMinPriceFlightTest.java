package tests;

import base.page.PageManager;
import base.test.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.FindPerfectDestinationPage;
import pages.MainSearchPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/16/2018.
 */
public class FindMinPriceFlightTest extends BaseTest {

    private static String departure;
    private static String arrival;
    private static LocalDate flightDate;
    private static String expectedStation;
    private static String expectedCountry;
    private static double expectedPrice;

    @BeforeClass
    @Parameters({"departure", "arrival", "flightDate", "flightDateFormat", "expectedStation","expectedCountry", "expectedPrice"})
    public void setUp(String departure, String arrival, String flightDate,
                      String flightDateFormat, String expectedStation, String expectedCountry, String expectedPrice) {

        this.departure = departure;
        this.arrival = arrival;
        String format = String.format("dd/%s", flightDateFormat);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String date = String.format("01/%s", flightDate);
        this.flightDate = LocalDate.parse(date, formatter);
        this.expectedStation = expectedStation;
        this.expectedCountry = expectedCountry;
        this.expectedPrice = Double.parseDouble(expectedPrice);

    }

    @Override
    public void runTest() {
        step(1, "Open main page");
        MainSearchPage firstPage = PageManager.createPage(MainSearchPage.class,"Start Page");

        step(2, "Open 'Plan and Book' additional menu");
        firstPage.openPlanAndBookMenu();

        step(3, "Click 'Advanced search' link");
        firstPage.goAdvancedSearch();
        FindPerfectDestinationPage findPerfectDestinationPage = PageManager.createPage(FindPerfectDestinationPage.class,"Find Destinations");

        step(4, "Select departure country");
        findPerfectDestinationPage.selectDeparture(departure);

        step(5, "Select arrival country");
        findPerfectDestinationPage.selectArrival(arrival);

        step(6, "Click 'When will you be taking off'");
        findPerfectDestinationPage.openWhenSection();

        step(7, "Select 'Single flight'");
        findPerfectDestinationPage.setSingleTrip();

        step(8, "Select 'Specific month'");
        findPerfectDestinationPage.setDateTypeMonth();

        step(9, "Select the month from the list");
        findPerfectDestinationPage.setMonth(flightDate);

        step(10, "Click 'Day of the week dropdown' menu, choose 'Any day of the week'");
        findPerfectDestinationPage.setAnyDayOfWeek();

        step(11, "Submit form");
        findPerfectDestinationPage.search();

        check("Verify the destination");
        findPerfectDestinationPage.assertMinPriceDestination(expectedStation,expectedCountry);

        check("Verify the price");
        findPerfectDestinationPage.assertMinPrice(expectedPrice);


    }
}
