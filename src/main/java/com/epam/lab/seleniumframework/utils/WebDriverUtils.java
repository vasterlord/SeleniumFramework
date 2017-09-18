package com.epam.lab.seleniumframework.utils;

import com.epam.lab.seleniumframework.pageobjects.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverUtils {
    private static final Logger LOGGER = Logger.getLogger(WebDriverUtils.class);
    private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static int counter = 0;
    private static final int THREADS_COUNT_IN_ONE_TIME = 3;

    public static WebDriver getWebDriverThreadLocal() {
        ConfigurationProperties configurationProperties = new ConfigurationProperties();
        if (WEB_DRIVER_THREAD_LOCAL.get() != null) {
            return WEB_DRIVER_THREAD_LOCAL.get();
        }
        System.setProperty(configurationProperties.getWebDriverName(), configurationProperties.getWebDriverPath());
        synchronized (WEB_DRIVER_THREAD_LOCAL) {
            while (counter == THREADS_COUNT_IN_ONE_TIME) {
                WEB_DRIVER_THREAD_LOCAL.notify();
            }
            WebDriver webDriverInstance = new ChromeDriver();
            WEB_DRIVER_THREAD_LOCAL.set(webDriverInstance);
            counter++;
            WEB_DRIVER_THREAD_LOCAL.get().manage().window().maximize();
            WEB_DRIVER_THREAD_LOCAL.get().manage().timeouts().setScriptTimeout(PageObject.TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);
            WEB_DRIVER_THREAD_LOCAL.get().manage().timeouts().pageLoadTimeout(PageObject.TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);
            WEB_DRIVER_THREAD_LOCAL.get().manage().timeouts().implicitlyWait(PageObject.TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);
            return WEB_DRIVER_THREAD_LOCAL.get();
        }
    }

    private WebDriverUtils() {
    }

    public static void close() {
        LOGGER.info("Closing browser");
        WEB_DRIVER_THREAD_LOCAL.get().close();
        WEB_DRIVER_THREAD_LOCAL.set(null);
        WEB_DRIVER_THREAD_LOCAL.remove();
        counter--;
    }

    public static void quit() {
        LOGGER.info("Quiting browser");
        WEB_DRIVER_THREAD_LOCAL.get().quit();
        WEB_DRIVER_THREAD_LOCAL.set(null);
        WEB_DRIVER_THREAD_LOCAL.remove();
        counter--;
    }

}
