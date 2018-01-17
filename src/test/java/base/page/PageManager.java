package base.page;

import base.browser.Browser;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/17/2018.
 */
public class PageManager  {

    public static <E extends BasePage> E createPage(final Class<E> elementClass, String pageName) {
        try {
            final E page =  elementClass
                    .getDeclaredConstructor(String.class)
                    .newInstance(pageName);
            page.init((WebDriver) Browser.getInstance().getDriver()  );
            return page;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }



}
