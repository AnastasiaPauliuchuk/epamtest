package base.element;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/4/2018.
 */
public class ElementFieldDecorator extends DefaultFieldDecorator {

   // private ElementFactory elementFactory = new DefaultElementFactory();
    private ContainerFactory containerFactory = new ContainerFactory();

    public ElementFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    /**
     * Метод вызывается фабрикой для каждого поля в классе
     */
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<BaseElement> decoratableClass = decoratableClass(field);
        // если класс поля декорируемый
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }

            if (List.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass);
            }

            return createElement(loader, locator, decoratableClass);
        }
        if (BaseContainer.class.isAssignableFrom(field.getType())) {
            return decorateContainer(loader, field);
        }
        return super.decorate(loader, field);
    }


    /**
     * Возвращает декорируемый класс поля,
     * либо null если класс не подходит для декоратора
     */
    @SuppressWarnings("unchecked")
    private Class<BaseElement> decoratableClass(Field field) {

        Class<?> clazz = field.getType();

        if (List.class.isAssignableFrom(clazz)) {

            // для списка обязательно должна быть задана аннотация
            if (field.getAnnotation(FindBy.class) == null &&
                    field.getAnnotation(FindBys.class) == null) {
                return null;
            }

            // Список должен быть параметризирован
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            // получаем класс для элементов списка
            clazz = (Class<?>) ((ParameterizedType) genericType).
                    getActualTypeArguments()[0];
        }

        if (BaseElement.class.isAssignableFrom(clazz)) {
            return (Class<BaseElement>) clazz;
        }
        else {
            return null;
        }
    }

    /**
     * Создание элемента.
     * Находит WebElement и передает его в кастомный класс
     */
    protected BaseElement createElement(ClassLoader loader,
                                     ElementLocator locator,
                                     Class<BaseElement> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy);
    }

    /**
     * Создание списка
     */
    @SuppressWarnings("unchecked")
    protected List<BaseElement> createList(ClassLoader loader,
                                        ElementLocator locator,
                                        Class<BaseElement> clazz) {

        InvocationHandler handler =
                new LocatingCustomElementListHandler(locator, clazz);
        List<BaseElement> elements =
                (List<BaseElement>) Proxy.newProxyInstance(
                        loader, new Class[] {List.class}, handler);
        return elements;
    }
    private ElementLocator createLocator(final Field field) {
        return factory.createLocator(field);
    }

    private Object decorateContainer(final ClassLoader loader, final Field field) {
        final WebElement wrappedElement = proxyForLocator(loader, createLocator(field));
        final BaseContainer container = containerFactory.create((Class<? extends BaseContainer>) field.getType(), wrappedElement);

        PageFactory.initElements(new ElementFieldDecorator(wrappedElement), container);
        return container;
    }
}


