package com.epam.lab.seleniumframework.controls;

import com.epam.lab.seleniumframework.controls.decorationutils.AbstractElement;
import org.openqa.selenium.WebElement;

public class TextInput extends AbstractElement {


    public TextInput(WebElement webElement) {
        super(webElement);
    }

    public boolean isVisible() {
        return getWrappedElement().getCssValue("visibility").equalsIgnoreCase("visible");
    }
}
