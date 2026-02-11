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

public class TC6_VerifyProducts {

 @Description("UI TC06: Verify that products page loads and displays products")
 @Severity(SeverityLevel.CRITICAL)
 @Test
 public void testVerifyProducts() {
  WebDriver driver = DriverManager.getDriver();
  driver.get("https://automationexercise.com");

  // Click 'Products' link
  WebElement productsLink = driver.findElement(By.xpath("//a[text()='Products']"));
  WaitUtils.waitForClickable(driver, productsLink).click();

  // Verify page title or products visible
  WebElement productsHeader = driver.findElement(By.xpath("//h2[text()='All Products']"));
  Assert.assertTrue(WaitUtils.waitForVisible(driver, productsHeader).isDisplayed(),
          "Products page should be visible");
 }

 @AfterMethod(alwaysRun = true)
 public void tearDown(ITestResult result) {
  if (!result.isSuccess()) {
   ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
  }
  DriverManager.quitDriver();
 }
}
