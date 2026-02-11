package com.ae.tests.ui;

import com.ae.utils.ScreenshotUtil;
import com.ae.utils.DriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC2_LoginValid {

    @Description("UI TC02: Login with valid credentials and verify user is logged in.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testLoginValid() {

        WebDriver driver = DriverManager.getDriver();

        driver.get("https://automationexercise.com/login");

        // ⚠️ USE REAL REGISTERED ACCOUNT
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("YOUR_EMAIL_HERE");
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("YOUR_PASSWORD_HERE");
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        String page = driver.getPageSource().toLowerCase();

        Assert.assertTrue(
                page.contains("logout") ||
                        driver.getCurrentUrl().toLowerCase().contains("account"),
                "Expected user to be logged in."
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
        }
        DriverManager.quitDriver();
    }
}
