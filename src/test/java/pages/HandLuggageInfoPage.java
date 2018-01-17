package pages;

import base.browser.Browser;
import base.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        this.scrollToElement(videoElement);
        iframeElement = videoElement.findElement(new By.ByXPath(IFRAME_LOCATOR));
        WebDriver driver = Browser.getInstance().getDriver();
        driver.switchTo().frame(iframeElement);
        //String src = iframeElement.getAttribute("src");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement linkElement = Browser.getInstance().getDriver().findElement(By.xpath(IFRAME_HEAD_LOCATOR));
        String src = linkElement.getAttribute("href");
        driver.switchTo().defaultContent();

        return src;

    }

    public void goVideoLink(String url) {
       /* this.scrollToElement(videoElement);
        iframeElement = videoElement.findElement(new By.ByXPath(IFRAME_LOCATOR));
        WebDriver driver = Browser.getInstance().getDriver();
        driver.switchTo().frame(iframeElement);
        WebElement linkElement = Browser.getInstance().getDriver().findElement(By.xpath(IFRAME_HEAD_LOCATOR));
        linkElement.click();
        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();*/

       //todo switch to new browser tab
        Browser.getInstance().getDriver().get(url);
    }

    public void assertVideoLink(String expectedLink) {

        assertEquals(getVideoLink(),expectedLink);
    }
}
