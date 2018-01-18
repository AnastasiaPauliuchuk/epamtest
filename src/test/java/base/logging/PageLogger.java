package base.logging;

import base.page.BasePage;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/5/2018.
 */
public class PageLogger extends BaseLogger {

    private static PageLogger instance = null;

    private PageLogger() {    }

    public static PageLogger getInstance() {
        if (instance == null) {
            instance = new PageLogger();
        }
        return instance;
    }

    @Override
    public void info(Object o, String msg) {
        BasePage page = (BasePage) o;
        logger.info(String.format("** Page '%1$s': %2$s **", page.getName(), msg));
    }

    @Override
    public void warn(Object o, String msg) {
        super.warn(o, msg);
    }

    @Override
    public void fatal(Object o, String msg) {
        super.fatal(o, msg);
    }


}
