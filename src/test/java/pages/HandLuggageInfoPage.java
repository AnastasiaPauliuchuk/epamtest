package pages;

import base.browser.Browser;
import base.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/15/2018.
 */
public class HandLuggageInfoPage extends BasePage {

    private final static String MARKER_LOCATOR = "//div[@id=\"arrival-and-departure-results-2\"]";
    private final static String IFRAME_LOCATOR = "descendant::iframe";
    private final static String IFRAME_HEAD_LOCATOR = "//a[contains(@class,\"ytp-title-link\")]";
    private WebElement iframeElement = null;

    @FindBy(xpath = "//div[@class=\"video\"]")
    private WebElement videoElement;


    public HandLuggageInfoPage(String name) {
        super(name);
    }

    @Override
    public By getMarkerLocator() {
        return new By.ByXPath(MARKER_LOCATOR);
    }

    public String getVideoLink() {
        iframeElement = videoElement.findElement(new By.ByXPath(IFRAME_LOCATOR));
        WebDriver driver = Browser.getInstance().getDriver();
        driver.switchTo().frame(iframeElement);
        WebElement linkElement = Browser.getInstance().getDriver().findElement(By.xpath(IFRAME_HEAD_LOCATOR));
        String src = linkElement.getAttribute("href");
        driver.switchTo().defaultContent();
        return src;

    }

    public void goVideoLink() {
        this.scrollToElement(videoElement);
        iframeElement = videoElement.findElement(new By.ByXPath(IFRAME_LOCATOR));
        WebDriver driver = Browser.getInstance().getDriver();
        driver.switchTo().frame(iframeElement);
        WebElement linkElement = Browser.getInstance().getDriver().findElement(By.xpath(IFRAME_HEAD_LOCATOR));
        linkElement.click();
        driver.switchTo().parentFrame();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
    }

    public void assertVideoLink(String expectedLink) {

        assertEquals(getVideoLink(),expectedLink);
    }

    public void scrollToVideo() {
        this.scrollToElement(videoElement);
    }
}
