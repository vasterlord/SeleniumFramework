package com.epam.lab.seleniumframework.pageobjects;

import com.epam.lab.seleniumframework.controls.decorationutils.TextInput;
import com.epam.lab.seleniumframework.controls.decorationutils.TextSection;
import com.epam.lab.seleniumframework.utils.WebDriverUtils;
import org.openqa.selenium.support.FindBy;

public class GmailLoginPage extends PageObject {

    @FindBy(xpath = "//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")
    private TextSection signInElement;

    @FindBy(xpath = "//input[normalize-space(@id)='identifierId']")
    private TextInput inputEmailElement;

    @FindBy(xpath = "//content[@class='CwaK9']/span[1]")
    private TextSection nextClickElement;

    @FindBy(xpath = "//div[normalize-space(@class)='Xb9hP']/input[normalize-space(@type)='password']")
    private TextInput passwordElement;

    public GmailLoginPage() {
        WebDriverUtils.gotToURL(configurationProperties.getStartURL());
        WebDriverUtils.gotToURL(signInElement.getAttribute("href"));
    }

    public void typeLoginAndSubmit(String email) {
        inputEmailElement.sendKeys(email);
        inputEmailElement.isVisible();
        waitPresenceOfElement("//content[@class='CwaK9']/span[1]");
        nextClickElement.click();
    }

    public void typePasswordAndSubmit(String password) {
        waitPresenceOfElement("//div[normalize-space(@class)='Xb9hP']/input[normalize-space(@type)='password']");
        passwordElement.sendKeys(password);
        waitPresenceOfElement("//content[@class='CwaK9']/span[1]");
        nextClickElement.click();
    }
}
