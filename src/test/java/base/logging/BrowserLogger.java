package base.logging;

import base.browser.Browser;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/5/2018.
 */
public class BrowserLogger extends BaseLogger {

    public void info(Object o, String msg) {
        Browser browser = (Browser) o;
        logger.info(String.format("Browser '%1$s' : %2$s", browser.currentBrowser.toString(), msg));
    }
}
