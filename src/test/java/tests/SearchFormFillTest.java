package tests;

import base.page.PageManager;
import base.test.BaseTest;
import pages.MainSearchPage;
import pages.SearchResultPage;
import utils.PassengerSet;

import java.time.LocalDate;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/3/2018.
 */
public class SearchFormFillTest extends BaseTest {

    private static final int RANDOM_INDEX_FROM = 2;
    private static final int RANDOM_INDEX_TO = 0;
    private static final int RANDOM_DAY_OF_MONTH = 12;
    private static final PassengerSet PASSENGER_SET = new PassengerSet(2,3,2);;

    @Override
    public void runTest() {
        step(1,"Open main page");
        MainSearchPage firstPage = PageManager.createPage(MainSearchPage.class,"Start Page");

        step(2,"Select departure airport");
        String valueFrom = firstPage.selectDepartureByIndex(RANDOM_INDEX_FROM);

        check("Verify if selected value is displayed");
        firstPage.assertDepartureSelected(valueFrom);

        step(3,"Select arrival airport");
        String valueTo = firstPage.selectArrivalByIndex(RANDOM_INDEX_TO);

        check("Verify if selected value is displayed");
        firstPage.assertArrivalSelected(valueTo);

        step(4,"Select date");
        LocalDate date = firstPage.selectDayNextMonth(RANDOM_DAY_OF_MONTH);

        check("Verify if date selected displayed");
        firstPage.assertDateSelected(date);

        step(5,"Select one-way flight");
        firstPage.uncheckReturn();

        check("Verify if return datepicker is disabled");
        firstPage.assertReturnDateDisabled();

        step(6,"Select passengers count");

        firstPage.setPassengersCount(PASSENGER_SET);

        check("Verify passengers count");
        firstPage.assertPassengerSet(PASSENGER_SET);

        step(7,"Submit form");
        firstPage.search();
        SearchResultPage secondPage = PageManager.createPage(SearchResultPage.class,"Search results");

        check("Verify availiable flights");
        secondPage.assertFlightAvailiable();

    }
}
