package elements;

import base.element.AbstractBaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/6/2018.
 */
public class CheckBox extends AbstractBaseElement {

    //private final static String LABEL_FOR_LOCATOR;
    public CheckBox(WebElement wrappedElement) {
        super(wrappedElement);
    }


    @Override
    public String getElementType() {
        return "Checkbox";
    }

    public boolean isChecked() {
        return wrappedElement.isSelected();
    }

    public void setChecked(boolean state) {
        if(wrappedElement.isSelected()!=state)
        {
            By labelLoc = By.xpath(String.format("//label[@for=\"%s\"]",wrappedElement.getAttribute("id")));
            WebElement label = this.findElement(labelLoc);
            label.click();
            info("click");
        }

    }

}
