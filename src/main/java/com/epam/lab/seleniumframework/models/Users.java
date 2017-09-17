package com.epam.lab.seleniumframework.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Users {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    @XmlElement
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
