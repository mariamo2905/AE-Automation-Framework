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

public class TC8_AddToCart {

 @Description("UI TC08: Add first product to cart and verify it appears in cart.")
 @Severity(SeverityLevel.CRITICAL)
 @Test
 public void testAddToCart() {
  WebDriver driver = DriverManager.getDriver();
  driver.get("https://automationexercise.com");

  WebElement firstProduct = driver.findElement(By.cssSelector(".features_items .product-image-wrapper"));
  WaitUtils.waitForClickable(driver, firstProduct).click();

  WebElement addToCartBtn = driver.findElement(By.cssSelector(".cart"));
  WaitUtils.waitForClickable(driver, addToCartBtn).click();

  WebElement cartModal = driver.findElement(By.id("cartModal"));
  WaitUtils.waitForVisible(driver, cartModal);

  Assert.assertTrue(cartModal.isDisplayed(), "Expected cart modal to appear after adding product");
 }

 @AfterMethod(alwaysRun = true)
 public void tearDown(ITestResult result) {
  if (!result.isSuccess()) {
   ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
  }
  DriverManager.quitDriver();
 }
}
