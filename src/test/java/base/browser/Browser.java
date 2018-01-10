package base.browser;

import base.Base;
import base.logging.BaseLogger;
import base.logging.BrowserLogger;
import base.utils.PropertiesResourceManager;
import com.google.common.base.Strings;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import javax.naming.NamingException;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/3/2018.
 */
public class Browser extends Base {

    private static final String PROPERTIES_FILE = "selenium.properties";
    private static final long IMPLICITY_WAIT = 2;
    private static final long SCRIPT_WAIT = 60;
    private static final String DEFAULT_CONDITION_TIMEOUT = "defaultConditionTimeout";
    private static final String DEFAULT_LOAD_TIMEOUT = "defaultPageLoadTimeout";
    private static final String DEFAULT_ELEMENT_LOAD_TIMEOUT  = "defaultElementNotDisplayedTimeout";
    private static final String BROWSER_BY_DEFAULT = "chrome";
    private static final String BROWSER_PROP = "browser";
    private static final String START_URL_PROP = "urlStartPage";


    private static PropertiesResourceManager props;
    private static Browser instance;

    private static RemoteWebDriver driver;
    private static String timeoutForLoad;
    private static String timeoutForCondition;
    private static String timeoutForNotDisplayed;
    public static Browsers currentBrowser;

//    private static Logger logger = Logger.getLogger(Browser.class);
    private Browser() {
        info("constructed");
    }

    @Override
    protected BaseLogger createLoggerFactoryMethod() {
        return new BrowserLogger();
    }

    public static Browser getInstance() {
        if (instance == null) {
            initProperties();
            try {
                driver = BrowserFactory.setUp(currentBrowser.toString());
                driver.manage().timeouts().implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);
               /* driver.manage().timeouts().setScriptTimeout(60,TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);*/
            } catch (NamingException e) {
                e.printStackTrace();
            }
            instance = new Browser();
        }
        return instance;
    }

    private static void initProperties() {

        props = new PropertiesResourceManager(PROPERTIES_FILE);
        timeoutForLoad = props.getProperty(DEFAULT_LOAD_TIMEOUT);
        timeoutForCondition = props.getProperty(DEFAULT_CONDITION_TIMEOUT);
        timeoutForNotDisplayed = props.getProperty(DEFAULT_ELEMENT_LOAD_TIMEOUT);


        if (Strings.isNullOrEmpty(props.getProperty(BROWSER_PROP))) {
            currentBrowser = Browsers.valueOf(System.getProperty(BROWSER_PROP, BROWSER_BY_DEFAULT).toUpperCase());
        } else {
            String proper = props.getProperty(BROWSER_PROP);
            currentBrowser = Browsers.valueOf(proper.toUpperCase());
        }
    }

    public void open() {
        driver.get(props.getProperty(START_URL_PROP));
    }


    public void close() {
        try {
            driver.quit();
            info("close");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            instance = null;
        }
    }

    public String getTimeoutForCondition() {
        return timeoutForCondition;
    }

    public String getTimeoutForLoad() {
        return timeoutForLoad;
    }
    public String getTimeoutForNotDisplayed() {
        return timeoutForNotDisplayed;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }


    public Wait<WebDriver> getWait() {
        return new FluentWait<WebDriver>(instance.getDriver())
                .withTimeout(Long.parseLong(instance.getTimeoutForLoad()), SECONDS)
                .pollingEvery(Long.parseLong(instance.getTimeoutForCondition()), SECONDS)
                .ignoring(NoSuchElementException.class);
    }



    public enum Browsers {

        FIREFOX("firefox"),
        CHROME("chrome");

        public String value;

        Browsers(final String values) {
            value = values;
        }

        public String toString() {
            return value;
        }
    }

}
