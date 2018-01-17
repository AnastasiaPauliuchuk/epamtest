package pages;

import base.page.BasePage;
import elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/15/2018.
 */
public class YouTubeVideoPage extends BasePage {



    private final static String MARKER_LOCATOR = "//video[contains(@class,\"video-stream\")]";

    @FindBy(css = "h1[class~=\"title\"]")
    private Label lblTitle;

    @FindBy(xpath = "//div[@id=\"owner-container\"]//a")
    private Label lblAuthor;


    public YouTubeVideoPage(String name) {
        super(name);
    }

    @Override
    public By getMarkerLocator() {
        return new By.ByXPath(MARKER_LOCATOR);
    }

    public void assertTitle(String expectedTitle) {
        assertEquals(lblTitle.getText(),expectedTitle);

    }

    public void assertAuthor(String expectedAuthor) {
        assertEquals(lblAuthor.getText(),expectedAuthor);

    }
}
