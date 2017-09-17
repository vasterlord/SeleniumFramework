package com.epam.lab.seleniumframework;

import com.epam.lab.seleniumframework.businessobjects.GmailHomeBO;
import com.epam.lab.seleniumframework.businessobjects.GmailLoginBO;
import com.epam.lab.seleniumframework.models.Letter;
import com.epam.lab.seleniumframework.models.User;
import com.epam.lab.seleniumframework.utils.DataUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GmailTest {

    private GmailLoginBO gmailLoginBO;
    private GmailHomeBO gmailHomeBO;
    private List<User> userList;
    private List<Letter> letterList;

    @BeforeMethod
    public void setUp() throws Exception {
        gmailLoginBO = new GmailLoginBO();
        gmailHomeBO = new GmailHomeBO();
        userList = DataUtils.getUsersFromXML().getUsers();
        letterList = DataUtils.getLettersFromXML().getLetters();
    }

    @AfterClass
    public void tearDown() throws Exception {
         //WebDriverUtils.close();
    }

    @Test
    public void testGmailFunctionality() {
        User currentUser = userList.get(0);
        Assert.assertTrue(gmailLoginBO.isSignIn(currentUser));
        Letter currentLetter = letterList.get(0);
        Assert.assertTrue(gmailHomeBO.isLetterSavedInDraft(currentLetter));
        Assert.assertTrue(gmailHomeBO.isLetterSent(currentLetter));
    }

}