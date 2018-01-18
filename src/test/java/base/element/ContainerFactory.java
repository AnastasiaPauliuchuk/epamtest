package base.element;

import org.openqa.selenium.WebElement;

public class ContainerFactory {

    public <C extends BaseContainer> C create(final Class<C> containerClass, final WebElement wrappedElement) {
        final C container = createInstanceOf(containerClass);
        container.init(wrappedElement);
        return container;
    }

    private <C extends BaseContainer> C createInstanceOf(final Class<C> containerClass) {
        try {
            return containerClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
