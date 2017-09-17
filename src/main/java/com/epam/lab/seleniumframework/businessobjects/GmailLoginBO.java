package com.epam.lab.seleniumframework.businessobjects;


import com.epam.lab.seleniumframework.models.User;
import com.epam.lab.seleniumframework.pageobjects.GmailLoginPage;

public class GmailLoginBO {

    private GmailLoginPage gmailLoginPage;

    public void signIn(User user) {
        gmailLoginPage = new GmailLoginPage();
        gmailLoginPage.typeLoginAndSubmit(user.getUserEmail());
        gmailLoginPage.typePasswordAndSubmit(user.getUserPassword());
    }
}
