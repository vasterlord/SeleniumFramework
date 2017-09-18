package com.epam.lab.seleniumframework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationProperties {

    private Properties properties;

    private static final String START_URL = "startURL";
    private static final String USERS_XML_DATA_KEY = "users";
    private static final String LETTERS_XML_DATA_KEY = "messages";
    private static final String WEB_DRIVER_NAME_KEY = "webDriverName";
    private static final String WEB_DRIVER_PATH_KEY = "webDriverPath";

    public ConfigurationProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src/main/resources/configuration.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStartURL () {
        return properties.getProperty(START_URL);
    }

    public String getUsersXMLData() {
        return properties.getProperty(USERS_XML_DATA_KEY);
    }

    public String getLettersXMLData() {
        return properties.getProperty(LETTERS_XML_DATA_KEY);
    }

    public String getWebDriverName() {
        return properties.getProperty(WEB_DRIVER_NAME_KEY);
    }

    public String getWebDriverPath() {
        return properties.getProperty(WEB_DRIVER_PATH_KEY);
    }

}

