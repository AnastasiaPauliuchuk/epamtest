package base.element;

import base.Base;
import base.browser.Browser;
import base.logging.BaseLogger;
import base.logging.ElementLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    //protected By locator;
    protected WebElement wrappedElement;
    protected String id;

    @Override
    protected BaseLogger createLoggerFactoryMethod() {
        return ElementLogger.getInstance();
    }

    abstract public void init();
    /*public BaseElement(WebElement wrappedElement) {

        this.wrappedElement = wrappedElement;
    }*/
  /*  protected BaseElement(final By loc) {
        locator = loc;
    }*/
    /*protected BaseElement(final By loc, final String nameOf) {
        locator = loc;
        name = nameOf;
    }*/

    /* private void waitForIsElementPresent() {
         try {
             Wait<WebDriver> wait =
                     new FluentWait<WebDriver>(Browser.getInstance().getDriver())
                             .withTimeout(Long.parseLong(Browser.getInstance().getTimeoutForLoad()), TimeUnit.SECONDS)
                             .pollingEvery(Long.parseLong(Browser.getInstance().getTimeoutForCondition()), TimeUnit.SECONDS)
                             .ignoring(NoSuchElementException.class);


             wait.until(ExpectedConditions.presenceOfElementLocated(this.getWrappedElement()));

            // info("is visible : " + true);

         } catch (Exception e) {
             //info("is visible : " + false);

         }
     }*/
    public WebElement getWrappedElement() {
      /*  return Browser.getInstance().getWait().until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });*/
        return wrappedElement;
    }

   /* public By getLocator() {
        return locator;
    }*/

    public abstract String getElementType();

    public void sendKeys(Keys key) {
        //waitForIsElementPresent();
        wrappedElement.sendKeys(key);
    }

    /**
     * Click on the item.
     */
    public void click() {
        wrappedElement.click();
        info("click");

    }

    /**
     * Hover  the item.
     */
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


        wait.until(ExpectedConditions.attributeToBe(this.getWrappedElement(),"innerHTML",oldText));
       /* wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver driver) {
                return !wrappedElement.getText().equals(oldText);
            }
        });*/
        return true;
    }

    public String getElementName() {
        return this.getClass().getCanonicalName();
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

        List <WebElement> list = this.findElements(by);
        return (list.size()>0);
    }


}
