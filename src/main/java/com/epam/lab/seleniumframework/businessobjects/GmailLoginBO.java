package com.epam.lab.seleniumframework.businessobjects;


import com.epam.lab.seleniumframework.models.User;
import com.epam.lab.seleniumframework.pageobjects.GmailHomePage;
import com.epam.lab.seleniumframework.pageobjects.GmailLoginPage;

public class GmailLoginBO {

    private GmailLoginPage gmailLoginPage;
    public boolean isSignIn(User user) {

        gmailLoginPage = new GmailLoginPage();

        gmailLoginPage.typeLoginAndSubmit(user.getUserEmail());

        GmailHomePage gmailHomePage = gmailLoginPage.typePasswordAndSubmit(user.getUserPassword());

        return gmailHomePage.isLoggedIn();
    }
}
