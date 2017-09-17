package com.epam.lab.seleniumframework.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Letters
{
    private List<Letter> letters;

    public List<Letter> getLetters() {
        return letters;
    }

    @XmlElement
    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }
}
