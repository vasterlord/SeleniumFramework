package com.epam.lab.seleniumframework.controls;

import com.epam.lab.seleniumframework.controls.decorataionfactory.AbstractElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class TextSection extends AbstractElement {

    private static final Logger LOGGER = Logger.getLogger(TextSection.class);

    public TextSection(WebElement webElement) {
        super(webElement);
    }

    public boolean isVisible() {
        return getWrappedElement().getCssValue("visibility").equalsIgnoreCase("visible");
    }

    public boolean isFullEnabled() {
        return getWrappedElement().isEnabled() && this.getWrappedElement().isDisplayed() && this.isVisible();
    }

    @Override
    public void click() {
        if(isFullEnabled()) {
            super.click();
        } else {
            LOGGER.error("This element is not clickable!");
        }
    }

    @Override
    public String getText() {
        return this.getWrappedElement().getAttribute("innerText");
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        LOGGER.error("This element can't get some text key!");
    }

    @Override
    public void submit() {
        LOGGER.error("This element can't be submit!");
    }

}
