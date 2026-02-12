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

  // 1️⃣ Open products page directly
  driver.get("https://automationexercise.com/products");

  // 2️⃣ Wait for search box
  WebElement searchBox = WaitUtils.waitForVisible(driver,
          By.id("search_product"));

  // 3️⃣ Type product
  searchBox.sendKeys("Dress");

  // 4️⃣ Click search
  driver.findElement(By.id("submit_search")).click();

  // 5️⃣ Wait for results
  List<WebElement> results = WaitUtils.waitForAllVisible(driver,
          By.cssSelector(".product-image-wrapper"));

  Assert.assertTrue(results.size() > 0,
          "Expected search results for 'Dress'");
 }

 @AfterMethod(alwaysRun = true)
 public void tearDown(ITestResult result) {
  if (!result.isSuccess()) {
   ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
  }
  DriverManager.quitDriver();
 }
}
