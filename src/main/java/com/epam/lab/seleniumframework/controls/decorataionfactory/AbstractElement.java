package com.epam.lab.seleniumframework.controls.decorataionfactory;

import org.openqa.selenium.*;
import org.openqa.selenium.internal.WrapsElement;

import java.util.List;

public class AbstractElement implements WebElement, WrapsElement {

    private final WebElement webElement;

    public AbstractElement(WebElement webElement) {
        this.webElement = webElement;
    }

    @Override
    public void click() {
        this.webElement.click();
    }

    @Override
    public void submit() {
        this.webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        this.webElement.sendKeys(charSequences);
    }

    @Override
    public void clear() {
        this.webElement.clear();
    }

    @Override
    public String getTagName() {
        return this.webElement.getTagName();
    }

    @Override
    public String getAttribute(String s) {
        return this.webElement.getAttribute(s);
    }

    @Override
    public boolean isSelected() {
        return this.webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return this.webElement.isEnabled();
    }

    @Override
    public String getText() {
        return this.webElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return this.webElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return this.webElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return this.webElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return this.webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return this.webElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return this.webElement.getRect();
    }


    @Override
    public String getCssValue(String s) {
        return this.webElement.getCssValue(s);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return this.webElement.getScreenshotAs(outputType);
    }

    @Override
    public WebElement getWrappedElement() {
        return this.webElement;
    }
}