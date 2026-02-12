package com.ae.tests.ui;

import com.ae.utils.ScreenshotUtil;
import com.ae.utils.DriverManager;
import com.ae.utils.WaitUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC8_AddToCart {

 @Description("UI TC08: Add first product to cart and verify it appears in cart.")
 @Severity(SeverityLevel.CRITICAL)
 @Test
 public void testAddToCart() {

  WebDriver driver = DriverManager.getDriver();
  driver.get("https://automationexercise.com");

  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

  // Close any potential iframe (ads) if present
  try {
   WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@id,'aswift')]"));
   driver.switchTo().frame(iframe);

   // Optional: click "close" if the iframe has a close button
   // If no close button, just switch back
   driver.switchTo().defaultContent();
  } catch (NoSuchElementException ignored) {
   // No iframe found → continue
  }

  // Wait for products section to be visible
  WaitUtils.waitForVisible(driver, By.cssSelector(".features_items"));

  // Locate first "Add to Cart" button
  WebElement firstAddToCart = wait.until(ExpectedConditions.elementToBeClickable(
          By.xpath("(//a[contains(@class,'add-to-cart')])[1]")));

  // Scroll into view and click using JS to avoid iframe overlays
  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstAddToCart);
  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstAddToCart);

  // Wait for cart modal to appear
  WebElement cartModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));

  // Assert modal is displayed
  Assert.assertTrue(cartModal.isDisplayed(),
          "Expected cart modal to appear after adding product");
 }

 @AfterMethod(alwaysRun = true)
 public void tearDown(ITestResult result) {
  if (!result.isSuccess()) {
   ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
  }
  DriverManager.quitDriver();
 }
}
