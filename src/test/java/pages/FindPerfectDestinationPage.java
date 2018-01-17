package pages;

import base.page.BasePage;
import elements.*;
import elements.menu.RadioButton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/16/2018.
 */
public class FindPerfectDestinationPage extends BasePage {


    @FindBy(id="countryStationSelection_Origin-input")
    private SelectWithAutocomplete departureSelect;

    @FindBy(id="countryStationSelection_Destination-input")
    private SelectWithAutocomplete arrivalSelect;

    @FindBy(xpath="//div[@data-budget-euro]/parent::div/preceding::div[@class=\"toggle-button-level-1\"]")
    private Link lnkToggleBudgetSection;


    @FindBy(id="budgetSelection_EurosBudget")
    private TextBox tbxBudgetEuro;

    @FindBy(xpath="//select[@id=\"data-flight-type\"]/ancestor::div[@class=\"toggle-content-level-1\"]/parent::div/child::div//div[@class=\"toggle-button-level-1\"]")
    private Link lnkToggleWhenSection;

    @FindBy(id ="data-flight-type")
    private SelectOptionsWrapped selectFlightType;
    private static final String SINGLE_TRIP_OPTION_VALUE = "Single";

    @FindBy(id = "data-type-month")
    private RadioButton btnSpecificMonth;

    @FindBy(id = "timeFrameSelection_SingleFlight_SpecificMonth")
    private SelectOptionsWrapped selectMonth;

    @FindBy(id="timeFrameSelection_SingleFlight_DepartureDayOfTheWeek")
    private SelectOptionsWrapped selectDayOfWeek;
    private static final String ANY_DAY_OPTION_VALUE = "";

    @FindBy(xpath="//div[@data-package=\"alternative-search\"]//button[@type=\"submit\"]")
    private Button btnSearch;

    @FindBy(xpath = "//div[contains(@class,\"destination_button\")]//h2")
    private Label lblMinPriceCity;

    @FindBy(xpath = "//div[contains(@class,\"destination_button\")]//h3")
    private Label lblMinPriceCountry;

    @FindBy(xpath = "//div[contains(@class,\"destination_button\")]//div[contains(@class,\"sticker--price\")]//span[@class=\"integer\"]")
    private Label lblMinPrice;


   private final static  String RESULTS_SELECTOR = "//div[contains(@class,\"destination_container\")]";
   private final static String  PRICE_RESULT_SELECTOR ="//div[contains(@class,\"sticker--price\")]//span[@class=\"integer\"]";



    private final static String MARKER_LOCATOR = "div.inspirational-header";

    private final static String DATE_FORMAT = "yyyy-MM-dd";
    public FindPerfectDestinationPage(String name) {
        super(name);
    }

    @Override
    public By getMarkerLocator() {
        return new By.ByCssSelector(MARKER_LOCATOR);
    }

    public void selectDeparture(String departurePlace) {
        departureSelect.searchAndSelect(departurePlace);
    }
    public void selectArrival(String arrivalPlace) {
        arrivalSelect.searchAndSelect(arrivalPlace);
    }

    public void setBudget(double budget) {
        lnkToggleBudgetSection.click();
        String budgetStr = Integer.toString(new Double(budget).intValue());
        tbxBudgetEuro.type(budgetStr);
    }

    public void search() {
        btnSearch.click();
    }

    public void assertFlightsExists() {
        List <WebElement> lstResultDestinations = findElements(new By.ByXPath(RESULTS_SELECTOR));
        int size = lstResultDestinations.size();

        Assert.assertTrue(size>0);
        checkInfo( "Flights exist","true");
    }

    public void assertResultPrices(double budget) {
        List <WebElement> lstResultPrices = findElements(new By.ByXPath(PRICE_RESULT_SELECTOR));
        for (WebElement priceElement: lstResultPrices) {
            double price = Double.parseDouble(priceElement.getText());
            Assert.assertTrue(price < budget);
            System.out.println(price);
            checkInfo(String.format("%1$f < %2$f", price, budget),"true");
        }

    }

    public void openWhenSection() {
        lnkToggleWhenSection.click();

    }
    public void setSingleTrip() {
      //  if(selectFlightType.isElementVisible())
             selectFlightType.selectByValue(SINGLE_TRIP_OPTION_VALUE);
    }

    public void setMonth(LocalDate flightDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String dateStr = flightDate.format(formatter);
        selectMonth.selectByValue(dateStr);
    }

    public void setDateTypeMonth() {
        btnSpecificMonth.check();
    }

    public void setAnyDayOfWeek() {
        selectDayOfWeek.selectByValue(ANY_DAY_OPTION_VALUE);
    }

    public void assertMinPriceDestination(String expectedStation, String expectedCountry) {
        assertEquals(lblMinPriceCity.getText(),expectedStation);
        assertEquals(lblMinPriceCountry.getText(),expectedCountry);
    }

    public void assertMinPrice(double expectedPrice) {
        assertEquals(Double.parseDouble(lblMinPrice.getText()),expectedPrice);
    }
}
