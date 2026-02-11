package com.ae.tests.ui;

import com.ae.utils.DriverManager;
import com.ae.utils.WaitUtils;
import com.ae.utils.ScreenshotUtil;
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

public class TC10_CheckoutBeforeRegister {

 @Description("UI TC10: Attempt checkout without registering and verify redirect to login")
 @Severity(SeverityLevel.CRITICAL)
 @Test
 public void testCheckoutBeforeRegister() {
  WebDriver driver = DriverManager.getDriver();
  driver.get("https://automationexercise.com");

  // Add first product to cart
  WebElement firstProduct = driver.findElement(By.xpath("(//a[text()='Add to cart'])[1]"));
  WaitUtils.waitForClickable(driver, firstProduct).click();

  WebElement viewCart = driver.findElement(By.xpath("//u[text()='View Cart']"));
  WaitUtils.waitForClickable(driver, viewCart).click();

  // Click 'Proceed To Checkout'
  WebElement checkout = driver.findElement(By.xpath("//a[text()='Proceed To Checkout']"));
  WaitUtils.waitForClickable(driver, checkout).click();

  // Verify redirected to login/signup page
  WebElement loginHeader = driver.findElement(By.xpath("//h2[text()='Login to your account']"));
  Assert.assertTrue(WaitUtils.waitForVisible(driver, loginHeader).isDisplayed(),
          "User should be redirected to login page if not registered before checkout");
 }

 @AfterMethod(alwaysRun = true)
 public void tearDown(ITestResult result) {
  if (!result.isSuccess()) {
   ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
  }
  DriverManager.quitDriver();
 }
}
