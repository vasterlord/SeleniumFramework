package com.epam.lab.seleniumframework.pageobjects;

import com.epam.lab.seleniumframework.controls.Button;
import com.epam.lab.seleniumframework.controls.TextInput;
import org.openqa.selenium.support.FindBy;

public class GmailLoginPage extends PageObject {

    @FindBy(xpath = "//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")
    private Button signInElement;

    @FindBy(xpath = "//input[normalize-space(@id)='identifierId']")
    private TextInput inputEmailElement;

    @FindBy(xpath = "//content[@class='CwaK9']/span[1]")
    private Button nextClickElement;

    @FindBy(xpath = "//div[normalize-space(@class)='Xb9hP']/input[normalize-space(@type)='password']")
    private TextInput passwordElement;

    public GmailLoginPage() {
        this.webDriver.navigate().to(configurationProperties.getStartURL());
        this.webDriver.navigate().to(signInElement.getAttribute("href"));
    }

    public void typeLoginAndSubmit(String email) {
        inputEmailElement.sendKeys(email);
        waitPresenceOfElement("//content[@class='CwaK9']/span[1]");
        nextClickElement.clickAndHold(this.webDriver);
    }

    public void typePasswordAndSubmit(String password) {
        waitPresenceOfElement("//div[normalize-space(@class)='Xb9hP']/input[normalize-space(@type)='password']");
        passwordElement.sendKeys(password);
        waitPresenceOfElement("//content[@class='CwaK9']/span[1]");
        nextClickElement.clickAndHold(this.webDriver);
    }
}
