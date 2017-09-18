package com.epam.lab.seleniumframework.controls.decorataionfactory;

import com.epam.lab.seleniumframework.controls.ElementDecorator;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.List;

public class CustomFieldDecorator extends DefaultFieldDecorator {

    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<ElementDecorator> decoratableClass = decoratableClass(field);
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }

            if (List.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass);
            }

            return createElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }

    @SuppressWarnings("unchecked")
    private Class<ElementDecorator> decoratableClass(Field field) {

        Class<?> clazz = field.getType();

        if (List.class.isAssignableFrom(clazz)) {

            if (field.getAnnotation(FindBy.class) == null && field.getAnnotation(FindBys.class) == null) {
                return null;
            }
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            clazz = (Class<?>) ((ParameterizedType) genericType).
                    getActualTypeArguments()[0];
        }

        if (ElementDecorator.class.isAssignableFrom(clazz)) {
            return (Class<ElementDecorator>) clazz;
        } else {
            return null;
        }
    }

    protected ElementDecorator createElement(ClassLoader loader, ElementLocator locator, Class<ElementDecorator> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy);
    }

    @SuppressWarnings("unchecked")
    protected List<ElementDecorator> createList(ClassLoader loader, ElementLocator locator, Class<ElementDecorator> clazz) {
        InvocationHandler handler = new LocatingCustomElementListHandler(locator, clazz);
        List<ElementDecorator> elements = (List<ElementDecorator>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return elements;
    }

}
