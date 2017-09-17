package com.epam.lab.seleniumframework.businessobjects;

import com.epam.lab.seleniumframework.models.Letter;
import com.epam.lab.seleniumframework.pageobjects.GmailHomePage;

public class GmailHomeBO {

    private GmailHomePage gmailHomePage;

    public GmailHomeBO() {
        gmailHomePage = new GmailHomePage();
    }

    public boolean isSignIn() {
        return gmailHomePage.isLoggedIn();
    }

    public boolean isLetterSavedInDraft(Letter letter) {
        gmailHomePage.writeLetter(letter.getReceiverEmail(), letter.getSubjectText(), letter.getContentLetter());
        gmailHomePage.getLetterFromDraft();
        return gmailHomePage.isSavedInDraft(letter.getSubjectText(), letter.getContentLetter());
    }

    public boolean isLetterSent(Letter letter) {
        gmailHomePage.sendLetterFromDraft();
        gmailHomePage.getLetterFromSent();
        return gmailHomePage.isSavedInSent(letter.getSubjectText(), letter.getContentLetter());
    }

}
