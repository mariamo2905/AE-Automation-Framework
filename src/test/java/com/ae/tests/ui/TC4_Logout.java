package com.ae.tests.ui;

import com.ae.utils.DriverManager;
import com.ae.utils.ScreenshotUtil;
import com.ae.utils.TestData;
import com.ae.utils.WaitUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC4_Logout {

    @Test(dependsOnMethods = "com.ae.tests.ui.TC1_RegisterUser.testRegisterUser")
    @Description("UI TC04: Logout and verify user is redirected to login page.")
    @Severity(SeverityLevel.CRITICAL)
    public void testLogout() {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://automationexercise.com/login");

        removeAds(driver);

        // Get registered credentials safely
        String email = TestData.getEmail();
        String password = TestData.getPassword();

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new RuntimeException("TestData credentials are not set! Cannot login.");
        }

        // Login
        WebElement emailInput = WaitUtils.waitForVisible(driver,
                By.xpath("//input[@data-qa='login-email']"));
        emailInput.clear();
        emailInput.sendKeys(email);

        WebElement passInput = WaitUtils.waitForVisible(driver,
                By.xpath("//input[@data-qa='login-password']"));
        passInput.clear();
        passInput.sendKeys(password);

        WaitUtils.waitAndClickJS(driver, By.xpath("//button[@data-qa='login-button']"));

        // Wait for page to load and Logout button to appear
        removeAds(driver);
        WebElement logoutBtn = WaitUtils.waitForVisible(driver,
                By.xpath("//a[normalize-space()='Logout']"));

        Assert.assertTrue(logoutBtn.isDisplayed(), "Logout button should be visible after login");
        WaitUtils.clickJS(driver, logoutBtn);

        // Verify redirect to login page
        WebElement loginBtn = WaitUtils.waitForVisible(driver,
                By.xpath("//button[@data-qa='login-button']"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"),
                "Expected to be redirected to login page after logout");
    }

    /**
     * Remove potential Google ad iframes to prevent click interception.
     */
    private void removeAds(WebDriver driver) {
        try {
            Thread.sleep(500); // allow iframes to load
            for (WebElement iframe : driver.findElements(By.xpath("//iframe[contains(@id,'aswift')]"))) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", iframe);
            }
        } catch (InterruptedException | StaleElementReferenceException ignored) {}
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
        }
        DriverManager.quitDriver();
    }
}
