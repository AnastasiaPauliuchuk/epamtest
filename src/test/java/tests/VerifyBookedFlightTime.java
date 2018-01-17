package tests;

import base.browser.Browser;
import base.test.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.LoginPage;
import pages.MainSearchPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/14/2018.
 */
public class VerifyBookedFlightTime extends BaseTest {

    private static String bookingID;
    private static String username;
    private static LocalDate flightDate;

    @BeforeClass
    @Parameters({"bookingID", "username", "flightDate","flightDateFormat"})
    public void setUp(String bookingID, String username,String flightDate, String flightDateFormat) {
        this.bookingID = bookingID;
        this.username = username;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(flightDateFormat);
        this.flightDate = LocalDate.parse(flightDate, formatter);

    }
    @Override
    public void runTest() {
        step(1,"Open main page");
        MainSearchPage firstPage = new MainSearchPage("Start Page");
        firstPage.init((WebDriver) Browser.getInstance().getDriver());

      // MainSearchPage firstPage = PageManager.createPage(MainSearchPage.class,"Start Page");
        step(2,"Open popup menu 'Manage your booking'");
        firstPage.openManageBookingMenu();
        check("Additional menu drops down");
        step(3,"Select 'View your booking'");
        firstPage.goViewBooking();
        LoginPage loginPage = new LoginPage("Login");
        loginPage.init((WebDriver) Browser.getInstance().getDriver());
        step(4, "Type the booking details");
        loginPage.setBookingID(bookingID);
        loginPage.setUsername(username);
        loginPage.setDate(flightDate);
        loginPage.submit();
        check("Verify login is successful");
        loginPage = new LoginPage("Login result");
        loginPage.init((WebDriver) Browser.getInstance().getDriver());
        loginPage.assertSuccessfulLogin();
        //todo -  not finished. Figure out the correct login data


    }
}
