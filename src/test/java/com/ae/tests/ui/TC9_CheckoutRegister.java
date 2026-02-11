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

import java.util.UUID;

public class TC9_CheckoutRegister {

 @Description("UI TC09: Checkout process with account registration")
 @Severity(SeverityLevel.CRITICAL)
 @Test
 public void testCheckoutRegister() {
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

  // Register new user
  String uniqueEmail = "test" + UUID.randomUUID().toString().substring(0,5) + "@example.com";
  driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Test User");
  driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(uniqueEmail);
  driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

  driver.findElement(By.id("password")).sendKeys("Password123");
  driver.findElement(By.id("days")).sendKeys("10");
  driver.findElement(By.id("months")).sendKeys("May");
  driver.findElement(By.id("years")).sendKeys("1995");
  driver.findElement(By.id("first_name")).sendKeys("Test");
  driver.findElement(By.id("last_name")).sendKeys("User");
  driver.findElement(By.id("address1")).sendKeys("Test Street");
  driver.findElement(By.id("country")).sendKeys("Canada");
  driver.findElement(By.id("state")).sendKeys("State");
  driver.findElement(By.id("city")).sendKeys("City");
  driver.findElement(By.id("zipcode")).sendKeys("12345");
  driver.findElement(By.id("mobile_number")).sendKeys("1234567890");
  driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();

  // Verify account created page
  Assert.assertTrue(driver.getPageSource().toLowerCase().contains("account created"),
          "Expected account creation success message after checkout registration");
 }

 @AfterMethod(alwaysRun = true)
 public void tearDown(ITestResult result) {
  if (!result.isSuccess()) {
   ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
  }
  DriverManager.quitDriver();
 }
}
