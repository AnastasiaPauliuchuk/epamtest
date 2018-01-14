package elements;

import base.element.AbstractBaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/6/2018.
 */
public class TextBox extends AbstractBaseElement {

    public TextBox(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public TextBox(By by) {
        super(by);
    }


    @Override
    public String getElementType() {
        return "TextBox";
    }

    public void clear() {
        wrappedElement.click();
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        wrappedElement.sendKeys(selectAll);
        wrappedElement.sendKeys(Keys.DELETE);
    }

    public void type(String s) {
        info(String.format("type '%s'",s));
        wrappedElement.sendKeys(s);
    }

    public String getText() {
        return wrappedElement.getText();
    }
}
