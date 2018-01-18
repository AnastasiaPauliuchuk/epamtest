package base.logging;

import org.apache.log4j.Logger;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/3/2018.
 */
public class BaseLogger {


    protected static Logger logger = Logger.getLogger(BaseLogger.class);

    public void info(Object o, String msg) {
        logger.info(msg);
    }

    public void warn(Object o, String msg) {
        logger.warn(msg);
    }

    public void fatal(Object o, String msg) {
        logger.fatal(msg);
    }

    public void assertInfo(Object o, final Object expected, final Object actual) {
        logger.info(String.format("[Assertion: expected ='%1$s', actual = '%2$s'])", expected.toString(), actual.toString()));
    }

    public void assertCheck(Object o, final Object result, final Object condition) {
        logger.info(String.format("[Verify: condition ='%1$s', result = '%2$s'])", condition.toString(), result.toString()));
    }
}
