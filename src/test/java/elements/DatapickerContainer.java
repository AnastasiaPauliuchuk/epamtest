package elements;

import base.element.AbstractBaseElement;
import base.element.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/7/2018.
 */
public class DatapickerContainer extends AbstractBaseElement {

    private static final String DAY_CALENDAR_LOCATOR =
            ".//div[@id=\"ui-datepicker-div\"]/table//td[@data-day=\"%d\"]";
    public static final String DATE_DAY_ATTR = "data-day";
    public static final String DATE_MONTH_ATTR = "data-month";
    public static final String DATE_YEAR_ATTR = "data-year";

    private static final String NEXT_MONTH_BUTTON_LOCATOR = "div/div/a[contains(@class,\"ui-datepicker-next\")]";
    private static final String PREV_MONTH_BUTTON_LOCATOR = "div/div/a[contains(@class,\"ui-datepicker-prev\")]";
    private static final String MONTH_LABEL = "//span[contains(@class,\"ui-datepicker-month\")]";
    private static final String YEAR_LABEL = "//span[contains(@class,\"ui-datepicker-year\")]";
    public DatapickerContainer(WebElement wrappedElement) {
        super(wrappedElement);
    }


    public LocalDate selectDay(int day) {
        WebElement dayElement = this.findElement(new By.ByXPath(String.format(DAY_CALENDAR_LOCATOR, day)));

        LocalDate date = LocalDate.of(Integer.parseInt(dayElement.getAttribute(DATE_YEAR_ATTR)),
                Integer.parseInt(dayElement.getAttribute(DATE_MONTH_ATTR)),
                Integer.parseInt(dayElement.getAttribute(DATE_DAY_ATTR)));

        BaseElement dayCalendar = new Button(dayElement);
        info("select day " + day);

        dayCalendar.click();
        return date;
    }

    @Override
    public String getElementType() {
        return "Calendar";
    }

    public void incrementMonth() {
       /* WebElement nextElement = this.findElement(new By.ByXPath(NEXT_MONTH_BUTTON_LOCATOR));
        BaseElement nextMonth = new Button(nextElement);
        info("increment month");
        nextMonth.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        switchMonth(-1);
    }

    public void switchMonth(long monthCount) {

        if (monthCount > 0) {


            for (long i = 0; i < monthCount; i++) {

                WebElement prevElement = this.findElement(new By.ByXPath(PREV_MONTH_BUTTON_LOCATOR));

                prevElement.click();
                waitReload(1000);
            }
        } else {
            WebElement nextElement = this.findElement(new By.ByXPath(NEXT_MONTH_BUTTON_LOCATOR));
            for (long i = monthCount; i < 0; i++) {
                nextElement.click();
                waitReload(1000);
            }
        }

        Label currentMonth = new Label(findElement(new By.ByXPath(MONTH_LABEL)));
        Label currentYear = new Label(findElement(new By.ByXPath(YEAR_LABEL)));
        info(String.format("set %1$s %2$s",currentMonth.getText(),currentYear.getText()));
    }

}
