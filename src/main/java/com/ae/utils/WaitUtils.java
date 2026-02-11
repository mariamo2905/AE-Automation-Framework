package com.ae.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    // Wait until element is visible
    public static WebElement waitForVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    // Wait until element is clickable
    public static WebElement waitForClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}
