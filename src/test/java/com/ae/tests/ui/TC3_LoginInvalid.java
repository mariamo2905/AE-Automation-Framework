package com.ae.tests.ui;

import com.ae.tests.utils.ScreenshotUtil;
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

public class TC3_LoginInvalid {

    @Description("UI TC03: Login with invalid credentials and verify error message appears.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testLoginInvalid() {

        WebDriver driver = DriverManager.getDriver();

        driver.get("https://automationexercise.com/login");

        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("wrong@example.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("wrongpassword");
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        String page = driver.getPageSource().toLowerCase();

        Assert.assertTrue(
                page.contains("incorrect") || page.contains("error"),
                "Expected error message for invalid login."
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
