package base.logging;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/5/2018.
 */
public class TestLogger extends BaseLogger {

    private static TestLogger instance = null;
    private TestLogger() {

    }
    public static TestLogger getInstance()
    {
        if(instance == null) {
            instance = new TestLogger();
        }
        return instance;
    }

    @Override
    public void info(Object o, String msg) {
        logger.info(String.format("==================Test '%1$s' %2$s =====================",o.getClass().getName(), msg));
    }

    public void warn(Object o,String msg) {
        logger.warn(String.format("==================Test '%1$s' %2$s =====================",o.getClass().getName(), msg));
    }

    public void fatal(Object o,String msg) {
        logger.fatal(String.format("==================Test '%1$s' %2$s ====================",o.getClass().getName(), msg));
    }
    public void step(Object o,int i, String msg) {
        logger.info(String.format("[Step %1$d: %2$s]",i, msg));
    }
    public void check(Object o, String msg) {
        logger.info(String.format("[Check: %s]", msg));
    }


}
