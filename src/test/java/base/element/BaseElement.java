package base.element;

import base.Base;
import base.browser.Browser;
import base.logging.BaseLogger;
import base.logging.ElementLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/3/2018.
 */
public abstract class BaseElement extends Base {

    private static final int TIMEOUT_WAIT_0 = 0;

    protected String name;
    protected WebElement wrappedElement;
    protected String id;

    @Override
    protected BaseLogger createLoggerFactoryMethod() {
        return ElementLogger.getInstance();
    }

    abstract public void init();

    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    public abstract String getElementType();

    public void sendKeys(Keys key) {
        wrappedElement.sendKeys(key);
    }


    public void click() {
        if (this.isElementClickable()) {
            wrappedElement.click();
            info("click");
        }
    }

    public boolean isElementClickable() {

        try {
            Wait<WebDriver> wait =
                    new FluentWait<WebDriver>(Browser.getInstance().getDriver())
                            .withTimeout(Long.parseLong(Browser.getInstance().getTimeoutForLoad()), TimeUnit.SECONDS)
                            .pollingEvery(Long.parseLong(Browser.getInstance().getTimeoutForCondition()), TimeUnit.SECONDS);


            wait.until(ExpectedConditions.elementToBeClickable(wrappedElement));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isElementVisible() {

        try {
            Wait<WebDriver> wait =
                    new FluentWait<WebDriver>(Browser.getInstance().getDriver())
                            .withTimeout(Long.parseLong(Browser.getInstance().getTimeoutForLoad()), TimeUnit.SECONDS)
                            .pollingEvery(Long.parseLong(Browser.getInstance().getTimeoutForCondition()), TimeUnit.SECONDS);


            wait.until(ExpectedConditions.visibilityOf(wrappedElement));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public void hover() {

        Actions actions = new Actions(Browser.getInstance().getDriver());
        actions.moveToElement(wrappedElement).perform();
        info("hover");
    }

    public boolean waitUntilContentChanged(String oldText) {

        Wait<WebDriver> wait =
                new FluentWait<WebDriver>(Browser.getInstance().getDriver())
                        .withTimeout(Long.parseLong(Browser.getInstance().getTimeoutForLoad()), TimeUnit.SECONDS)
                        .pollingEvery(Long.parseLong(Browser.getInstance().getTimeoutForCondition()), TimeUnit.SECONDS);


        wait.until(ExpectedConditions.attributeToBe(this.getWrappedElement(), "innerHTML", oldText));

        return true;
    }

    public String getElementName() {

        String result = this.toString();
        try {
            WebElement element = this.getWrappedElement();

            String id = element.getAttribute("id");
            if (id.length() > 0)
                result = id;
            else {
                String name = element.getAttribute("name");
                if (name.length() > 0)
                    result = name;
            }

        } catch (Exception e) {

        }
        return result;
    }


    public WebElement findElement(By by) {

        WebElement element = this.getWrappedElement();
        return browser.getWait().until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return element.findElement(by);
            }
        });

    }

    public List<WebElement> findElements(By by) {
        WebElement element = this.getWrappedElement();
        return browser.getWait().until(new Function<WebDriver, List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> list = element.findElements(by);
                return list;
            }
        });

    }

    public boolean isRelativeElementPresent(By by) {

        List<WebElement> list = this.findElements(by);
        return (list.size() > 0);
    }


    public void scrollToElement() {
        Point p = this.getWrappedElement().getLocation();
        int y = p.getY();
        JavascriptExecutor js = ((JavascriptExecutor) Browser.getInstance().getDriver());
        js.executeScript(String.format("window.scrollTo(0,%d)", y));
        info("scroll to the element");
    }

}
