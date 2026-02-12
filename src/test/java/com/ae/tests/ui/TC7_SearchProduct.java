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

import java.util.List;

public class TC7_SearchProduct {

 @Description("UI TC07: Search for a product and verify results appear.")
 @Severity(SeverityLevel.CRITICAL)
 @Test
 public void testSearchProducts() {
  WebDriver driver = DriverManager.getDriver();
  driver.get("https://automationexercise.com");

  WebElement searchBox = driver.findElement(By.id("search_product"));
  WaitUtils.waitForVisible(driver, searchBox);
  searchBox.sendKeys("Dress");

  driver.findElement(By.id("submit_search")).click();

  List<WebElement> results = driver.findElements(By.cssSelector(".features_items .product-image-wrapper"));
  for (WebElement r : results) {
   WaitUtils.waitForVisible(driver, r);
  }

  Assert.assertTrue(results.size() > 0, "Expected search results for 'Dress'");
 }

 @AfterMethod(alwaysRun = true)
 public void tearDown(ITestResult result) {
  if (!result.isSuccess()) {
   ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
  }
  DriverManager.quitDriver();
 }
}
