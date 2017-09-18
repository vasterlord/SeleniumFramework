package com.epam.lab.seleniumframework.controls;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Button extends Element {

    private static final Logger LOGGER = Logger.getLogger(Button.class);

    public Button(WebElement webElement) {
        super(webElement);
    }

    public boolean isVisible() {
        return this.webElement.getCssValue("visibility").equalsIgnoreCase("visible");
    }

    public boolean isFullEnabled() {
        return this.webElement.isEnabled() && this.webElement.isDisplayed() && this.isVisible();
    }

    public void click() {
        if (isFullEnabled()) {
            this.webElement.click();
        } else {
            LOGGER.error("This element is not clickable!");
        }
    }

    public void clickAndHold(WebDriver webDriver) {
        if (isFullEnabled()) {
            new Actions(webDriver).clickAndHold(this.webElement).release().perform();
        } else {
            LOGGER.error("This element is not clickable!");
        }
    }

    public String getText() {
        return this.webElement.getAttribute("innerText");
    }


}
