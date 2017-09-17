package com.epam.lab.seleniumframework.pageobjects;

import com.epam.lab.seleniumframework.controls.TextInput;
import com.epam.lab.seleniumframework.utils.WebDriverUtils;
import org.openqa.selenium.support.FindBy;

public class GmailLoginPage extends PageObject {

    @FindBy(xpath = "//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")
    private TextInput signInElement;

    @FindBy(xpath = "//input[normalize-space(@id)='identifierId']")
    private TextInput inputEmailElement;

    @FindBy(xpath = "//content[@class='CwaK9']/span[1]")
    private TextInput nextClickElement;

    @FindBy(xpath = "//div[normalize-space(@class)='Xb9hP']/input[normalize-space(@type)='password']")
    private TextInput passwordElement;

    public GmailLoginPage() {
        WebDriverUtils.gotToURL(configurationProperties.getStartURL());
        WebDriverUtils.gotToURL(signInElement.getAttribute("href"));
    }

    public void typeLoginAndSubmit(String email) {
        inputEmailElement.sendKeys(email);
        inputEmailElement.isVisible();
        waitElementToBeLoaded("//content[@class='CwaK9']/span[1]");
        nextClickElement.click();
    }

    public GmailHomePage typePasswordAndSubmit(String password) {
        waitElementToBeLoaded("//div[normalize-space(@class)='Xb9hP']/input[normalize-space(@type)='password']");
        passwordElement.sendKeys(password);
        waitElementToBeLoaded("//content[@class='CwaK9']/span[1]");
        nextClickElement.click();
        return new GmailHomePage();
    }
}
