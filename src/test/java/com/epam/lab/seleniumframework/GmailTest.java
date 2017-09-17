package com.epam.lab.seleniumframework;

import com.epam.lab.seleniumframework.businessobjects.GmailHomeBO;
import com.epam.lab.seleniumframework.businessobjects.GmailLoginBO;
import com.epam.lab.seleniumframework.models.Letter;
import com.epam.lab.seleniumframework.models.User;
import com.epam.lab.seleniumframework.utils.DataUtils;
import com.epam.lab.seleniumframework.utils.WebDriverUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class GmailTest {

    private static final Logger LOGGER = Logger.getLogger(GmailTest.class);

    private GmailLoginBO gmailLoginBO;
    private GmailHomeBO gmailHomeBO;

    @BeforeMethod
    public void setUp() throws Exception {
        gmailLoginBO = new GmailLoginBO();
        gmailHomeBO = new GmailHomeBO();
    }

    @AfterClass
    public void tearDown() throws Exception {
         WebDriverUtils.quit();
    }

    @Test(dataProvider = "testData", threadPoolSize = 2)
    public void testGmailFunctionality(User user, Letter letter) {
        gmailLoginBO.signIn(user);
        Assert.assertTrue(gmailHomeBO.isSignIn());
        Assert.assertTrue(gmailHomeBO.isLetterSavedInDraft(letter));
        Assert.assertTrue(gmailHomeBO.isLetterSent(letter));
    }

    @DataProvider(name = "testData", parallel = true)
    public Object[][] getTestData() throws Exception {
        List<User> userList = DataUtils.getUsersFromXML().getUsers();
        List<Letter> letterList = DataUtils.getLettersFromXML().getLetters();
        int rowCount = userList.size();
        int columnCount = 2;
        Object[][] testData = new Object[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            testData[i][0] = userList.get(i);
            testData[i][1] = letterList.get(i);
        }
        return testData;
    }
}