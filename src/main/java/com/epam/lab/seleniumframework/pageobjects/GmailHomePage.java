package com.epam.lab.seleniumframework.pageobjects;

import com.epam.lab.seleniumframework.controls.TextInput;
import com.epam.lab.seleniumframework.utils.WebDriverUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class GmailHomePage extends PageObject {
    private static final Logger LOGGER = Logger.getLogger(GmailHomePage.class);

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-KE L3']")
    private TextInput writeActionElement;

    @FindBy(xpath = "//textarea[@class='vO']")
    private TextInput receiverElement;

    @FindBy(xpath = "//input[@class='aoT']")
    private TextInput subjectElement;

    @FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
    private TextInput contentLetterElement;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")
    private TextInput sendLetterActionElement;

    @FindBy(xpath = "//table[@class='cf Ht']//img[@class='Ha']")
    private TextInput closeLetterActionElement;

    @FindBy(xpath = "//div[@class='TK']/div[4]/div[1]/div[1]/div[2]/span[1]/a[1]")
    private TextInput draftLinkElement;

    @FindBy(xpath = "//div[@class='TK']/div[3]/div[1]/div[1]/div[2]/span[1]/a[1]")
    private TextInput sentLinkElement;

    @FindBys({
            @FindBy(xpath = "//table[@class='F cf zt']/tbody/tr[1]/td[6]/div[1]/div[1]/div[1]/span")
    })
    private List<WebElement> letterActionOpenElement;

    public void writeLetter(String receiverText, String subjectText, String contentLetterText) {
        waitElementToBeLoaded("//div[@class='T-I J-J5-Ji T-I-KE L3']");
        writeActionElement.click();
        receiverElement.sendKeys(receiverText);
        subjectElement.sendKeys(subjectText);
        contentLetterElement.sendKeys(contentLetterText);
        closeLetterActionElement.click();
    }

    public boolean isLoggedIn() {
        waitElementToBeLoaded("//div[@class='T-I J-J5-Ji T-I-KE L3']");
        if (writeActionElement.isVisible()) {
            LOGGER.info("User successfully logged in");
            return true;
        } else {
            return false;
        }
    }

    public void getLetterFromDraft(){
        getLettersFromSection(draftLinkElement);
    }

    public void getLetterFromSent(){
        getLettersFromSection(sentLinkElement);
    }

    public boolean isSavedInDraft(String subjectText, String contentLetterText) {
        return isSavedInSection(subjectText, contentLetterText, draftLinkElement);
    }

    public boolean isSavedInSent(String subjectText, String contentLetterText) {
        return isSavedInSection(subjectText, contentLetterText, sentLinkElement);
    }

    private boolean isSavedInSection(String subjectText, String contentLetterText, WebElement letersSectionWebElement) {
        List<WebElement> letterDraftInfo = letterActionOpenElement.stream().filter(item -> item.getText().length() > 0).collect(Collectors.toList());
        if (letterDraftInfo.get(0).getText().equalsIgnoreCase(subjectText) && letterDraftInfo.get(1).getText().contains(contentLetterText)) {
            LOGGER.info(String.format("Message successfully saved in %s", letersSectionWebElement.getText()));
            return true;
        } else {
            LOGGER.info(String.format("Message didn't save in %s", letersSectionWebElement.getText()));
            return false;
        }
    }

    public void sendLetterFromDraft() {
        letterActionOpenElement.stream().filter(item -> item.getText().length() > 0).findFirst().get().click();
        sendLetterActionElement.click();
        LOGGER.info("Message from draft successfully sent");
    }

    private void getLettersFromSection(WebElement webElement) {
        webElement.click();
        new WebDriverWait(WebDriverUtils.getWebDriverThreadLocal(), 10).until(ExpectedConditions.urlToBe(webElement.getAttribute("href")));
    }


}
