package com.epam.lab.seleniumframework.pageobjects;

import com.epam.lab.seleniumframework.controls.decorationutils.CustomFieldDecorator;
import com.epam.lab.seleniumframework.utils.ConfigurationProperties;
import com.epam.lab.seleniumframework.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

    protected ConfigurationProperties configurationProperties;

    public PageObject(WebDriver webDriver) {
        configurationProperties = new ConfigurationProperties();
        PageFactory.initElements(new CustomFieldDecorator(webDriver), this);
    }

    public PageObject() {
        this(WebDriverUtils.getWebDriverThreadLocal());
    }

    protected void waitElementToBeLoaded(String locator) {
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), 2000).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), 2000).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), 2000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }
}
