package com.ae.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 30;

    // Wait for visibility
    public static WebElement waitForVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static List<WebElement> waitForAllVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // JS click helper
    public static void clickJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // Wait + JS click (locator) - safer for Firefox / dynamic elements
    public static void waitAndClickJS(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));

        // Wait for presence
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        // Wait for visibility
        wait.until(ExpectedConditions.visibilityOf(el));

        // Tiny pause to let JS render (avoids timing issues)
        try { Thread.sleep(200); } catch (InterruptedException ignored) {}

        // Click via JS
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true); arguments[0].click();", el
        );
    }

    // Wait + JS click (element) - keeps old behavior
    public static void waitAndClickJS(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        clickJS(driver, element);
    }

    // Optional: Ignore overlays / iframes (for ads blocking clicks)
    public static void waitAndClickJSIgnoreOverlays(WebDriver driver, By locator) {
        // Hide iframes temporarily
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe').forEach(f => f.style.display='none');"
        );

        // Use safer JS click
        waitAndClickJS(driver, locator);
    }
}
