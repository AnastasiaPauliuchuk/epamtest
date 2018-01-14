package elements;

import base.element.AbstractBaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Anastasia Pauliuchuk
 *         created:  1/8/2018.
 */
public class PassCountSpinner extends AbstractBaseElement {

    private TextBox tbxCount;
    private Button btnInc;
    private Button btnDec;
    private static final String INPUT_LOCATOR = "descendant::input[@type=\"text\"]";
    private static final String INC_BUTTON_LOCATOR = "descendant::button[contains(@class,\"increase\")]";
    private static final String DEC_BUTTON_LOCATOR = "descendant::button[contains(@class,\"decrease\")]";
    private static final String VAL_LOCATOR = "descendant::span[@class=\"value\"]";

    @Override
    public String getElementType() {
        return "Spinner";
    }
    /*@Override
    public void init() {
        super.init();
        tbxCount = new TextBox(findElement(new By.ByXPath(INPUT_LOCATOR)));
        btnInc = new Button(findElement(new By.ByXPath(INC_BUTTON_LOCATOR)));
        btnDec = new Button(findElement(new By.ByXPath(DEC_BUTTON_LOCATOR)));

    }*/

    public PassCountSpinner(WebElement wrappedElement) {
        super(wrappedElement);
        tbxCount = new TextBox(findElement(new By.ByXPath(INPUT_LOCATOR)));
        btnInc = new Button(findElement(new By.ByXPath(INC_BUTTON_LOCATOR)));
        btnDec = new Button(findElement(new By.ByXPath(DEC_BUTTON_LOCATOR)));
    }

    public void setField(int count) {
        tbxCount.clear();
        tbxCount.type(String.valueOf(count));

    }
    public void setFieldBySpinner(int count) {
        WebElement el = findElement(new By.ByXPath(VAL_LOCATOR));
        int curCount = Integer.parseInt(el.getAttribute("innerHTML"));
        if(count<curCount)
            for(int i=count;i<curCount;i++) btnDec.click();
        else
            for(int i=count;i>curCount;i--) btnInc.click();

    }

}
