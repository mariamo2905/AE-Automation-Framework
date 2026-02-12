package com.ae.tests.ui;

import com.ae.utils.ScreenshotUtil;
import com.ae.utils.DriverManager;
import com.ae.utils.WaitUtils;
import com.ae.utils.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC2_LoginValid {

    @Description("UI TC02: Login with valid credentials and verify user is logged in.")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dependsOnMethods = "com.ae.tests.ui.TC1_RegisterUser.testRegisterUser")
    public void testLoginValid() {

        WebDriver driver = DriverManager.getDriver();
        driver.get("https://automationexercise.com/login");

        // ✅ Get email/password safely
        String email = TestData.getEmail();
        String password = TestData.getPassword();

        if (email == null || password == null) {
            throw new RuntimeException("TestData credentials are not set! Cannot login.");
        }

        // Enter login credentials
        WebElement emailInput = WaitUtils.waitForVisible(driver,
                By.xpath("//input[@data-qa='login-email']"));
        emailInput.sendKeys(email);

        WebElement passwordInput = WaitUtils.waitForVisible(driver,
                By.xpath("//input[@data-qa='login-password']"));
        passwordInput.sendKeys(password);

        // Click Login button
        WebElement loginButton = WaitUtils.waitForClickable(driver,
                By.xpath("//button[@data-qa='login-button']"));
        loginButton.click();

        // Wait for Logout button to verify login
        WebElement logoutBtn = WaitUtils.waitForClickable(driver,
                By.xpath("//a[normalize-space()='Logout']"));

        Assert.assertTrue(logoutBtn.isDisplayed(), "Expected user to be logged in.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
        }
        DriverManager.quitDriver();
    }
}
