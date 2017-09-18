package com.epam.lab.seleniumframework;

import com.epam.lab.seleniumframework.businessobjects.GmailHomeBO;
import com.epam.lab.seleniumframework.businessobjects.GmailLoginBO;
import com.epam.lab.seleniumframework.models.Letter;
import com.epam.lab.seleniumframework.models.User;
import com.epam.lab.seleniumframework.utils.DataUtils;
import com.epam.lab.seleniumframework.utils.WebDriverUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class GmailTest {

    @AfterMethod
    public void tearDown() throws Exception {
        WebDriverUtils.quit();
    }

    @Test(dataProvider = "testData", threadPoolSize = 3)
    public void testGmailFunctionality(User user, Letter letter) {
        GmailLoginBO gmailLoginBO = new GmailLoginBO();
        gmailLoginBO.signIn(user);
        GmailHomeBO gmailHomeBO = new GmailHomeBO();
        Assert.assertTrue(gmailHomeBO.isSignIn());
        Assert.assertTrue(gmailHomeBO.isLetterSavedInDraft(letter));
        Assert.assertTrue(gmailHomeBO.isLetterSent(letter));
    }

    @DataProvider(name = "testData", parallel = true)
    public static Object[][] getTestData() throws Exception {
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