package base.page;

import base.Base;
import base.browser.Browser;
import base.element.ExtendedFieldDecorator;
import base.logging.BaseLogger;
import base.logging.PageLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/3/2018.
 */
public abstract class BasePage extends Base {


    private String name;

    public BasePage(String name) {
        this.name = name;
    }

    @Override
    protected BaseLogger createLoggerFactoryMethod() {
        return PageLogger.getInstance();
    }

    public String getName() {
        return name;
    }

    public void init(WebDriver driver) {

        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
        this.assertDisplayed();
        info("displayed");
    }

    abstract public By getMarkerLocator();


    private void assertDisplayed() {

        Assert.assertTrue(isElementVisible(getMarkerLocator()));
    }

    public void scrollDown() {
        JavascriptExecutor js = ((JavascriptExecutor) Browser.getInstance().getDriver());
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        info("scroll down");
    }

    public void scrollToElement(WebElement element) {
        Point p = element.getLocation();
        int y = p.getY();
        JavascriptExecutor js = ((JavascriptExecutor) Browser.getInstance().getDriver());
        js.executeScript(String.format("window.scrollTo(0,%d)", y));
        info("scroll");
    }

}
