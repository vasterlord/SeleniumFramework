package com.epam.lab.seleniumframework.controls;

import com.epam.lab.seleniumframework.controls.decorationutils.AbstractElement;
import org.openqa.selenium.WebElement;

public class TextInput extends AbstractElement {

    // link to decorator
    //
    public TextInput(WebElement webElement) {
        super(webElement);
    }

    public boolean isVisible() {
        System.out.println(this.getTagName());
        return getWrappedElement().getCssValue("visibility").equalsIgnoreCase("visible");
    }
}
