package com.ae.tests.ui;

import com.ae.utils.DriverManager;
import com.ae.utils.WaitUtils;
import com.ae.utils.ScreenshotUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC7_SearchProduct {

 @Description("UI TC07: Search for a product and verify results appear")
 @Severity(SeverityLevel.CRITICAL)
 @Test
 public void testSearchProducts() {
  WebDriver driver = DriverManager.getDriver();
  driver.get("https://automationexercise.com");

  // Go to Products
  WebElement productsLink = driver.findElement(By.xpath("//a[text()='Products']"));
  WaitUtils.waitForClickable(driver, productsLink).click();

  // Search for 'Dress'
  WebElement searchBox = driver.findElement(By.id("search_product"));
  WaitUtils.waitForVisible(driver, searchBox).sendKeys("Dress" + Keys.ENTER);

  // Verify results appear
  WebElement searchResult = driver.findElement(By.xpath("//h2[text()='Searched Products']"));
  Assert.assertTrue(WaitUtils.waitForVisible(driver, searchResult).isDisplayed(),
          "Search results should be visible");
 }

 @AfterMethod(alwaysRun = true)
 public void tearDown(ITestResult result) {
  if (!result.isSuccess()) {
   ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
  }
  DriverManager.quitDriver();
 }
}
