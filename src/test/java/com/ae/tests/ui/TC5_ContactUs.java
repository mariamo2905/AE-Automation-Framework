package com.ae.tests.ui;

import com.ae.utils.DriverManager;
import com.ae.utils.ScreenshotUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC5_ContactUs {

    @Test

    @Description("UI TC05: Submit Contact Us form and verify success message.")
    @Severity(SeverityLevel.NORMAL)
    public void testContactUs() {

        WebDriver driver = DriverManager.getDriver();

        driver.get("https://automationexercise.com/contact_us");

        // Fill form
        driver.findElement(By.name("name")).sendKeys("Test User");
        driver.findElement(By.name("email")).sendKeys("test@example.com");
        driver.findElement(By.name("subject")).sendKeys("Test Subject");
        driver.findElement(By.name("message")).sendKeys("This is a test message.");

        // Click Submit
        driver.findElement(By.name("submit")).click();

        // Wait for alert and accept it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        // Wait for success message to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".status.alert-success")
        ));

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
