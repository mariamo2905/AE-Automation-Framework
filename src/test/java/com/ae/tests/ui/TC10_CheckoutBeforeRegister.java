package com.ae.tests.ui;

import com.ae.utils.DriverManager;
import com.ae.utils.WaitUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC10_CheckoutBeforeRegister {

 @Description("UI TC10: Verify checkout before login shows Register/Login popup.")
 @Severity(SeverityLevel.CRITICAL)
 @Test
 public void testCheckoutBeforeRegister() {

  WebDriver driver = DriverManager.getDriver();
  driver.get("https://automationexercise.com");

  // Click 'Cart' link in the header (or wherever your cart icon is)
  driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();

  // Click 'Proceed To Checkout' in the cart page
  driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]")).click();

  // Wait for the popup heading 'Checkout'
  WebElement popupHeader = WaitUtils.waitForVisible(
          driver,
          By.xpath("//h2[contains(text(),'Checkout')]")
  );
  Assert.assertTrue(popupHeader.isDisplayed(), "Expected checkout popup to appear.");

  // Verify popup text contains 'Register / Login account to proceed on checkout.'
  WebElement popupText = driver.findElement(
          By.xpath("//p[contains(text(),'Register / Login account to proceed on checkout.')]")
  );
  Assert.assertTrue(popupText.isDisplayed(), "Expected popup message about registering/logging in.");

  // Verify 'Register / Login' button exists in the popup
  WebElement registerLoginBtn = driver.findElement(By.xpath("//a[contains(text(),'Register / Login')]"));
  Assert.assertTrue(registerLoginBtn.isDisplayed(), "Expected Register/Login button in checkout popup.");
 }
}
