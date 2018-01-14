package base.element;

import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;

public class ElementFactory {


    public <E extends BaseElement> E create(final Class<E> elementClass, final WebElement wrappedElement) {

        final E element = createInstanceOf(elementClass,wrappedElement);
        element.init();
        return element;
    }

    private <E extends BaseElement> E createInstanceOf(final Class<E> elementClass, final WebElement wrappedElement) {
        try {
            return elementClass
                    .getDeclaredConstructor(WebElement.class)
                    .newInstance(wrappedElement);
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

