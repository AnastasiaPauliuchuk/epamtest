package base.test;

import base.Base;
import base.browser.Browser;
import base.logging.BaseLogger;
import base.logging.TestLogger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/3/2018.
 */
public abstract class BaseTest extends Base {


    @Override
    protected BaseLogger createLoggerFactoryMethod() {
        return TestLogger.getInstance();
    }

    @BeforeClass
    public void setUp() {
        Browser.getInstance().open();
    }

    @AfterClass
    public void tearDown() {
        Browser.getInstance().close();

    }

    public abstract void runTest();

    @Test
    public void executeTest() throws Throwable {

        info("started");
        runTest();
        info("completed");
    }

    public void step(int i, String msg) {
        ((TestLogger) logger).step(this, i, msg);
    }
    public void step(String customStepNumber , String msg) {
        ((TestLogger) logger).step(this, customStepNumber, msg);
    }
    public void check(String msg) {
        ((TestLogger) logger).check(this, msg);
    }


}
