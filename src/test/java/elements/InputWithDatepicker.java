package elements;

import base.browser.Browser;
import base.element.AbstractBaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/6/2018.
 */
public class InputWithDatepicker  extends AbstractBaseElement {


    private static final By ICON_CLICKABLE_LOCATOR = new By.ByXPath("parent::div/descendant::span[contains(@class,\"datepicker-trigger\")]");
    private static final By INPUT_LOCATOR = new By.ByXPath("descendant::input[@type=\"text\"]");
    private static By DAY_SELECT_LOCATOR = new By.ByXPath("parent::div/following::div/select[@class=\"date-day\"]");
    private static By MONTH_SELECT_LOCATOR = new By.ByXPath("parent::div/following::div/select[@class=\"date-month\"]");
    private static By YEAR_SELECT_LOCATOR = new By.ByXPath("parent::div/following::div/select[@class=\"date-year\"]");
    private static DatapickerContainer calendar = null;
    private static By CALENDAR_LOCATOR = new By.ByXPath("//div[@data-date-picker-placeholder]");

    private static By SUGGEST_LABEL_LOCATOR = new By.ByXPath("parent::div[@data-suggestion-label]");

    private static final String DATE_FORMAT = "dd-MM-yyyy";


    private static final String CLASS_DISABLED = "placeholder-as-if-input";

    public InputWithDatepicker(WebElement wrappedElement) {
        super(wrappedElement);
    }


    public DatapickerContainer openCalendar() {
        info("open calendar");
        WebElement element = findElement(ICON_CLICKABLE_LOCATOR);
        element.click();
        List<WebElement> calendars = Browser.getInstance().findElements(CALENDAR_LOCATOR);
        for (WebElement el : calendars) {
            if (el.isDisplayed())
                return new DatapickerContainer(el);
        }
        return null;
    }


    public LocalDate selectDayFromCalendar(int day) {
        calendar = openCalendar();
        return calendar.selectDay(day);
    }
    public LocalDate selectDayNextMonth(int day) {
        calendar = openCalendar();
        calendar.incrementMonth();
        return calendar.selectDay(day);
    }
    public void assertDaySelected(int day) {
        Select daySelected = new Select(wrappedElement.findElement(DAY_SELECT_LOCATOR));
        String dayOption= daySelected.getFirstSelectedOption().getText();
        assertEquals(dayOption,day);
    }

    @Override
    public String getElementType() {
        return "Datapicker";
    }

    public void assertDateSelected(LocalDate date) {
        int day = Integer.parseInt(findElement(DAY_SELECT_LOCATOR).getAttribute("value"));
        int month = Integer.parseInt(findElement(MONTH_SELECT_LOCATOR).getAttribute("value"));
        int year = Integer.parseInt(findElement(YEAR_SELECT_LOCATOR).getAttribute("value"));
        assertEquals(date,LocalDate.of(year,month,day));

    }

    public void assertIsDisabled() {
        TextBox tbDate = new TextBox(findElement(INPUT_LOCATOR));
        String cssClass = tbDate.getWrappedElement().getAttribute("class");
        checkInfo("Input is disabled","true");
        Assert.assertTrue(cssClass.contains(CLASS_DISABLED));
    }

    public void setDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String dateString = date.format(formatter);
        TextBox tbDate = new TextBox(findElement(INPUT_LOCATOR));
        tbDate.clear();
        tbDate.type(dateString);



    }

    public void setDateCalendar(LocalDate date) {
        LocalDate dateToday = LocalDate.now();
        Period period = Period.between(date, dateToday);
        long monthCount = period.toTotalMonths();
        calendar = openCalendar();
        calendar.switchMonth(monthCount);
        calendar.selectDay(date.getDayOfMonth());


    }
}
