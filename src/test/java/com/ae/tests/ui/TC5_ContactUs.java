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

public class TC5_ContactUs {

    @Description("UI TC05: Submit Contact Us form and verify success message.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testContactUs() {

        WebDriver driver = DriverManager.getDriver();

        driver.get("https://automationexercise.com/contact_us");

        driver.findElement(By.name("name")).sendKeys("Test User");
        driver.findElement(By.name("email")).sendKeys("test@example.com");
        driver.findElement(By.name("subject")).sendKeys("Test Subject");
        driver.findElement(By.name("message")).sendKeys("This is a test message.");

        driver.findElement(By.name("submit")).click();

        String page = driver.getPageSource().toLowerCase();

        Assert.assertTrue(
                page.contains("success"),
                "Expected success message after submitting contact form."
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