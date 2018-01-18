package tests;

import base.page.PageManager;
import base.test.BaseTest;
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
        MainSearchPage firstPage = PageManager.createPage(MainSearchPage.class,"Start Page");

        step(2,"Open popup menu 'Manage your booking'");
        firstPage.openManageBookingMenu();

        check("Additional menu drops down");
        step(3,"Select 'View your booking'");
        firstPage.goViewBooking();
        LoginPage loginPage = PageManager.createPage(LoginPage.class,"Login");

        step(4, "Type the booking details");
        loginPage.setBookingID(bookingID);
        loginPage.setUsername(username);
        loginPage.setDate(flightDate);
        loginPage.submit();

        check("Verify login is successful");
        loginPage = PageManager.createPage(LoginPage.class,"Login result");
        loginPage.assertSuccessfulLogin();
        // not finished. Figure out the correct login data


    }
}
