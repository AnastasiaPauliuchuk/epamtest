package tests;

import base.browser.Browser;
import base.test.BaseTest;
import org.openqa.selenium.WebDriver;
import pages.MainSearchPage;
import pages.SearchResultPage;
import utils.PassengerSet;

import java.time.LocalDate;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/3/2018.
 */
public class SearchFormFillTest extends BaseTest {

    private static final int randomIndexFrom = 2;
    private static final int randomIndexTo = 0;
    private static final int randomDayOfMonth = 12;
    private static final int passCount = 2;

    @Override
    public void runTest() {
        Browser.getInstance().getDriver().navigate().refresh();
        step(1,"Open main page");
        MainSearchPage firstPage = new MainSearchPage("Start Page");
        firstPage.init((WebDriver) Browser.getInstance().getDriver());

        step(2,"Select departure airport");
        String valueFrom = firstPage.selectDepartureByIndex(randomIndexFrom);
        check("Verify if selected value is displayed");
        firstPage.assertDepartureSelected(valueFrom);
        step(3,"Select arrival airport");
        String valueTo = firstPage.selectArrivalByIndex(randomIndexTo);
        check("Verify if selected value is displayed");
        firstPage.assertArrivalSelected(valueTo);
        step(4,"Select date");
        LocalDate date = firstPage.selectDayNextMonth(randomDayOfMonth);
        check("Verify if date selected displayed:" + date.toString());
        firstPage.assertDateSelected(date);
        step(5,"Select one-way flight");
        firstPage.uncheckReturn();
        check("Verify if return datepicker is disabled");
        firstPage.assertReturnDateDisabled();
        step(6,"Select one person from passengers input widget");
        PassengerSet pSet = firstPage.setPassengersCount(new PassengerSet(passCount, 0, 0));

        check("Verify passengers count");
        firstPage.assertAdultPassengerCount(passCount);
        step(7,"Submit form");
        firstPage.search();

        SearchResultPage secondPage = new SearchResultPage("Search results");
        secondPage.init((WebDriver) Browser.getInstance().getDriver());
        check("Verify availiable flights");
        secondPage.assertFlightAvailiable();

    }
}
