package com.ae.tests.ui;

import com.ae.utils.ScreenshotUtil;
import com.ae.utils.DriverManager;
import com.ae.utils.WaitUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC9_CheckoutRegister {

 @Description("UI TC09: Go to checkout, register a new user, and verify checkout page.")
 @Severity(SeverityLevel.CRITICAL)
 @Test
 public void testCheckoutRegister() {
  WebDriver driver = DriverManager.getDriver();
  driver.get("https://automationexercise.com");

  WebElement cartBtn = driver.findElement(By.xpath("//a[@href='/view_cart']"));
  WaitUtils.waitForClickable(driver, cartBtn).click();

  WebElement checkoutBtn = driver.findElement(By.xpath("//a[text()='Proceed To Checkout']"));
  WaitUtils.waitForClickable(driver, checkoutBtn).click();

  WebElement nameInput = driver.findElement(By.name("name"));
  WaitUtils.waitForVisible(driver, nameInput).sendKeys("Test User");

  WebElement emailInput = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));
  emailInput.sendKeys("testuser" + System.currentTimeMillis() + "@example.com");

  WebElement signupBtn = driver.findElement(By.xpath("//button[@data-qa='signup-button']"));
  WaitUtils.waitForClickable(driver, signupBtn).click();

  WebElement createAccountBtn = driver.findElement(By.xpath("//button[@data-qa='create-account']"));
  WaitUtils.waitForVisible(driver, createAccountBtn);

  Assert.assertTrue(createAccountBtn.isDisplayed(), "Expected 'Create Account' button to appear during checkout registration");
 }

 @AfterMethod(alwaysRun = true)
 public void tearDown(ITestResult result) {
  if (!result.isSuccess()) {
   ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
  }
  DriverManager.quitDriver();
 }
}
