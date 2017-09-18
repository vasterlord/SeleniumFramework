package com.epam.lab.seleniumframework.utils;

import com.epam.lab.seleniumframework.models.Letters;
import com.epam.lab.seleniumframework.models.Users;
import org.apache.log4j.Logger;

import javax.xml.bind.*;
import java.nio.file.Paths;

public class DataUtils {
    private static final Logger LOGGER = Logger.getLogger(DataUtils.class);

    private static ConfigurationProperties configurationProperties = new ConfigurationProperties();

    public static Users getUsersFromXML() {
        JAXBContext jaxbContext = null;
        Users users = new Users();
        try {
            jaxbContext = JAXBContext.newInstance(Users.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            users = (Users) jaxbUnmarshaller.unmarshal(Paths.get(configurationProperties.getUsersXMLData()).toFile());
            return users;
        } catch (JAXBException e) {
            e.printStackTrace();
            LOGGER.info(String.format("JAXBException : %s", e.getMessage()));
            return users;
        }
    }

    @SuppressWarnings(value = "unchecked")
    public static Letters getLettersFromXML() {
        JAXBContext jaxbContext;
        Letters letters = new Letters();
        try {
            jaxbContext = JAXBContext.newInstance(Letters.class);
            Unmarshaller jaxbContextUnmarshaller = jaxbContext.createUnmarshaller();
            letters = (Letters) jaxbContextUnmarshaller.unmarshal(Paths.get(configurationProperties.getLettersXMLData()).toFile());
            return letters;
        } catch (JAXBException e) {
            e.printStackTrace();
            LOGGER.info(String.format("JAXBException : %s", e.getMessage()));
            return letters;
        }
    }

}
