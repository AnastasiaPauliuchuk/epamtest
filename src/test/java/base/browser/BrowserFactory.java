package base.browser;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.naming.NamingException;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/3/2018.
 */
public class BrowserFactory {

    private static final String BROWSER_PROPERTIES_FILE = "selenium.properties";

    public static RemoteWebDriver setUp(final Browser.Browsers type) {


        RemoteWebDriver driver = null;
        File myFile = null;
        URL myTestURL;


        switch (type) {
            case CHROME:

                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

                chromePrefs.put("download.directory_upgrade", true);
                chromePrefs.put("browser.helperApps.alwaysAsk.force", false);

                chromePrefs.put("safebrowsing.enabled", true);
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.prompt_for_download", "false");

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("start-maximized", "disable-popup-blocking", "--incognito");
                myTestURL = ClassLoader.getSystemResource("chromedriver.exe");
                try {
                    myFile = new File(myTestURL.toURI());
                } catch (URISyntaxException e1) {

                }
                System.setProperty("webdriver.chrome.driver", myFile.getAbsolutePath());

                driver = new ChromeDriver(options);
                break;

            case FIREFOX:

                FirefoxOptions ffoptions = new FirefoxOptions();

                ffoptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream")
                        .addPreference("browser.download.folderList", 2)
                        .addPreference("browser.privatebrowsing.autostart", true);
                       // .addPreference("dom.max_script_run_time", 60);
                myTestURL = ClassLoader.getSystemResource("geckodriver.exe");
                try {
                    myFile = new File(myTestURL.toURI());
                } catch (URISyntaxException e1) {
                }

                System.setProperty("webdriver.gecko.driver", myFile.getAbsolutePath());
                driver = new FirefoxDriver(ffoptions);
                break;
        }
        return driver;
    }

    public static RemoteWebDriver setUp(final String type) throws NamingException {
        for (Browser.Browsers t : Browser.Browsers.values()) {
            if (t.toString().equalsIgnoreCase(type)) {
                return setUp(t);
            }
        }
        throw new NamingException("Naming exception");
    }
}


