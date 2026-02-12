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

public class TC10_CheckoutBeforeRegister {

 @Description("UI TC10: Try to checkout without registering and verify warning message.")
 @Severity(SeverityLevel.CRITICAL)
 @Test
 public void testCheckoutBeforeRegister() {
  WebDriver driver = DriverManager.getDriver();
  driver.get("https://automationexercise.com");

  WebElement cartBtn = driver.findElement(By.xpath("//a[@href='/view_cart']"));
  WaitUtils.waitForClickable(driver, cartBtn).click();

  WebElement checkoutBtn = driver.findElement(By.xpath("//a[text()='Proceed To Checkout']"));
  WaitUtils.waitForClickable(driver, checkoutBtn).click();

  WebElement loginPrompt = driver.findElement(By.xpath("//h2[text()='Login to your account']"));
  WaitUtils.waitForVisible(driver, loginPrompt);

  Assert.assertTrue(loginPrompt.isDisplayed(), "Expected login prompt when checking out without registration");
 }

 @AfterMethod(alwaysRun = true)
 public void tearDown(ITestResult result) {
  if (!result.isSuccess()) {
   ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
  }
  DriverManager.quitDriver();
 }
}
