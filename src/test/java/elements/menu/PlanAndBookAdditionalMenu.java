package elements.menu;

import elements.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/14/2018.
 */
public class PlanAndBookAdditionalMenu extends TopAdditionalMenu {


    private final static String ADVANCED_SEARCH_LOCATOR = "//a[contains(@href,\"/book-a-flight/advanced-search/search/\")]";

    public PlanAndBookAdditionalMenu(WebElement wrappedElement) {
        super(wrappedElement);
    }


    public void goAdvancedSearch() {
        Link linkAdvancedSearch = new Link(wrappedElement.findElement(new By.ByXPath(ADVANCED_SEARCH_LOCATOR)));
        linkAdvancedSearch.click();
    }
}
