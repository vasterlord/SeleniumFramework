package com.epam.lab.seleniumframework.controls;

import org.openqa.selenium.WebElement;

public abstract class Element {

    protected WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public String getAttribute(String attribute) {
        return webElement.getAttribute(attribute);
    }

    public abstract void click();

    public abstract String getText();
}