package com.epam.lab.seleniumframework.controls.decorataionfactory;

import com.epam.lab.seleniumframework.controls.Element;
import org.openqa.selenium.WebElement;

public class WrapperFactory {

    public static Element createInstance(Class<Element> clazz, WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class). newInstance(element);
        } catch (Exception e) {
            throw new AssertionError( "WebElement can't be represented as " + clazz );
        }
    }
}
