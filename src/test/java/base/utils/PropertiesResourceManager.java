package base.utils;

import base.Base;
import base.logging.BaseLogger;
import base.logging.TestLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/3/2018.
 */
public class PropertiesResourceManager extends Base {

    private Properties properties = new Properties();


    public PropertiesResourceManager() {
        properties = new Properties();
    }

    public PropertiesResourceManager(final String resourceName) {
        properties = appendFromResource(properties, resourceName);

    }


     private Properties appendFromResource(final Properties objProperties, final String resourceName) {
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);

        if (inStream != null) {
            try {
                objProperties.load(inStream);
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
                  fatal(String.format("Resource \"%1$s\" could not be found", resourceName));
        }
        return objProperties;
    }

    public String getProperty(final String key) {
        return properties.getProperty(key);
    }


    public String getProperty(final String key, final String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    @Override
    protected BaseLogger createLoggerFactoryMethod() {
        return TestLogger.getInstance();
    }
}
