package com.epam.lab.seleniumframework.pageobjects;

import com.epam.lab.seleniumframework.controls.Button;
import com.epam.lab.seleniumframework.controls.Element;
import com.epam.lab.seleniumframework.controls.TextInput;
import com.epam.lab.seleniumframework.utils.WebDriverUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;


public class GmailHomePage extends PageObject {
    private static final Logger LOGGER = Logger.getLogger(GmailHomePage.class);

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-KE L3']")
    private Button writeActionElement;

    @FindBy(xpath = "//textarea[@class='vO']")
    private TextInput receiverElement;

    @FindBy(xpath = "//input[@class='aoT']")
    private TextInput subjectElement;

    @FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
    private TextInput contentLetterElement;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")
    private Button sendLetterActionElement;

    @FindBy(xpath = "//table[@class='cf Ht']//img[@class='Ha']")
    private Button closeLetterActionElement;

    @FindBy(xpath = "//div[contains(@jscontroller,'DUNnfe')]//div[contains(@role,'navigation')]//a[contains (@href,'https://mail.google.com/mail/u/0/#drafts')]")
    private Button draftLinkElement;

    @FindBy(xpath = "//div[contains(@jscontroller,'DUNnfe')]//div[contains(@role,'navigation')]//a[contains (@href,'https://mail.google.com/mail/u/0/#sent')]")
    private Button sentLinkElement;

    @FindBys({
            @FindBy(xpath = "//table[@class='F cf zt']/tbody/tr[1]/td[6]/div[1]/div[1]/div[1]/span")
    })
    private List<Button> letterActionOpenElement;

    public GmailHomePage() {
    }

    public void writeLetter(String receiverText, String subjectText, String contentLetterText) {
        waitPresenceOfElement("//div[@class='T-I J-J5-Ji T-I-KE L3']");
        writeActionElement.click();
        receiverElement.sendKeys(receiverText);
        subjectElement.sendKeys(subjectText);
        contentLetterElement.sendKeys(contentLetterText);
        closeLetterActionElement.click();
    }

    public boolean isLoggedIn() {
        waitPresenceOfElement("//div[@class='T-I J-J5-Ji T-I-KE L3']");
        if (writeActionElement.isFullEnabled()) {
            LOGGER.info("User successfully logged in");
            return true;
        } else {
            return false;
        }
    }

    public void getLetterFromDraft() {
        getLettersFromSection(draftLinkElement);
    }

    public void getLetterFromSent() {
        getLettersFromSection(sentLinkElement);
    }

    public boolean isSavedInDraft(String subjectText, String contentLetterText) {
        return isSavedInSection(subjectText, contentLetterText, draftLinkElement);
    }

    public boolean isSavedInSent(String subjectText, String contentLetterText) {
        return isSavedInSection(subjectText, contentLetterText, sentLinkElement);
    }

    private boolean isSavedInSection(String subjectText, String contentLetterText, Element lettersSectionWebElement) {
        List<Element> letterDataList = letterActionOpenElement.stream().skip(2).collect(Collectors.toList());
        if (letterDataList.get(0).getText().equalsIgnoreCase(subjectText) && letterDataList.get(1).getText().toLowerCase().contains(contentLetterText.toLowerCase())) {
            LOGGER.info(String.format("Message successfully saved in %s", lettersSectionWebElement.getText()));
            return true;
        } else {
            LOGGER.info(String.format("Message didn't save in %s", lettersSectionWebElement.getText()));
            return false;
        }
    }

    public void sendLetterFromDraft() {
        letterActionOpenElement.stream().skip(2).findFirst().get().clickAndHold(this.webDriver);
        waitPresenceOfElement("//div[@class='T-I J-J5-Ji aoO T-I-atl L3']");
        sendLetterActionElement.click();
        LOGGER.info("Message from draft successfully sent");
    }

    private void getLettersFromSection(Element webElement) {
        webElement.click();
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), TIME_OUT_IN_SECONDS).until(ExpectedConditions.urlToBe(webElement.getAttribute("href")));
    }

}
