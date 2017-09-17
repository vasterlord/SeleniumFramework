package com.epam.lab.seleniumframework.models;


import javax.xml.bind.annotation.XmlElement;


public class Letter {
    private String receiverEmail;
    private String subjectText;
    private String contentLetter;

    public String getReceiverEmail() {
        return receiverEmail;
    }

    @XmlElement
    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getSubjectText() {
        return subjectText;
    }

    @XmlElement
    public void setSubjectText(String subjectText) {
        this.subjectText = subjectText;
    }

    public String getContentLetter() {
        return contentLetter;
    }

    @XmlElement
    public void setContentLetter(String contentLetter) {
        this.contentLetter = contentLetter;
    }
}
