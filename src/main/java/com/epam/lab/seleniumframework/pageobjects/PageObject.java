package com.epam.lab.seleniumframework.pageobjects;

import com.epam.lab.seleniumframework.controls.decorataionfactory.CustomFieldDecorator;
import com.epam.lab.seleniumframework.utils.ConfigurationProperties;
import com.epam.lab.seleniumframework.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

    private static final int TIME_OUT_IN_SECONDS = 30;
    protected ConfigurationProperties configurationProperties;

    public PageObject(WebDriver webDriver) {
        configurationProperties = new ConfigurationProperties();
        PageFactory.initElements(new CustomFieldDecorator(webDriver), this);
    }

    public PageObject() {
        this(WebDriverUtils.getWebDriverThreadLocal());
    }

    protected void waitPresenceOfElement(String locator) {
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), TIME_OUT_IN_SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }
}
