package base.logging;

import base.element.BaseElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/6/2018.
 */
public class ElementLogger extends BaseLogger {

    private static ElementLogger instance = null;

    private ElementLogger() {
    }

    public static ElementLogger getInstance()
    {
        if(instance == null) {
            instance = new ElementLogger();
        }
        return instance;
    }

    @Override
    public void info(Object o, String msg) {
        BaseElement element = (BaseElement)o;
        logger.info(String.format("*** %1$s '%2$s' : %3$s ***", element.getElementType(), element.getElementName(), msg));
    }
}
