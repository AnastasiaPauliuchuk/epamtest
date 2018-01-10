package base.page;

import base.Base;
import base.element.ExtendedFieldDecorator;
import base.logging.BaseLogger;
import base.logging.PageLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/3/2018.
 */
public abstract class BasePage extends Base {


    private String name;

    public BasePage(String name) {
        this.name = name;
    }

    @Override
    protected BaseLogger createLoggerFactoryMethod() {
        return PageLogger.getInstance();
    }

    public String getName() {
        return name;
    }

    public void init(WebDriver driver) {

        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
       this.assertDisplayed();
        info("displayed");
    }

    abstract public By getMarkerLocator();



    private void assertDisplayed() {

        Assert.assertTrue(isElementVisible(getMarkerLocator()));

    }



}
