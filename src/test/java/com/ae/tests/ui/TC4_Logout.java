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

public class TC4_Logout {

    @Description("UI TC04: Logout and verify user is redirected to login page.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testLogout() {

        WebDriver driver = DriverManager.getDriver();

        driver.get("https://automationexercise.com/login");

        // ⚠️ Use REAL registered account
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("YOUR_EMAIL_HERE");
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("YOUR_PASSWORD_HERE");
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        // Click Logout
        driver.findElement(By.xpath("//a[@href='/logout']")).click();

        // Assertion
        Assert.assertTrue(
                driver.getCurrentUrl().contains("login"),
                "Expected to be redirected to login page after logout."
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
