package pages;

import base.page.BasePage;
import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/10/2018.
 */
public class FareSelectPage extends BasePage {


    private final static String TABLE_LOCATOR = "//table[contains(@class,\"ssr-table\")]";
    private final static String BASIC_OPTION = "A";
    private final static String PLUS_OPTION = "B";
    private final static String MAX_OPTION = "C";
    private final static double BABY_PRICE = 20;
    private final static String COLUMN_INDEX_REGEXP =".*column-(\\d)";
    private final static String PRICE_REGEXP ="(.*)\\W(\\d+)";
    private final static String TOTAL_PRICE_REGEXP = "\\W(\\d+\\.)(.*)(\\d{2})";




    private final static String PRICE_PERSON_LOCATOR_TEMPLATE = "(//td[@class=\"td-content\"]//span[contains(@class,\"price\")])[%d]";
    private final static String PRICE_TOTAL_LOCATOR = "//section[@class=\"price-section\"]//div[@class=\"back\"]";

    private final static String OPTION_BUTTON_TEMPLATE_LOCATOR = " //th[@data-product-class=\"%s\"]";


    private final static String OPTION_BUTTON_SELECTED_LOCATOR = "following-sibling::button[contains(@class,\"button-call-to-action\")]";


    public void selectBasicOption()
    {
        selectOption(BASIC_OPTION);
    }

    public void selectPlusOption()
    {
        selectOption(PLUS_OPTION);
    }
    public void selectMaxOption()
    {
        selectOption(MAX_OPTION);
    }

    private void selectOption(String option) {

        Button btnSelect = new Button(findElement(new By.ByXPath(String.format(OPTION_BUTTON_TEMPLATE_LOCATOR, option))));
        btnSelect.click();
        waitSelected();

    }

    public void waitSelected() {
        isElementVisible(new By.ByXPath(OPTION_BUTTON_SELECTED_LOCATOR));

    }


    public FareSelectPage(String name) {
        super(name);
    }

    @Override
    public By getMarkerLocator() {
        return new By.ByXPath(TABLE_LOCATOR);
    }

    public double getPricePerPerson() {

        int selectedColumn= getSelectedColumn();
        WebElement priceElement = findElement(new By.ByXPath(String.format(PRICE_PERSON_LOCATOR_TEMPLATE,selectedColumn)));
        Pattern p = Pattern.compile(PRICE_REGEXP);
        Matcher m = p.matcher(priceElement.getAttribute("innerHTML"));
        if(m.find()) {
            return Double.parseDouble(m.group(2));
        }
        return -1;

    }

    private int getSelectedColumn() {
        WebElement tableElement = findElement(new By.ByXPath(TABLE_LOCATOR));
        String className = tableElement.getAttribute("class");
        Pattern p = Pattern.compile(COLUMN_INDEX_REGEXP);
        Matcher m = p.matcher(className);
        if(m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return 0;
    }

    public double getPriceBaby() {
        return BABY_PRICE;

    }

    public double getTotalPrice() {
        WebElement priceElement = findElement(new By.ByXPath(PRICE_TOTAL_LOCATOR));
        Pattern p = Pattern.compile(TOTAL_PRICE_REGEXP);
        Matcher m = p.matcher(priceElement.getAttribute("innerHTML"));
        if(m.find()) {
            return Double.parseDouble(m.group(1)+m.group(3));
        }
        return -1;

    }


}
