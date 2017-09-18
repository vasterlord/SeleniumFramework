package com.epam.lab.seleniumframework.controls.decorataionfactory;

import com.epam.lab.seleniumframework.controls.ElementDecorator;
import org.openqa.selenium.WebElement;

public class WrapperFactory {

    public static ElementDecorator createInstance(Class<ElementDecorator> clazz, WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class). newInstance(element);
        } catch (Exception e) {
            throw new AssertionError( "WebElement can't be represented as " + clazz );
        }
    }
}
