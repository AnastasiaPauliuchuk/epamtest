package pages;

import base.page.BasePage;
import elements.*;
import elements.menu.TopMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import utils.PassengerSet;

import java.time.LocalDate;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/4/2018.
 */
public class MainSearchPage extends BasePage {

    private final static String MARKER_LOCATOR = "//section[@data-package=\"search\"]";

    @FindBy(id = "routeSelection_DepartureStation-input")
    private SelectWithAutocomplete selectFrom;

    @FindBy(id = "routeSelection_ArrivalStation-input")
    private SelectWithAutocomplete selectTo;

    @FindBy(xpath = "//input[@id=\"dateSelection_IsReturnFlight\" and @value=\"true\"]")
    private CheckBox checkReturn;

    //todo locator=div
    @FindBy(xpath = "//input[@id=\"dateSelection_IsReturnFlight-datepicker\"]/parent::div")
    private InputWithDatepicker returnDateInput;

    //todo locator=div
    @FindBy(xpath = "//input[@id=\"dateSelection_OutboundDate-datepicker\"]/parent::div")
    private InputWithDatepicker flightDateInput;

    @FindBy(id = "booking-passengers-input")
    private InputPassengersExtended passengersInput;

    @FindBy(xpath = "//section[@data-package=\"search\"]//button[@type=\"submit\"]")
    private Button searchButton;

    @FindBy(xpath = "//a[@data-module=\"ui/CombinationFlightHelper\"]")
    private Link multipleDestinationsLink;

    @FindBy(xpath = "//div[@class=\"header_bar\"]//ul[contains(@class,\"primary-navigation_list\")]")
    private TopMenu topMenu;


    @FindBy(xpath = "(//a[contains(@href,\"/destinations/\" )])[2]")
    private Link lnkAllDestinations;

    public MainSearchPage(String name) {
        super(name);
    }


    @Override
    public By getMarkerLocator() {
        return new By.ByXPath(MARKER_LOCATOR);
    }

    public void assertDepartureSelected(String value) {
        selectFrom.assertSelectedText(value);
    }

    public void assertArrivalSelected(String value) {
        selectTo.assertSelectedText(value);
    }

    public String selectDepartureByIndex(int index) {
        return selectFrom.selectByIndex(index);
    }

    public String selectArrivalByIndex(int index) {

        return selectTo.selectByIndex(index);
    }

    public String selectDepartureByName(String placeName) {
        return selectFrom.searchAndSelect(placeName);
    }

    public String selectArrivalByName(String placeName) {

        return selectTo.searchAndSelect(placeName);
    }


    public void checkReturn() {
        checkReturn.setChecked(true);
    }

    public void uncheckReturn() {
        checkReturn.setChecked(false);
    }

    public void assertReturnDateDisabled() {
        returnDateInput.assertIsDisabled();
    }

    public void selectDay(int day) {
        flightDateInput.selectDayFromCalendar(day);
    }

    public LocalDate selectDayNextMonth(int day) {
        return flightDateInput.selectDayNextMonth(day);
    }

    public void assertDateSelected(LocalDate date) {
        flightDateInput.assertDateSelected(date);
    }

    public PassengerSet setPassengersCount(PassengerSet passengerSet) {
        return passengersInput.setPassengersCount(passengerSet);
    }

    public PassengerSet setPassengersCountBySpinner(PassengerSet passengerSet) {
        return passengersInput.setPassengersCountBySpinner(passengerSet);
    }

    public void assertPassengerSet(PassengerSet pSet) {
        passengersInput.assertPassengerSet(pSet);
    }


    public void search() {
        searchButton.click();
    }


    public void goMultipleDestination() {
        multipleDestinationsLink.click();
    }

    public void openManageBookingMenu() {
        topMenu.openMenuManageYourBooking();
    }

    public void goViewBooking() {
        topMenu.goViewBooking();
    }

    public void openServiceMenu() {
        topMenu.openServiceMenu();
    }

    public void goHandLuggage() {
        topMenu.goHandLuggage();
    }

    public void goAllDestinations() {
        lnkAllDestinations.click();
    }

    public void openPlanAndBookMenu() {
        topMenu.openPlanAndBookMenu();
    }

    public void goAdvancedSearch() {
        topMenu.goAdvancedSearch();
    }


}
