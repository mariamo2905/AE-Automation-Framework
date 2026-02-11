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

public class TC8_AddToCart {

 @Description("UI TC08: Add a product to cart and verify it appears in cart")
 @Severity(SeverityLevel.CRITICAL)
 @Test
 public void testAddToCart() {
  WebDriver driver = DriverManager.getDriver();
  driver.get("https://automationexercise.com");

  // Go to Products
  WebElement productsLink = driver.findElement(By.xpath("//a[text()='Products']"));
  WaitUtils.waitForClickable(driver, productsLink).click();

  // Add first product to cart
  WebElement firstProduct = driver.findElement(By.xpath("(//a[text()='Add to cart'])[1]"));
  WaitUtils.waitForClickable(driver, firstProduct).click();

  // Click 'View Cart'
  WebElement viewCart = driver.findElement(By.xpath("//u[text()='View Cart']"));
  WaitUtils.waitForClickable(driver, viewCart).click();

  // Verify product is in cart
  WebElement cartItem = driver.findElement(By.xpath("//tr[@id='product-1']"));
  Assert.assertTrue(WaitUtils.waitForVisible(driver, cartItem).isDisplayed(),
          "Product should appear in cart");
 }

 @AfterMethod(alwaysRun = true)
 public void tearDown(ITestResult result) {
  if (!result.isSuccess()) {
   ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
  }
  DriverManager.quitDriver();
 }
}
