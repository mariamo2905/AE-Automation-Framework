package com.ae.tests.ui;

import com.ae.utils.ScreenshotUtil;
import com.ae.utils.DriverManager;
import com.ae.utils.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class TC1_RegisterUser {

    @Description("UI TC01: Register new user and verify account creation.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testRegisterUser() {

        WebDriver driver = DriverManager.getDriver();
        driver.get("https://automationexercise.com/login");

        // Generate unique email & password
        String uniqueEmail = "test" + UUID.randomUUID().toString().substring(0, 5) + "@example.com";
        String password = "Password123";

        // ✅ Store correctly
        TestData.setEmail(uniqueEmail);
        TestData.setPassword(password);

        // Fill registration form
        driver.findElement(By.name("name")).sendKeys("Test User");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(uniqueEmail);
        driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

        // Required fields
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("days")).sendKeys("10");
        driver.findElement(By.id("months")).sendKeys("May");
        driver.findElement(By.id("years")).sendKeys("1995");

        driver.findElement(By.id("first_name")).sendKeys("Test");
        driver.findElement(By.id("last_name")).sendKeys("User");
        driver.findElement(By.id("address1")).sendKeys("Test Street");
        driver.findElement(By.id("country")).sendKeys("Canada");
        driver.findElement(By.id("state")).sendKeys("State");
        driver.findElement(By.id("city")).sendKeys("City");
        driver.findElement(By.id("zipcode")).sendKeys("12345");
        driver.findElement(By.id("mobile_number")).sendKeys("1234567890");

        driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();

        // Verify account created
        Assert.assertTrue(
                driver.getPageSource().toLowerCase().contains("account created"),
                "Expected account creation success message."
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
