package com.epam.lab.seleniumframework.controls;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class CheckBox extends ElementDecorator {

    private static final Logger LOGGER = Logger.getLogger(CheckBox.class);

    public CheckBox(WebElement webElement) {
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

    public void setChecked(boolean value) {
        if (value != isChecked()) {
            this.getWrappedElement().click();
        }
    }

    public boolean isChecked() {
        return this.getWrappedElement().isSelected();
    }

    @Override
    public String getText() {
        if(isChecked()) {
            return "checked";
        } else {
            return "unchecked";
        }
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
