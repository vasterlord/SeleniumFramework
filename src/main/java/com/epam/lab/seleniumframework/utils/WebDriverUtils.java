package com.epam.lab.seleniumframework.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverUtils {

    private static final Logger LOGGER = Logger.getLogger(WebDriverUtils.class);
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getWebDriverThreadLocal() {
        ConfigurationProperties configurationProperties = new ConfigurationProperties();
        if (webDriverThreadLocal.get() != null) {
            return webDriverThreadLocal.get();
        }
        System.setProperty(configurationProperties.getWebDriverName(), configurationProperties.getWebDriverPath());
        WebDriver webDriverInstance = new ChromeDriver();
        webDriverThreadLocal.set(webDriverInstance);
        LOGGER.info("Create instance");
        webDriverThreadLocal.get().manage().window().maximize();
        webDriverThreadLocal.get().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        webDriverThreadLocal.get().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriverThreadLocal.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return webDriverThreadLocal.get();
    }

    private WebDriverUtils() {
    }

    public static void close() {
        LOGGER.info("Closing browser");
        webDriverThreadLocal.get().close();
        webDriverThreadLocal.set(null);
    }

    public static void quit() {
        LOGGER.info("Quiting browser");
        webDriverThreadLocal.get().quit();
        webDriverThreadLocal.set(null);
    }

    public static void gotToURL(String url) {
        LOGGER.info(String.format("Navigating to %s ", url));
        webDriverThreadLocal.get().navigate().to(url);
    }

}
