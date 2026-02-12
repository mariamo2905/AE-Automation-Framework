package com.ae.tests.ui;

import com.ae.utils.DriverManager;
import com.ae.utils.ScreenshotUtil;
import com.ae.utils.WaitUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC9_CheckoutRegister {

 @Test
 @Description("UI TC09: Go to checkout, register a new user, and verify checkout page.")
 @Severity(SeverityLevel.CRITICAL)
 public void testCheckoutRegister() {
  WebDriver driver = DriverManager.getDriver();
  driver.get("https://automationexercise.com");

  closeAds(driver);

  // 1️⃣ Add first product to cart safely
  WebElement addToCartBtn = WaitUtils.waitForClickable(driver,
          By.xpath("(//a[contains(@class,'add-to-cart')])[1]"));
  WaitUtils.clickJS(driver, addToCartBtn);

  // Close modal if "Continue Shopping" appears
  try {
   WebElement continueBtn = WaitUtils.waitForVisible(driver,
           By.xpath("//button[normalize-space()='Continue Shopping']"));
   WaitUtils.clickJS(driver, continueBtn);
  } catch (Exception ignored) {}

  // 2️⃣ Go to Cart
  WaitUtils.waitAndClickJS(driver, By.xpath("//a[@href='/view_cart']"));

  // 3️⃣ Proceed To Checkout
  WebElement checkoutBtn = WaitUtils.waitForClickable(driver,
          By.xpath("//a[contains(text(),'Proceed To Checkout')]"));
  WaitUtils.clickJS(driver, checkoutBtn);

  // 4️⃣ Click Register / Login
  WebElement registerLink = WaitUtils.waitForClickable(driver,
          By.xpath("//u[normalize-space()='Register / Login']"));
  WaitUtils.clickJS(driver, registerLink);

  // 5️⃣ Fill signup form
  WaitUtils.waitForVisible(driver, By.name("name")).sendKeys("Test User");
  driver.findElement(By.xpath("//input[@data-qa='signup-email']"))
          .sendKeys("testuser" + System.currentTimeMillis() + "@example.com");
  WaitUtils.waitAndClickJS(driver, By.xpath("//button[@data-qa='signup-button']"));

  // 6️⃣ Verify Create Account page appears
  WebElement createAccountBtn = WaitUtils.waitForVisible(driver,
          By.xpath("//button[@data-qa='create-account']"));
  Assert.assertTrue(createAccountBtn.isDisplayed(),
          "Expected 'Create Account' button to appear.");
 }

 private void closeAds(WebDriver driver) {
  try {
   Thread.sleep(500);
   for (WebElement iframe : driver.findElements(By.xpath("//iframe[contains(@id,'aswift')]"))) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", iframe);
   }
  } catch (InterruptedException | StaleElementReferenceException ignored) {}
 }

 @AfterMethod(alwaysRun = true)
 public void tearDown(ITestResult result) {
  if (!result.isSuccess()) {
   ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
  }
  DriverManager.quitDriver();
 }
}
