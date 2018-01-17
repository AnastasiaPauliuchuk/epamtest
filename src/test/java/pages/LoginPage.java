package pages;

import base.page.BasePage;
import elements.Button;
import elements.InputWithDatepicker;
import elements.Label;
import elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.time.LocalDate;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/17/2018.
 */
public class LoginPage extends BasePage {


    public static final String MARKER_LOCATOR = "section.personal-account-login";

    @FindBy(id="retrieveBookingByLastname_RecordLocator")
    private TextBox tbxBookingID;

    @FindBy(id="retrieveBookingByLastname_LastName")
    private TextBox tbxLastName;

    @FindBy(xpath="//input[@id=\"retrieveBookingByLastname_FlightDate-datepicker\"]/parent::div")
    private InputWithDatepicker inputWithDatepicker;

    @FindBy(xpath = "//div[contains(@class,\"panel-bbl\")]//button[@type=\"submit\"]")
    private Button btnSearch;

    @FindBy(css = "li.notification-message")
    private Label lblError;

    public LoginPage(String name) {
        super(name);
    }

    @Override
    public By getMarkerLocator() {
        return new By.ByCssSelector(MARKER_LOCATOR);
    }

    public void setBookingID(String bookingId) {
        tbxBookingID.type(bookingId);
    }

    public void setUsername(String username) {
        tbxLastName.type(username);
    }

    public void setDate(LocalDate date) {
        inputWithDatepicker.setDateCalendar(date);
    }

    public void submit() {
        btnSearch.click();

    }

    public void assertSuccessfulLogin() {
        checkInfo("Error is displayed","false" );
        Assert.assertTrue(!lblError.isElementVisible());

    }
}
