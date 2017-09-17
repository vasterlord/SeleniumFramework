package com.epam.lab.seleniumframework.models;

import javax.xml.bind.annotation.XmlElement;

public class User {

    private String userEmail;
    private String userPassword;

    public User() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    @XmlElement
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @XmlElement
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
