package base;

import base.browser.Browser;
import base.logging.BaseLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/5/2018.
 */
abstract public class Base {

    protected static Browser browser = Browser.getInstance();

    protected BaseLogger logger = createLoggerFactoryMethod();

    abstract protected BaseLogger createLoggerFactoryMethod();

    protected void info(String msg) {
        logger.info(this, msg);
    }

    protected void warn(String msg) {
        logger.warn(this, msg);
    }
    protected void fatal(String msg) {
        logger.fatal(this, msg);
    }
    public void assertinfo(final Object actual, final Object expected) {
       info(String.format("[Assertion: expected ='%1$s', actual = '%2$s'])",expected.toString(),actual.toString()));
    }

    public void checkInfo(final Object condition, final Object result) {
        info(String.format("[Assertion: '%1$s' is '%2$s'])",condition.toString(),result.toString()));
    }

    public void assertEquals(final Object actual, final Object expected) {
        assertinfo(actual,expected);
        Assert.assertEquals(actual,expected);
    }

    public WebElement findElement(By by) {

        Browser browser = Browser.getInstance();
        return browser.getWait().until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });

    }

    public List<WebElement> findElements(By by) {

        Browser browser = Browser.getInstance();
        return browser.getWait().until(new Function<WebDriver, List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                return driver.findElements(by);
            }
        });

    }


    public boolean isElementPresent(By by) {
        List <WebElement> list = Browser.getInstance().getDriver().findElements(by);
        return (list.size()>0);
    }

    public boolean isElementVisible(By by) {
        if(isElementPresent(by)) {
            try {
                Wait<WebDriver> wait =
                        new FluentWait<WebDriver>(Browser.getInstance().getDriver())
                                .withTimeout(Long.parseLong(Browser.getInstance().getTimeoutForLoad()), TimeUnit.SECONDS)
                                .pollingEvery(Long.parseLong(Browser.getInstance().getTimeoutForCondition()), TimeUnit.SECONDS)
                                .ignoring(ElementNotVisibleException.class);


                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }



    public void waitReload(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
